package airport.app.dao.repository;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;

import airport.app.dao.exception.NoFlightAvailableException;
import airport.app.dao.model.Flight;

public interface FlightRepository {

	public void saveFlights(List<? extends Flight> items);

	public Collection<Flight> getFlightsForGivenDayOfWeek(DayOfWeek dayOfTheWeek) throws NoFlightAvailableException;

	public List<Flight> getAllFlights();

}
