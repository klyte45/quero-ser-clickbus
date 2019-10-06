package com.klyte.places.controllers;

import com.klyte.places.dto.PlaceDTO;
import com.klyte.places.dto.PlaceRequestDTO;
import com.klyte.places.services.PlaceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/places", produces = "application/json")
public class PlacesController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    @ApiOperation("List all places stored")
    public ResponseEntity<List<PlaceDTO>> list(
            @ApiParam("Filter the list and brings only that contains this string in its name.")
            @RequestParam(value = "name", required = false) Optional<String> name

    ) {
        return new ResponseEntity<>(placeService.listPlaces(name.orElse("")), HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Place not found"),
    })
    @ApiOperation("Get a specific place by its slug")
    public ResponseEntity<PlaceDTO> get(@PathVariable("slug") String urlSlug) {
        return new ResponseEntity<>(placeService.getPlace(urlSlug), HttpStatus.OK);
    }

    @PutMapping("/{slug}")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Place not found"),
            @ApiResponse(code = 400, message = "Invalid data sent"),
    })
    @ApiOperation("Update a place data")
    public ResponseEntity<PlaceDTO> update(@PathVariable("slug") String urlSlug, @RequestBody @Valid PlaceRequestDTO place) {
        return new ResponseEntity<>(placeService.updatePlace(urlSlug, place), HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid data sent"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a new place")
    public ResponseEntity<PlaceDTO> create(@RequestBody @Valid PlaceRequestDTO place) {
        return new ResponseEntity<>(placeService.createPlace(place), HttpStatus.CREATED);
    }

}
