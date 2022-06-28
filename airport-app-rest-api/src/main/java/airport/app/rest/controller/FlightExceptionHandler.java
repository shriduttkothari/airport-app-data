package airport.app.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import airport.app.dao.exception.NoFlightAvailableException;
import airport.app.rest.dto.ErrorResponseDTO;

@ControllerAdvice
public class FlightExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex) {
		ErrorResponseDTO error = new ErrorResponseDTO("Internal Server Error");
		return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoFlightAvailableException.class)
	public final ResponseEntity<ErrorResponseDTO> handleNoFlightAvailableException(NoFlightAvailableException ex) {
		ErrorResponseDTO error = new ErrorResponseDTO("No Flight Available");
		return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.NOT_FOUND);
	}
}
