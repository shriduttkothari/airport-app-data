package airport.app.rest.mapper;

import java.util.Collection;

import airport.app.dao.model.Flight;
import airport.app.rest.dto.FlightDTO;

public interface FlightDTOMapper {

	public Collection<FlightDTO> mapToFlightDTO(Collection<Flight> flights);
}
