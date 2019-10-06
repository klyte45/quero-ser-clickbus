package com.klyte.places.repository;

import com.klyte.places.entities.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
    PlaceEntity findByUrlSlug(String slug);

    List<PlaceEntity> findAllByNameContaining(String name);

    void deleteByUrlSlug(String slug);
}
