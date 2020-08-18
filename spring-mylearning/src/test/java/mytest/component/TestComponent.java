package mytest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
	@Autowired
	TestComponent1 testComponent1;

	public TestComponent() {

	}

	//@Autowired
	public TestComponent(TestComponent1 testComponent1) {
		this.testComponent1 = testComponent1;
	}

	public TestComponent1 getTestComponent1() {
		return testComponent1;
	}

	public void setTestComponent1(TestComponent1 testComponent1) {
		this.testComponent1 = testComponent1;
	}
}
