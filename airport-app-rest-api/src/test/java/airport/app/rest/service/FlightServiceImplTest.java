package airport.app.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import airport.app.dao.model.Flight;
import airport.app.dao.repository.FlightRepository;
import airport.app.rest.dto.FlightDTO;
import airport.app.rest.mapper.FlightDTOMapper;
import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {

	@InjectMocks
	private FlightServiceImpl classUnderTest;

	@Mock
	private FlightRepository flightRepository;

	@Mock
	private FlightDTOMapper flightMapper;

	
	@Test
	@SneakyThrows
	public void test_get_flights_for_given_day_of_week_throws_illeg() {

		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
			classUnderTest.getFlightsForGivenDate(null);
		}).withMessage("searchDate can not be null");
	}

	@Test
	@SneakyThrows
	public void test_get_flights_for_given_day_of_week_returns_whatever_is_recevied_from_flight_repository() {

		Set<Flight> flightSetExpexted = Collections.emptySet();
		Set<FlightDTO> flightDTOSetExpexted = Collections.emptySet();

		LocalDate searchDate = LocalDate.of(2022, 6, 26);
		DayOfWeek searchDayOfWeek = searchDate.getDayOfWeek();

		when(flightRepository.getFlightsForGivenDayOfWeek(searchDayOfWeek)).thenReturn(flightSetExpexted);
		when(flightMapper.mapToFlightDTO(flightSetExpexted)).thenReturn(flightDTOSetExpexted);
		
		Collection<FlightDTO> flightDTOSetActual = classUnderTest.getFlightsForGivenDate(searchDate);

		assertThat(flightDTOSetActual).isEqualTo(flightDTOSetExpexted);
	}
}
