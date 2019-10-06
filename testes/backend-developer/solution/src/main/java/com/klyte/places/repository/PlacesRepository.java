package com.klyte.places.repository;

import com.klyte.places.entities.PlaceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesRepository extends CrudRepository<PlaceEntity, Long> {
    PlaceEntity findByUrlSlug(String slug);

    PlaceEntity deleteByUrlSlug(String slug);
}
