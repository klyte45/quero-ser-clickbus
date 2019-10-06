package com.klyte.places.util;

import com.klyte.places.dto.PlaceDTO;
import com.klyte.places.dto.PlaceRequestDTO;
import com.klyte.places.entities.PlaceEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class TestUtils {
    public static PlaceDTO createPlaceDTO(PlaceRequestDTO requestDTO) {
        return createPlaceDTO(requestDTO.getUrlSlug(), requestDTO.getName(), requestDTO.getCity(), requestDTO.getState());
    }

    public static PlaceDTO createPlaceDTO(String slug, String name, String city, String state) {
        PlaceDTO result = new PlaceDTO();
        result.setUrlSlug(slug);
        result.setState(state);
        result.setCity(city);
        result.setName(name);
        return result;
    }

    public static PlaceEntity createPlaceEntity(String slug, String name, String city, String state) {
        PlaceEntity result = new PlaceEntity();
        result.setUrlSlug(slug);
        result.setState(state);
        result.setCity(city);
        result.setName(name);
        return result;
    }

    public static PlaceDTO createRandomPlaceDTO() {
        String slug = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true).toLowerCase();
        String name = RandomStringUtils.random((int) (1 + Math.random() * 48), true, true);
        String city = RandomStringUtils.random((int) (1 + Math.random() * 88), true, true);
        String state = RandomStringUtils.random(2, true, false).toUpperCase();
        return createPlaceDTO(slug, name, city, state);
    }

    public static PlaceEntity createRandomPlaceEntity() {
        String slug = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true).toLowerCase();
        String name = RandomStringUtils.random((int) (1 + Math.random() * 48), true, true);
        String city = RandomStringUtils.random((int) (1 + Math.random() * 88), true, true);
        String state = RandomStringUtils.random(2, true, false).toUpperCase();
        return createPlaceEntity(slug, name, city, state);
    }
}
