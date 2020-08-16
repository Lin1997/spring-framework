package mytest;

import mytest.component.TestComponent;
import mytest.component.TestComponent1;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
	@Test
	public void test() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(context.getBean(TestComponent.class));
	}

	@Test
	public void apiTest() {
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		BeanFactory container = bindViaCode(beanRegistry);
		TestComponent testComponent = container.getBean(TestComponent.class);
		System.out.println(testComponent.getTestComponent1());
	}

	private BeanFactory bindViaCode(BeanDefinitionRegistry registry) {
		AbstractBeanDefinition testComponent =
				new RootBeanDefinition(TestComponent.class);
		AbstractBeanDefinition testComponent1 =
				new RootBeanDefinition(TestComponent1.class);
		// 将bean定义注册到容器中
		registry.registerBeanDefinition("testComponent", testComponent);
		registry.registerBeanDefinition("testComponent1", testComponent1);
		// 指定依赖关系
		if (Math.random() >= 0.5) {
			// 1. 可以通过构造方法注入方式
			ConstructorArgumentValues argValues = new ConstructorArgumentValues();
			argValues.addIndexedArgumentValue(0, testComponent1);
			testComponent.setConstructorArgumentValues(argValues);
		} else {
			// 2. 或者通过setter方法注入方式
			MutablePropertyValues propertyValues = new MutablePropertyValues();
			propertyValues.addPropertyValue(new PropertyValue("testComponent1", testComponent1));
			testComponent.setPropertyValues(propertyValues);
		}
		// 绑定完成
		return (BeanFactory) registry;
	}
}
