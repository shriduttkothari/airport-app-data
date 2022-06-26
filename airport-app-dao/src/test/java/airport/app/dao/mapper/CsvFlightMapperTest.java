package airport.app.dao.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import airport.app.dao.batch.model.FlightFromBatch;
import airport.app.dao.model.Flight;

public class CsvFlightMapperTest {

	private static CsvFlightMapper classUnderTest;

	@BeforeAll
	public static void init() {
		classUnderTest = new CsvFlightMapper();
	}

	@Test
	public void test_map_flight_from_batch_to_flight_returns_null_for_null_input() {

		Flight flightActual = classUnderTest.mapFlightFromBatchToFlight(null);

		assertThat(flightActual).isNull();
	}

	@Test
	public void test_departure_time_mapped_correctly() {

		FlightFromBatch flightFromBatch = buildFlightFromBatch();
		flightFromBatch.setDepartureTime("20:21");
		Flight flightActual = classUnderTest.mapFlightFromBatchToFlight(flightFromBatch);

		assertThat(flightActual.getDepartureTime()).isEqualTo(LocalTime.of(20, 21));
	}

	@Test
	public void test_destination_mapped_correctly() {

		FlightFromBatch flightFromBatch = buildFlightFromBatch();
		flightFromBatch.setDestination("Barbados");

		Flight flightActual = classUnderTest.mapFlightFromBatchToFlight(flightFromBatch);

		assertThat(flightActual.getDestination()).isEqualTo("Barbados");
	}

	@Test
	public void test_destination_airport_iata_mapped_correctly() {

		FlightFromBatch flightFromBatch = buildFlightFromBatch();
		flightFromBatch.setDestinationAirportIATA("BGI");

		Flight flightActual = classUnderTest.mapFlightFromBatchToFlight(flightFromBatch);

		assertThat(flightActual.getDestinationAirportIATA()).isEqualTo("BGI");
	}

	@Test
	public void test_flight_no_mapped_correctly() {

		FlightFromBatch flightFromBatch = buildFlightFromBatch();
		flightFromBatch.setFlightNo("VS029");

		Flight flightActual = classUnderTest.mapFlightFromBatchToFlight(flightFromBatch);

		assertThat(flightActual.getFlightNo()).isEqualTo("VS029");
	}

	@Test
	public void test_available_on_day_of_week_mapped_correctly_for_all_available() {

		FlightFromBatch flightFromBatch = buildFlightFromBatch();
		flightFromBatch.setSunday("x");
		flightFromBatch.setMonday("x");
		flightFromBatch.setTuesday("x");
		flightFromBatch.setWednesday("x");
		flightFromBatch.setThursday("x");
		flightFromBatch.setFriday("x");
		flightFromBatch.setSaturday("x");

		Flight flightActual = classUnderTest.mapFlightFromBatchToFlight(flightFromBatch);

		assertThat(flightActual.getAvailableOnDayOfWeek()).contains(DayOfWeek.values());
	}
	
	@Test
	public void test_available_on_day_of_week_mapped_correctly_for_all_non_available() {

		FlightFromBatch flightFromBatch = buildFlightFromBatch();
		
		Flight flightActual = classUnderTest.mapFlightFromBatchToFlight(flightFromBatch);

		assertThat(flightActual.getAvailableOnDayOfWeek()).doesNotContain(DayOfWeek.values());
	}

	private FlightFromBatch buildFlightFromBatch() {
		FlightFromBatch flightFromBatch = new FlightFromBatch();
		flightFromBatch.setDepartureTime("19:20");
		return flightFromBatch;
	}
}
