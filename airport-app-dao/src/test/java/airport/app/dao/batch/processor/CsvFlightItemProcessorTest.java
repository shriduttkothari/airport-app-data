package airport.app.dao.batch.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import airport.app.dao.batch.model.FlightFromBatch;
import airport.app.dao.mapper.FlightMapper;
import airport.app.dao.model.Flight;
import lombok.SneakyThrows;

/**
 * Example of JUnit using Mockito with Mockito annotations
 * 
 * @author Shridutt.Kothari
 *
 */
@ExtendWith(MockitoExtension.class)
public class CsvFlightItemProcessorTest {

	@InjectMocks
	private CsvFlightItemProcessor classUnderTest;

	@Mock
	private FlightMapper flightMapper;

	@Test
	@SneakyThrows
	public void test_process_returns_whatever_is_recevied_from_mapper() {

		FlightFromBatch flightFromBatch = new FlightFromBatch();
		Flight flightExpected = new Flight();
		when(flightMapper.mapFlightFromBatchToFlight(flightFromBatch)).thenReturn(flightExpected);

		Flight flightActual = classUnderTest.process(flightFromBatch);

		assertThat(flightActual).isEqualTo(flightExpected);
	}
}
