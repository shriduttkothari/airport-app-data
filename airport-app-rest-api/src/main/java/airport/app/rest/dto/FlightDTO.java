package airport.app.rest.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightDTO implements Comparable<FlightDTO> {

	@JsonFormat(pattern = "HH:mm")
	private LocalTime departureTime;
	private String destination;
	private String destinationAirportIATA;
	private String flightNo;

	@Override
	public int compareTo(FlightDTO another) {
		return this.departureTime.compareTo(another.departureTime);
	}
}
