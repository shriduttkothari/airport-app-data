package airport.app.dao.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight implements Comparable<Flight> {

	private LocalTime departureTime;
	private String destination;
	private String destinationAirportIATA;
	private String flightNo;
	private Set<DayOfWeek> availableOnDayOfWeek;

	@Override
	public int compareTo(Flight another) {
		return this.departureTime.compareTo(another.departureTime);
	}

}
