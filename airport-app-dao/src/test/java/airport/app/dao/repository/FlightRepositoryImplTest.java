package airport.app.dao.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import airport.app.dao.model.Flight;

@ExtendWith(MockitoExtension.class)
public class FlightRepositoryImplTest {

	@InjectMocks
	private static FlightRepositoryImpl classUnderTest;

	@Test
	public void test_list_is_empty_before_flights_are_loaded_in_memory() {

		Arrays.stream(DayOfWeek.values())
				.forEach(dayOfWeek -> assertThat(classUnderTest.getFlightsForGivenDayOfWeek(dayOfWeek).isEmpty()));
	}

	@Test
	public void test_list_contains_flight_with_matching_day_of_the_week() {

		Flight flight = new Flight();
		Set<DayOfWeek> availableOnDayOfWeek = new HashSet<DayOfWeek>();
		availableOnDayOfWeek.add(DayOfWeek.FRIDAY);
		flight.setAvailableOnDayOfWeek(availableOnDayOfWeek);
		List<Flight> flights = Collections.singletonList(flight);

		classUnderTest.saveFlights(flights);

		assertThat(classUnderTest.getFlightsForGivenDayOfWeek(DayOfWeek.FRIDAY)).contains(flight);
	}
	
	@Test
	public void test_save_is_adding_all_the_given_elements() {

		Flight flight1 = new Flight();
		Set<DayOfWeek> availableOnDayOfWeek1 = new HashSet<DayOfWeek>();
		availableOnDayOfWeek1.add(DayOfWeek.FRIDAY);
		flight1.setAvailableOnDayOfWeek(availableOnDayOfWeek1);
		List<Flight> flights1 = Collections.singletonList(flight1);

		classUnderTest.saveFlights(flights1);
		
		Flight flight2 = new Flight();
		Set<DayOfWeek> availableOnDayOfWeek2 = new HashSet<DayOfWeek>();
		availableOnDayOfWeek2.add(DayOfWeek.FRIDAY);
		flight2.setAvailableOnDayOfWeek(availableOnDayOfWeek2);
		List<Flight> flights2 = Collections.singletonList(flight2);

		
		classUnderTest.saveFlights(flights2);
		assertThat(classUnderTest.getAllFlights().size()).isGreaterThanOrEqualTo(2);
		assertThat(classUnderTest.getAllFlights()).contains(flight1);
		assertThat(classUnderTest.getAllFlights()).contains(flight2);
	}
}
