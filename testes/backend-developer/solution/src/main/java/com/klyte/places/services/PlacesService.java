package com.klyte.places.services;

import com.klyte.places.dto.PlaceDTO;

import java.util.List;

public interface PlacesService {
    List<PlaceDTO> listPlaces();

    PlaceDTO getPlace(String slug);

    PlaceDTO updatePlace(String slug, PlaceDTO data);

    void deletePlace(String slug);

    PlaceDTO createPlace(PlaceDTO data);
}
