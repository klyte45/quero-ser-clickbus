package com.klyte.places.repository;

import com.klyte.places.entities.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
    PlaceEntity findByUrlSlug(String slug);

    void deleteByUrlSlug(String slug);
}
