package com.klyte.places.exception;

import com.klyte.places.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlaceNotFoundException.class)
    static ResponseEntity<Object> handlePlaceNotFoundException(PlaceNotFoundException ex) {
        return generateDefaultErrorDTO(ex, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    static ResponseEntity<Object> handleGenericException(Exception ex) {
        return generateDefaultErrorDTO(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        return generateDefaultErrorDTO(exception, status);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return generateDefaultErrorDTO(ex, status);
    }

    private static ResponseEntity<Object> generateDefaultErrorDTO(Exception ex, HttpStatus status) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDescription(ex.getMessage());
        errorDTO.setErrorTitle(ex.getClass().getSimpleName());
        errorDTO.setStatusCode(status.value());
        return new ResponseEntity<>(errorDTO, status);
    }
}
