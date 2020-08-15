package mytest.component;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {
	@Before(" within(mytest.component.TestComponent)")
	public void before() {
		System.out.println("before...");
	}
}
