package com.valorant.api.controller;

import com.valorant.api.model.Arsenal;
import com.valorant.api.service.ArsenalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/arsenal")
public class ArsenalController {

    @Autowired
    private ArsenalService service;

    @GetMapping
    public ResponseEntity<?> allArsenal(){
        List<Arsenal> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Arsenal Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> arsenalById(@PathVariable Integer id){
        Arsenal arsenal = service.findById(id);
        if (arsenal == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arsenal "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(arsenal);
    }

    //@PostMapping
    public ResponseEntity<?> createArsenal(@RequestBody Arsenal arsenal){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(arsenal));
    }

    //@PutMapping("/{id}")
    public ResponseEntity<?> updateArsenal(@RequestBody Arsenal arsenal, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(arsenal, id));
    }
}
