package mytest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
	TestComponent1 testComponent1;

	public TestComponent() {

	}

	@Autowired
	public TestComponent(TestComponent1 testComponent1) {
		System.out.println("通过构造方法注入方式");
		this.testComponent1 = testComponent1;
	}

	public TestComponent1 getTestComponent1() {
		return testComponent1;
	}

	public void setTestComponent1(TestComponent1 testComponent1) {
		System.out.println("通过setter方法注入方式");
		this.testComponent1 = testComponent1;
	}
}
