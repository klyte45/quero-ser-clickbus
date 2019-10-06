package com.klyte.places.controllers;

import com.klyte.places.dto.PlaceDTO;
import com.klyte.places.services.PlacesService;
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
    private PlacesService placesService;

    @GetMapping
    public ResponseEntity<List<PlaceDTO>> list() {
        return new ResponseEntity<>(placesService.listPlaces(), HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<PlaceDTO> get(@PathVariable("slug") String urlSlug) {
        return new ResponseEntity<>(placesService.getPlace(urlSlug), HttpStatus.OK);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<PlaceDTO> update(@PathVariable("slug") String urlSlug, @RequestBody @Valid PlaceDTO place) {
        return new ResponseEntity<>(placesService.updatePlace(urlSlug, place), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlaceDTO> create(@RequestBody @Valid PlaceDTO place) {
        return new ResponseEntity<>(placesService.createPlace(place), HttpStatus.CREATED);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<PlaceDTO> delete(@PathVariable("slug") String urlSlug) {
        placesService.deletePlace(urlSlug);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
