package com.valorant.api.controller;

import com.valorant.api.model.Ability;
import com.valorant.api.service.AbilityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/abilities")
public class AbilityController {

    @Autowired
    private AbilityService service;

    @GetMapping
    public ResponseEntity<?> allAbilities(){
        List<Ability> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Abilities Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> abilityById(@PathVariable Integer id){
        Ability ability = service.findById(id);
        if (ability == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ability "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(ability);
    }

    //@PostMapping
    public ResponseEntity<?> createAbility(@RequestBody Ability ability){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(ability));
    }

    //@PutMapping("/{id}")
    public ResponseEntity<?> updateAbility(@RequestBody Ability ability, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(ability, id));
    }
}
