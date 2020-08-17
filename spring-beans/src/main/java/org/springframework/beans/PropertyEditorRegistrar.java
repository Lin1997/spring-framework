/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans;

/**
 * PropertyEditor更多信息:
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-beans-conversion
 * <p>
 *  Spring内部通过JavaBean的PropertyEditor来帮助进行String类型到其他类型的转换工作。只要
 * 为每种对象类型提供一个 PropertyEditor ，就可以根据该对象类型取得与其相对应的
 * PropertyEditor来做具体的类型转换。Spring容器内部在做具体的类型转换的时候，会采用JavaBean
 * 框架内默认的PropertyEditor搜寻逻辑，从而继承了对原生类型以及java.lang.String.java.awt.
 * Color和java.awt.Font等类型的转换支持。
 * 以下是这些Spring提供的部分PropertyEditor的简要说明:
 *  StringArrayPropertyEditor。该PropertyEditor会将符合CSV格式的字符串转换成
 * String[]数组的形式，默认是以逗号（，）分隔的字符串，但可以指定自定义的字符串分隔
 * 符。ByteArrayPropertyEditor、CharArrayPropertyEditor等都属于类似功能的PropertyEditor，参照Javadoc可以取得相应的详细信息。
 *  ClassEditor。根据String类型的class名称，直接将其转换成相应的Class对象，相当于通
 * 过Class.forName(String)完成的功效。可以通过String[]数组的形式传入需转换的值，以
 * 达到与提供的ClassArrayEditor同样的目的。
 *  FileEditor。Spring提供的对应java.io.File类型的PropertyEditor。同属于对资源进行
 * 定位的PropertyEditor还有InputStreamEditor、URLEditor等。
 *  LocaleEditor。针对java.util.Locale类型的PropertyEditor，格式可以参照LocaleEditor和Locale的Javadoc说明。
 *  PatternEditor。针对Java SE 1.4之后才引入的java.util.regex.Pattern的PropertyEditor，格式可以参照java.util.regex.Pattern类的Javadoc。
 * <p>
 * Interface for strategies that register custom
 * {@link java.beans.PropertyEditor property editors} with a
 * {@link org.springframework.beans.PropertyEditorRegistry property editor registry}.
 *
 * <p>This is particularly useful when you need to use the same set of
 * property editors in several different situations: write a corresponding
 * registrar and reuse that in each case.
 *
 * @author Juergen Hoeller
 * @since 1.2.6
 * @see PropertyEditorRegistry
 * @see java.beans.PropertyEditor
 */
public interface PropertyEditorRegistrar {

	/**
	 * Register custom {@link java.beans.PropertyEditor PropertyEditors} with
	 * the given {@code PropertyEditorRegistry}.
	 * <p>The passed-in registry will usually be a {@link BeanWrapper} or a
	 * {@link org.springframework.validation.DataBinder DataBinder}.
	 * <p>It is expected that implementations will create brand new
	 * {@code PropertyEditors} instances for each invocation of this
	 * method (since {@code PropertyEditors} are not threadsafe).
	 * @param registry the {@code PropertyEditorRegistry} to register the
	 * custom {@code PropertyEditors} with
	 */
	void registerCustomEditors(PropertyEditorRegistry registry);

}
