package com.klyte.places.services;

import com.klyte.places.dto.PlaceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacesServiceImpl implements PlacesService {

    @Override
    public List<PlaceDTO> listPlaces() {
        return null;
    }

    @Override
    public PlaceDTO getPlace(String slug) {
        return null;
    }

    @Override
    public PlaceDTO updatePlace(String slug, PlaceDTO data) {
        return null;
    }

    @Override
    public void deletePlace(String slug) {
    }

    @Override
    public PlaceDTO createPlace(PlaceDTO data) {
        return null;
    }
}
