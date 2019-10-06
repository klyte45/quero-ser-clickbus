package com.klyte.places.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klyte.places.dto.PlaceDTO;
import com.klyte.places.dto.PlaceRequestDTO;
import com.klyte.places.entities.PlaceEntity;
import com.klyte.places.exception.PlaceNotFoundException;
import com.klyte.places.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<PlaceDTO> listPlaces() {
        List<PlaceDTO> result = new ArrayList<>();
        repository.findAll().forEach((p) -> result.add(mapper.convertValue(p, PlaceDTO.class)));
        return result;
    }

    @Override
    public PlaceDTO getPlace(String slug) {
        PlaceEntity place = repository.findByUrlSlug(slug);

        if (place == null) {
            throw new PlaceNotFoundException(String.format("Slug '%s' was not found.", slug));
        }

        return mapper.convertValue(place, PlaceDTO.class);
    }

    @Override
    public PlaceDTO updatePlace(String slug, PlaceRequestDTO data) {
        PlaceEntity place = repository.findByUrlSlug(slug);
        if (place == null) {
            throw new PlaceNotFoundException(String.format("Slug '%s' was not found.", slug));
        }

        place.setCity(data.getCity());
        place.setName(data.getName());
        place.setState(data.getState());
        place.setUrlSlug(data.getUrlSlug());

        place = repository.save(place);

        return mapper.convertValue(place, PlaceDTO.class);
    }

    @Override
    public void deletePlace(String slug) {
        repository.deleteByUrlSlug(slug);
    }

    @Override
    public PlaceDTO createPlace(PlaceRequestDTO data) {
        PlaceEntity place = new PlaceEntity();

        place.setCity(data.getCity());
        place.setName(data.getName());
        place.setState(data.getState());
        place.setUrlSlug(data.getUrlSlug());

        place = repository.save(place);

        return getPlace(place.getUrlSlug());
    }
}
