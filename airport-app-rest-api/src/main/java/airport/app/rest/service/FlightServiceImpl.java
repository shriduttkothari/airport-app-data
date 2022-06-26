package airport.app.rest.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Service;

import airport.app.dao.model.Flight;
import airport.app.dao.repository.FlightRepository;
import airport.app.rest.dto.FlightDTO;
import airport.app.rest.mapper.FlightDTOMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

	private final FlightRepository flightRepository;
	private final FlightDTOMapper flightDTOMapper;

	@Override
	public Collection<FlightDTO> getFlightsForGivenDate(LocalDate searchDate) {
		if (null == searchDate) {
			throw new IllegalArgumentException("searchDate can not be null");
		}
		DayOfWeek dayOfTheWeek = searchDate.getDayOfWeek();

		Collection<Flight> flightlist = flightRepository.getFlightsForGivenDayOfWeek(dayOfTheWeek);
		Collection<FlightDTO> flightDTOlist = flightDTOMapper.mapToFlightDTO(flightlist);
		return flightDTOlist;
	}
}
