package com.klyte.places.services;

import com.klyte.places.dto.PlaceDTO;
import com.klyte.places.dto.PlaceRequestDTO;

import java.util.List;

public interface PlaceService {
    List<PlaceDTO> listPlaces();

    PlaceDTO getPlace(String slug);

    PlaceDTO updatePlace(String slug, PlaceRequestDTO data);

    void deletePlace(String slug);

    PlaceDTO createPlace(PlaceRequestDTO data);
}
