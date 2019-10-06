package com.klyte.places.exception;

import com.klyte.places.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlaceNotFoundException.class)
    static ResponseEntity<Object> handlePlaceNotFoundException(PlaceNotFoundException ex) {
        log.info("Exception returned", ex);
        return generateDefaultErrorDTO(ex, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    static ResponseEntity<Object> handleGenericException(Exception ex) {
        log.error("Exception returned", ex);
        return generateDefaultErrorDTO(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    static ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.warn("Exception returned", ex);
        if (ex.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
            SQLIntegrityConstraintViolationException violationException = (SQLIntegrityConstraintViolationException) ex.getRootCause();
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setDescription(violationException.getLocalizedMessage());
            errorDTO.setErrorTitle(violationException.getClass().getSimpleName());
            errorDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        return generateDefaultErrorDTO(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.info("Exception returned", exception);
        return generateDefaultErrorDTO(exception, status);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception returned", ex);
        return generateDefaultErrorDTO(ex, status);
    }

    private static ResponseEntity<Object> generateDefaultErrorDTO(Throwable ex, HttpStatus status) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDescription(ex.getMessage());
        errorDTO.setErrorTitle(ex.getClass().getSimpleName());
        errorDTO.setStatusCode(status.value());
        return new ResponseEntity<>(errorDTO, status);
    }
}
