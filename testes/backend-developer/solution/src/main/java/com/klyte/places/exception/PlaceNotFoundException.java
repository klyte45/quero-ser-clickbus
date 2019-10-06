package com.klyte.places.exception;

import org.springframework.http.HttpStatus;

public class PlaceNotFoundException extends RuntimeException implements IHttpStatusCodable {

    public PlaceNotFoundException(String message) {
        super(message);
    }
    
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
