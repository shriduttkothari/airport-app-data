package airport.app.dao.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import airport.app.dao.batch.model.FlightFromBatch;
import airport.app.dao.mapper.FlightMapper;
import airport.app.dao.model.Flight;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CsvFlightItemProcessor implements ItemProcessor<FlightFromBatch, Flight> {

	private FlightMapper csvFlightMapper;

	@Override
	public Flight process(final FlightFromBatch flight) throws Exception {
		return csvFlightMapper.mapFlightFromBatchToFlight(flight);
	}

}