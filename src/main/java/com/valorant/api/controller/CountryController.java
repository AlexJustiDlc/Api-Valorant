package com.valorant.api.controller;

import com.valorant.api.model.Country;
import com.valorant.api.service.CountryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService service;

    @GetMapping
    public ResponseEntity<?> allCountries(){
        List<Country> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Countries Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> countryById(@PathVariable Integer id){
        Country country = service.findById(id);
        if (country == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Country "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(country);
    }

    //@PostMapping
    public ResponseEntity<?> createCountry(@RequestBody Country country){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(country));
    }

    //@PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(@RequestBody Country country, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(country, id));
    }
}
