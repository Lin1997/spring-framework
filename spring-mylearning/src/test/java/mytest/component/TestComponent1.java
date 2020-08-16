package mytest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestComponent1 {
	TestComponent testComponent;

	public TestComponent1() {

	}

	@Autowired
	public TestComponent1(TestComponent testComponent) {
		this.testComponent = testComponent;
	}

	public TestComponent getTestComponent() {
		return testComponent;
	}

	public void setTestComponent(TestComponent testComponent) {
		this.testComponent = testComponent;
	}
}
