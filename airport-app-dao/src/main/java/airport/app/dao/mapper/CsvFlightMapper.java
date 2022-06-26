package airport.app.dao.mapper;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import airport.app.dao.batch.model.FlightFromBatch;
import airport.app.dao.model.Flight;

@Component
public class CsvFlightMapper implements FlightMapper {

	public Flight mapFlightFromBatchToFlight(final FlightFromBatch flightFromBatch) {
		if (flightFromBatch == null) {
			return null;
		}

		final Flight transformedFlight = Flight.builder()
				.departureTime(LocalTime.parse(flightFromBatch.getDepartureTime()))
				.destination(flightFromBatch.getDestination())
				.destinationAirportIATA(flightFromBatch.getDestinationAirportIATA())
				.flightNo(flightFromBatch.getFlightNo())
				.availableOnDayOfWeek(buildAvailableOnDayOfWeek(flightFromBatch)).build();

		return transformedFlight;

	}

	private Set<DayOfWeek> buildAvailableOnDayOfWeek(final FlightFromBatch flightFromBatch) {
		final Set<DayOfWeek> availableOnDayOfWeek = new HashSet<>();
		if (isAvaialbleOn(flightFromBatch.getSunday())) {
			availableOnDayOfWeek.add(DayOfWeek.SUNDAY);
		}
		if (isAvaialbleOn(flightFromBatch.getMonday())) {
			availableOnDayOfWeek.add(DayOfWeek.MONDAY);
		}
		if (isAvaialbleOn(flightFromBatch.getTuesday())) {
			availableOnDayOfWeek.add(DayOfWeek.TUESDAY);
		}
		if (isAvaialbleOn(flightFromBatch.getWednesday())) {
			availableOnDayOfWeek.add(DayOfWeek.WEDNESDAY);
		}
		if (isAvaialbleOn(flightFromBatch.getThursday())) {
			availableOnDayOfWeek.add(DayOfWeek.THURSDAY);
		}
		if (isAvaialbleOn(flightFromBatch.getFriday())) {
			availableOnDayOfWeek.add(DayOfWeek.FRIDAY);
		}
		if (isAvaialbleOn(flightFromBatch.getSaturday())) {
			availableOnDayOfWeek.add(DayOfWeek.SATURDAY);
		}

		return availableOnDayOfWeek;
	}

	private boolean isAvaialbleOn(String value) {
		if ("x".equals(value)) {
			return true;
		} {
			return false;
		}
	}

}
