package airport.app.rest.mapper;

import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import airport.app.dao.model.Flight;
import airport.app.rest.dto.FlightDTO;

@Component
public class FlightDTOMapperImpl implements FlightDTOMapper {

	public Collection<FlightDTO> mapToFlightDTO(Collection<Flight> flights) {

		if (null == flights) {
			return null;
		}

		return flights.stream().map(flight -> {
			return FlightDTO.builder()
					.departureTime(flight.getDepartureTime())
					.destination(flight.getDestination())
					.destinationAirportIATA(flight.getDestinationAirportIATA())
					.flightNo(flight.getFlightNo())
					.build();
		}).collect(Collectors.toCollection(TreeSet::new));
	}
}
