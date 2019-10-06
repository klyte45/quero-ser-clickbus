package com.klyte.places.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.security.Timestamp;
import java.time.ZonedDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String urlSlug;
    @NotBlank
    private String city;
    @NotBlank
    private String state;

    private ZonedDateTime creationDate;

    private ZonedDateTime updateDate;
}
