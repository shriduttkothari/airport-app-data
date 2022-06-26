package airport.app.dao.mapper;

import airport.app.dao.batch.model.FlightFromBatch;
import airport.app.dao.model.Flight;

public interface FlightMapper {

	public Flight mapFlightFromBatchToFlight(final FlightFromBatch flightFromBatch);
}
