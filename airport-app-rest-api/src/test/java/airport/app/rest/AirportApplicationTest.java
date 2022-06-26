package airport.app.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AirportApplication.class)
public class AirportApplicationTest {

	@Autowired
	private ApplicationContext appContexContext;

	/**
	 * This test just confirms that Spring Application Context can be wired up and
	 * started
	 */
	@Test
	public void contextLoads() {
		assertThat(appContexContext).isNotNull();
	}
}
