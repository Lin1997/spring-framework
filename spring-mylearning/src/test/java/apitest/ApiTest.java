package apitest;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class ApiTest {
	@Test
	public void autowireTest() {
		// 首先构造一个 DefaultListableBeanFactory 作为 BeanDefinitionRegistry
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		// 进行具体的对象注册和相关依赖管理
		BeanFactory container = bindViaCode(beanRegistry);
		TestComponent testComponent = container.getBean(TestComponent.class);
		System.out.println(testComponent + " 有个一依赖: " + testComponent.getTestComponent1());
	}

	private BeanFactory bindViaCode(BeanDefinitionRegistry registry) {
		AbstractBeanDefinition testComponent =
				new RootBeanDefinition(TestComponent.class);    // 使用RootBeanDefinition作为BeanDefinition的实现类
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
