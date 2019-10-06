package com.klyte.places.exception;

import org.springframework.http.HttpStatus;

public interface IHttpStatusCodable {
    HttpStatus getStatusCode();
}
