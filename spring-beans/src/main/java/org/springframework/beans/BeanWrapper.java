/*
 * Copyright 2002-2017 the original author or authors.
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

import java.beans.PropertyDescriptor;

/**
 * 容器只要根据相应bean定义的BeanDefinition取得实例化信息，
 * 结合CglibSubclassingInstantiationStrategy以及不同的bean定义类型，就可以返回实例化完成的对象实例。但是，返回方
 * 式上有些“点缀”。不是直接返回构造完成的对象实例，而是以BeanWrapper对构造完成的对象实例
 * 进行包裹，返回相应的BeanWrapper实例.
 * BeanWrapper接口通常在Spring框架内部使用，它有一个实现类BeanWrapperImpl.
 * 其作用是对某个bean进行“包裹”，然后就可以对这个“包裹”的bean进行操作，比如
 * 设置或者获取bean的相应属性值。而在bean实例化第一步结束后返回BeanWrapper实例而不是原先的对象实例，
 * 就是为了第二步“设置对象属性”.
 *
 * BeanWrapper定义继承了org.springframework.beans.PropertyAccessor接口，可以以统一的
 * 方式对对象属性进行访问；BeanWrapper定义同时又直接或者间接继承了PropertyEditorRegistry
 * 和TypeConverter接口。不知你是否还记得CustomEditorConfigurer？当把各种PropertyEditor注
 * 册给容器时，知道后面谁用到这些PropertyEditor吗？对，就是BeanWrapper！在第一步构造完成
 * 对象之后，Spring会根据对象实例构造一个BeanWrapperImpl实例，
 * 然后将之前CustomEditorConfigurer注册的PropertyEditor复制一份给BeanWrapperImpl实
 * （这就是BeanWrapper同时又是PropertyEditorRegistry的原因）。
 * 这样，当BeanWrapper转换类型、设置对象属性值时，就不会无从下手了。
 *
 * 使用BeanWrapper对bean实例操作很方便，可以免去直接使用Java反射API（Java Reflection API）
 * 操作对象实例的烦琐.
 * 假如我们有一个FXNewsProvider类型的provider,它有一个DowJonesNewsListener的属性:
 * Object provider = Class.forName("package.name.FXNewsProvider").newInstance();
 * Object listener = Class.forName("package.name.DowJonesNewsListener").newInstance();
 * 当我们使用BeanWrapper时，只需:
 * BeanWrapper newsProvider = new BeanWrapperImpl(provider);	// 包装成BeanWrapper
 * newsProvider.setPropertyValue("newsListener", listener);		//	简单的API
 * 而不是用反射:
 * Class providerClazz = provider.getClass();
 * Field listenerField = providerClazz.getField("newsListener");
 * listenerField.set(provider, listener);
 * 如果你觉得没有太大差别，那是因为没有看到紧随其后的那些异常（exception）还有待处理!
 * <p>
 *
 * The central interface of Spring's low-level JavaBeans infrastructure.
 *
 * <p>Typically not used directly but rather implicitly via a
 * {@link org.springframework.beans.factory.BeanFactory} or a
 * {@link org.springframework.validation.DataBinder}.
 *
 * <p>Provides operations to analyze and manipulate standard JavaBeans:
 * the ability to get and set property values (individually or in bulk),
 * get property descriptors, and query the readability/writability of properties.
 *
 * <p>This interface supports <b>nested properties</b> enabling the setting
 * of properties on subproperties to an unlimited depth.
 *
 * <p>A BeanWrapper's default for the "extractOldValueForEditor" setting
 * is "false", to avoid side effects caused by getter method invocations.
 * Turn this to "true" to expose present property values to custom editors.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 13 April 2001
 * @see PropertyAccessor
 * @see PropertyEditorRegistry
 * @see PropertyAccessorFactory#forBeanPropertyAccess
 * @see org.springframework.beans.factory.BeanFactory
 * @see org.springframework.validation.BeanPropertyBindingResult
 * @see org.springframework.validation.DataBinder#initBeanPropertyAccess()
 */
public interface BeanWrapper extends ConfigurablePropertyAccessor {

	/**
	 * Specify a limit for array and collection auto-growing.
	 * <p>Default is unlimited on a plain BeanWrapper.
	 * @since 4.1
	 */
	void setAutoGrowCollectionLimit(int autoGrowCollectionLimit);

	/**
	 * Return the limit for array and collection auto-growing.
	 * @since 4.1
	 */
	int getAutoGrowCollectionLimit();

	/**
	 * Return the bean instance wrapped by this object.
	 */
	Object getWrappedInstance();

	/**
	 * Return the type of the wrapped bean instance.
	 */
	Class<?> getWrappedClass();

	/**
	 * Obtain the PropertyDescriptors for the wrapped object
	 * (as determined by standard JavaBeans introspection).
	 * @return the PropertyDescriptors for the wrapped object
	 */
	PropertyDescriptor[] getPropertyDescriptors();

	/**
	 * Obtain the property descriptor for a specific property
	 * of the wrapped object.
	 * @param propertyName the property to obtain the descriptor for
	 * (may be a nested path, but no indexed/mapped property)
	 * @return the property descriptor for the specified property
	 * @throws InvalidPropertyException if there is no such property
	 */
	PropertyDescriptor getPropertyDescriptor(String propertyName) throws InvalidPropertyException;

}
