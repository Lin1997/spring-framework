package mytest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestComponent1 {
	TestComponent testComponent;

	@Autowired
	public TestComponent1(TestComponent testComponent) {
		this.testComponent = testComponent;
	}
}
