package airport.app.rest.controller;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import airport.app.rest.dto.FlightDTO;
import airport.app.rest.service.FlightService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FlightController {

	private final FlightService flightService;

	@GetMapping(path = "/flights")
	public ResponseEntity<Collection<FlightDTO>> getFlights(
			@RequestParam("for") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate searchDate) {
		Collection<FlightDTO> list = flightService.getFlightsForGivenDate(searchDate);
		return ResponseEntity.ok(list);
	}

}
