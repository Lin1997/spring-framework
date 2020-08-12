import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
	@Test
	public void test() {
		ApplicationContext context = new AnnotationConfigApplicationContext();
		System.out.println(context);
	}
}
