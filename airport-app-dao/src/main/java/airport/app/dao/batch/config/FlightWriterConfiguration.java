package airport.app.dao.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import airport.app.dao.batch.writer.FlightWritter;
import airport.app.dao.repository.FlightRepository;

@Configuration
public class FlightWriterConfiguration {

	@Bean
	public FlightWritter writer(FlightRepository flightRepository) {
		FlightWritter flightBatchItemWriter = new FlightWritter(flightRepository);
		return flightBatchItemWriter;
	}
}
