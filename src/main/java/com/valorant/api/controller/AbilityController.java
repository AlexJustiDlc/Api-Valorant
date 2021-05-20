package com.valorant.api.controller;

import com.valorant.api.model.Ability;
import com.valorant.api.service.AbilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/abilities")
public class AbilityController {

    @Autowired
    private AbilityService service;

    @GetMapping
    @ApiOperation(value = "Get All Abilities")
    public ResponseEntity<?> allAbilities(){
        List<Ability> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Abilities Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Ability by ID")
    public ResponseEntity<?> abilityById(@PathVariable Integer id){
        Ability ability = service.findById(id);
        if (ability == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ability "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(ability);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    @ApiOperation(value = "Create new Ability")
    public ResponseEntity<?> createAbility(@RequestBody Ability ability){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(ability));
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Ability by ID")
    public ResponseEntity<?> updateAbility(@RequestBody Ability ability, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(ability, id));
    }
}
