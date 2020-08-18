package mytest.component;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
	@Before(" within(mytest.component.TestComponent)")
	public void before() {
		System.out.println("before...");
	}
}
