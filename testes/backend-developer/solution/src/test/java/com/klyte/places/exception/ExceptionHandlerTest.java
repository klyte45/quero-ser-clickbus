package com.klyte.places.exception;

import com.klyte.places.dto.ErrorDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ExceptionHandlerTest {

    @Autowired
    private RestExceptionHandler exceptionHandler;

    @Test
    public void testPlaceNotFoundException() {
        PlaceNotFoundException ex = new PlaceNotFoundException("message");
        ResponseEntity<Object> response = RestExceptionHandler.handlePlaceNotFoundException(ex);
        assertErrorDTO((ErrorDTO) Objects.requireNonNull(response.getBody()), ex, ex.getStatusCode());
    }

    @Test
    public void testGenericException() {
        DummyException ex = new DummyException();
        ResponseEntity<Object> response = RestExceptionHandler.handleGenericException(ex);
        assertErrorDTO((ErrorDTO) Objects.requireNonNull(response.getBody()), ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private static void assertErrorDTO(ErrorDTO dto, Exception ex, HttpStatus status) {
        assertEquals(dto.getDescription(), ex.getMessage());
        assertEquals(dto.getErrorTitle(), ex.getClass().getSimpleName());
        assertEquals(dto.getStatusCode(), (Integer) status.value());
    }

}
