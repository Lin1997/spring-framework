package mytest.component;

import org.springframework.beans.factory.FactoryBean;

public class TestComponent1 implements FactoryBean<TestComponent1> {
	@Override
	public TestComponent1 getObject() throws Exception {
		return new TestComponent1();
	}

	@Override
	public Class<?> getObjectType() {
		return getClass();
	}
}
