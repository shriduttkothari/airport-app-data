package airport.app.dao.repository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import airport.app.dao.exception.NoFlightAvailableException;
import airport.app.dao.model.Flight;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

	private static final List<Flight> availableFlights = new ArrayList<>();

	@Override
	public void saveFlights(List<? extends Flight> items) {
		availableFlights.addAll(items);
	}

	@Override
	public List<Flight> getAllFlights() {
		return availableFlights;
	}

	@Override
	public List<Flight> getFlightsForGivenDayOfWeek(DayOfWeek dayOfTheWeek) throws NoFlightAvailableException {

		return availableFlights.stream().filter(flight -> flight.getAvailableOnDayOfWeek().contains(dayOfTheWeek))
				.collect(Collectors.toList());

	}

}
