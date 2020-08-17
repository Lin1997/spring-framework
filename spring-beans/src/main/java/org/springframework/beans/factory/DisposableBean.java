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
 * 容器会检查singleton类型的bean实例，看其是否实现了DisposableBean接口。
 * 或者其对应的bean定义是否通过<bean>的destroy-method属性指定了自定义的对象销毁方法。如果是，
 * 就会为该实例注册一个用于对象销毁的回调（Callback），以便在这些singleton类型的对象实例销毁之
 * 前，执行销毁逻辑。
 * 最常见到的该功能的使用场景就是在Spring容器中注册数据库连接池，在系统退出后，连接池应
 * 该关闭，以释放相应资源.
 * 但Spring容器在关闭之前，不会聪明到自动调用这些回调方法。
 * 对于BeanFactory容器来说。我们需要在独立应用程序的主程序退出之前，或者其他被认为是合
 * 适的情况下，调用ConfigurableBeanFactory提供的
 * destroySingletons()方法销毁容器中管理的所有singleton类型的对象实例.
 * 对于ApplicationContext容器来说。道理是一样的。不过AbstractApplicationContext为我们
 * 提供了方便的registerShutdownHook()方法，用户调用完该方法后，
 * 该方法底层将使用标准的Runtime类的addShutdownHook()方式来调用相应bean对象的销毁逻辑，
 * 从而保证在Java虚拟机退出之前，这些singtleton类型的bean对象实例的自定义销毁逻辑会被执行。
 * <p>
 * Interface to be implemented by beans that want to release resources on destruction.
 * A {@link BeanFactory} will invoke the destroy method on individual destruction of a
 * scoped bean. An {@link org.springframework.context.ApplicationContext} is supposed
 * to dispose all of its singletons on shutdown, driven by the application lifecycle.
 *
 * <p>A Spring-managed bean may also implement Java's {@link AutoCloseable} interface
 * for the same purpose. An alternative to implementing an interface is specifying a
 * custom destroy method, for example in an XML bean definition. For a list of all
 * bean lifecycle methods, see the {@link BeanFactory BeanFactory javadocs}.
 *
 * @author Juergen Hoeller
 * @since 12.08.2003
 * @see InitializingBean
 * @see org.springframework.beans.factory.support.RootBeanDefinition#getDestroyMethodName()
 * @see org.springframework.beans.factory.config.ConfigurableBeanFactory#destroySingletons()
 * @see org.springframework.context.ConfigurableApplicationContext#close()
 */
public interface DisposableBean {

	/**
	 * Invoked by the containing {@code BeanFactory} on destruction of a bean.
	 * @throws Exception in case of shutdown errors. Exceptions will get logged
	 * but not rethrown to allow other beans to release their resources as well.
	 */
	void destroy() throws Exception;

}
