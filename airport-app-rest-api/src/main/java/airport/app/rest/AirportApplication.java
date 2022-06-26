package airport.app.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = { "airport.app.rest", "airport.app.dao" }, exclude = {
		DataSourceAutoConfiguration.class })
public class AirportApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);
	}
}
