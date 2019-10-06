package com.klyte.places.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDTO {
    private String errorTitle;
    private String description;
    private Integer statusCode;
}
