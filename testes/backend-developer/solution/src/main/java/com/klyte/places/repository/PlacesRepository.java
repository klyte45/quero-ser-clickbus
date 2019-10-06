package com.klyte.places.repository;

import com.klyte.places.entities.Places;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesRepository extends CrudRepository<Places, Long> {}
