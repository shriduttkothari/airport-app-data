package airport.app.rest.service;

import java.time.LocalDate;
import java.util.Collection;

import airport.app.rest.dto.FlightDTO;

public interface FlightService {

	public Collection<FlightDTO> getFlightsForGivenDate(LocalDate searchDate);

}
