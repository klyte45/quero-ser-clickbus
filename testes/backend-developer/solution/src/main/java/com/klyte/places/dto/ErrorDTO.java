package com.klyte.places.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDTO {
    @ApiModelProperty(value = "Exception name", example = "Exception")
    private String errorTitle;
    @ApiModelProperty(value = "Exception detail", example = "Exception has occurred")
    private String description;
    @ApiModelProperty(value = "Status code generated", example = "500")
    private Integer statusCode;
}
