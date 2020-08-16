package mytest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
	TestComponent1 testComponent1;

	@Autowired
	public TestComponent(TestComponent1 testComponent1) {
		this.testComponent1 = testComponent1;
	}

	public void hello() {
		System.out.println("Hello!");
	}
}
