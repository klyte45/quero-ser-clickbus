package com.klyte.places.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@ApiModel
public class PlaceRequestDTO {
    @Pattern(regexp = ".{1,120}")
    @ApiModelProperty(value = "Place name", required = true, dataType = "java.lang.String", example = "Stet test")
    private String name;
    @Pattern(regexp = "[a-z0-9\\-]{1,120}")
    @ApiModelProperty(value = "Location's URL unique slug", required = true, dataType = "java.lang.String", example = "stet-test")
    private String urlSlug;
    @Pattern(regexp = ".{1,120}")
    @ApiModelProperty(value = "City name", required = true, dataType = "java.lang.String", example = "Stet")
    private String city;
    @Pattern(regexp = "[A-Z]{2}")
    @ApiModelProperty(value = "State acronym", required = true, dataType = "java.lang.String", example = "AS")
    private String state;
}
