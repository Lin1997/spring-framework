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
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
	@Test
	public void test() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		//ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		TestComponent bean = context.getBean(TestComponent.class);
		System.out.println(bean + " 有个一依赖: " + bean.getTestComponent1());
	}
}
