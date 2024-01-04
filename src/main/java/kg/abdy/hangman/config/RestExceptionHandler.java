package kg.abdy.hangman.config;

import kg.abdy.hangman.dto.ExceptionResponse;
import kg.abdy.hangman.exceptions.BadRequestException;
import kg.abdy.hangman.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setCode("NOT_FOUND");
        response.setTimestamp(LocalDateTime.now());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(BadRequestException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setCode("BAD_REQUEST");
        response.setTimestamp(LocalDateTime.now());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

}
