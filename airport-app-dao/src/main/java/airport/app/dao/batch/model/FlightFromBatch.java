package airport.app.dao.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightFromBatch {

	private String departureTime;
	private String destination;
	private String destinationAirportIATA;
	private String flightNo;
	private String sunday;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
}
