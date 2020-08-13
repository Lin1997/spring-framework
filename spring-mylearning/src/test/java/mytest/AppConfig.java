package mytest;

import mytest.component.TestComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public TestComponent myService() {
		return new TestComponent();
	}
}