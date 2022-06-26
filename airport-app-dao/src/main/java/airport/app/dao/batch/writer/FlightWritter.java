package airport.app.dao.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import airport.app.dao.model.Flight;
import airport.app.dao.repository.FlightRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FlightWritter implements ItemWriter<Flight> {

	private FlightRepository flightRepository;

	@Override
	public void write(List<? extends Flight> items) throws Exception {
		flightRepository.saveFlights(items);
	}

}
