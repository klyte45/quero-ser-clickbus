package com.klyte.places.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String urlSlug;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
}
