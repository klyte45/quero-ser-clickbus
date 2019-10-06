package com.klyte.places.dto;

import com.klyte.places.exception.IHttpStatusCodable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDTO {
    private String errorTitle;
    private String description;
    private Integer statusCode;

    public <T extends Exception & IHttpStatusCodable> ErrorDTO(T e) {
        errorTitle = e.getClass().getSimpleName();
        description = e.getMessage();
        statusCode = e.getStatusCode().value();
    }
}
