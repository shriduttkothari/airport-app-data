package airport.app.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import airport.app.dao.exception.NoFlightAvailableException;
import airport.app.rest.dto.FlightDTO;
import airport.app.rest.service.FlightService;
import lombok.SneakyThrows;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlightService flightService;

	@Test
	@SneakyThrows
	public void test_flights_get_endpoint_returns_valid_body_and_valid_http_status_code() {

		FlightDTO flightDTO = FlightDTO.builder().departureTime(LocalTime.parse("19:20")).build();
		List<FlightDTO> flightDTOList = Collections.singletonList(flightDTO);

		LocalDate searchDate = LocalDate.of(2022, 06, 26);

		when(flightService.getFlightsForGivenDate(searchDate)).thenReturn(flightDTOList);

		RequestBuilder operation = get("/flights?for=2022-06-26");
		ResultActions resultActions = this.mockMvc.perform(operation);

		resultActions.andDo(print());
		resultActions.andExpect(status().is(200));
		resultActions.andExpect(jsonPath("[0].departureTime").value("19:20"));
	}

	@Test
	@SneakyThrows
	public void test_flights_get_endpoint_returns_valid_body_and_valid_http_status_code1() {

		LocalDate searchDate = LocalDate.of(2022, 06, 26);

		when(flightService.getFlightsForGivenDate(searchDate)).thenThrow(new NoFlightAvailableException());

		RequestBuilder operation = get("/flights?for=2022-06-26");
		ResultActions resultActions = this.mockMvc.perform(operation);

		resultActions.andDo(print());
		resultActions.andExpect(status().is(404));
		resultActions.andExpect(jsonPath("$.errorMessage").value("No Flight Available"));
	}
}
