package com.klyte.places.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class PlaceRequestDTO {
    @Pattern(regexp = ".{1,120}")
    private String name;
    @Pattern(regexp = "[a-z0-9\\-]{1,120}")
    private String urlSlug;
    @Pattern(regexp = ".{1,120}")
    private String city;
    @Pattern(regexp = "[A-Z]{2}")
    private String state;
}
