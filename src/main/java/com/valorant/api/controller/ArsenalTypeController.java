package com.valorant.api.controller;

import com.valorant.api.model.ArsenalType;
import com.valorant.api.service.ArsenalTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/arsenal-type")
public class ArsenalTypeController {

    @Autowired
    private ArsenalTypeService service;

    @GetMapping
    public ResponseEntity<?> allArsenalType(){
        List<ArsenalType> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Arsenal Types Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> arsenalTypeById(@PathVariable Integer id){
        ArsenalType arsenalType = service.findById(id);
        if (arsenalType == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arsenal Type "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(arsenalType);
    }

    //@PostMapping
    public ResponseEntity<?> createArsenalType(@RequestBody ArsenalType arsenalType){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(arsenalType));
    }

    //@PutMapping("/{id}")
    public ResponseEntity<?> updateArsenalType(@RequestBody ArsenalType arsenalType, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(arsenalType, id));
    }
}
