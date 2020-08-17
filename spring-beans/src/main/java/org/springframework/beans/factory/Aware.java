/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.beans.factory;

/**
 * 当对象实例化完成并且相关属性以及依赖设置完成之后，Spring容器会检查当前对象实例是否实
 * 现了一系列的以Aware命名结尾的接口定义。如果是，则将这些Aware接口定义中规定的依赖注入给
 * 当前对象实例.
 * 这些Aware接口为如下几个:
 *  org.springframework.beans.factory.BeanNameAware。如果Spring容器检测到当前对象实
 * 例实现了该接口，会将该对象实例的bean定义对应的beanName设置到当前对象实例。
 *  org.springframework.beans.factory.BeanClassLoaderAware。如果容器检测到当前对
 * 象实例实现了该接口，会将对应加载当前bean的Classloader注入当前对象实例。默认会使用
 * 加载org.springframework.util.ClassUtils类的Classloader。
 *  org.springframework.beans.factory.BeanFactoryAware。在介绍方法注入的时候，我们
 * 提到过使用该接口以便每次获取prototype类型bean的不同实例。如果对象声明实现了
 * BeanFactoryAware接口，BeanFactory容器会将自身设置到当前对象实例。这样，当前对象
 * 实例就拥有了一个BeanFactory容器的引用，并且可以对这个容器内允许访问的对象按照需要
 * 进行访问.
 * 以上几个Aware接口只是针对BeanFactory类型的容器而言，对于ApplicationContext类型的容
 * 器，也存在几个Aware相关接口。不过在检测这些接口并设置相关依赖的实现机理上，与以上几个接
 * 口处理方式有所不同，使用的是BeanPostProcessor的方式。不过，设置Aware接口这
 * 一步与BeanPostProcessor是相邻的.
 * 更多信息:
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aware-list
 *
 * A marker superinterface indicating that a bean is eligible to be notified by the
 * Spring container of a particular framework object through a callback-style method.
 * The actual method signature is determined by individual subinterfaces but should
 * typically consist of just one void-returning method that accepts a single argument.
 *
 * <p>Note that merely implementing {@link Aware} provides no default functionality.
 * Rather, processing must be done explicitly, for example in a
 * {@link org.springframework.beans.factory.config.BeanPostProcessor}.
 * Refer to {@link org.springframework.context.support.ApplicationContextAwareProcessor}
 * for an example of processing specific {@code *Aware} interface callbacks.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.1
 */
public interface Aware {

}
