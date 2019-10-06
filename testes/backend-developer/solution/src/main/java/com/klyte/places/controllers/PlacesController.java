package com.klyte.places.controllers;

import com.klyte.places.dto.PlaceDTO;
import com.klyte.places.dto.PlaceRequestDTO;
import com.klyte.places.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/places")
public class PlacesController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceDTO>> list() {
        return new ResponseEntity<>(placeService.listPlaces(), HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<PlaceDTO> get(@PathVariable("slug") String urlSlug) {
        return new ResponseEntity<>(placeService.getPlace(urlSlug), HttpStatus.OK);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<PlaceDTO> update(@PathVariable("slug") String urlSlug, @RequestBody @Valid PlaceRequestDTO place) {
        return new ResponseEntity<>(placeService.updatePlace(urlSlug, place), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlaceDTO> create(@RequestBody @Valid PlaceRequestDTO place) {
        return new ResponseEntity<>(placeService.createPlace(place), HttpStatus.CREATED);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<PlaceDTO> delete(@PathVariable("slug") String urlSlug) {
        placeService.deletePlace(urlSlug);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
