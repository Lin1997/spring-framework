package mytest;

import mytest.component.TestComponent;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
	@Test
	public void test() {
		//ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		TestComponent bean = context.getBean(TestComponent.class);
		System.out.println(bean + " 有个一依赖: " + bean.getTestComponent1());
	}
}
