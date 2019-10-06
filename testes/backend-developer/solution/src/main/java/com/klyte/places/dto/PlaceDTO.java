package com.klyte.places.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO {
    @NotBlank
    private String name;
    @NotBlank
    @JsonProperty("slug")
    private String urlSlug;
    @NotBlank
    private String city;
    @NotBlank
    private String state;

    private ZonedDateTime creationDate;

    private ZonedDateTime updateDate;
}
