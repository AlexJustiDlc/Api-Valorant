package com.valorant.api.controller;

import com.valorant.api.model.ArsenalType;
import com.valorant.api.service.ArsenalTypeService;
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
@RequestMapping("/arsenal-type")
public class ArsenalTypeController {

    @Autowired
    private ArsenalTypeService service;

    @GetMapping
    @ApiOperation(value = "Get All Arsenal Type")
    public ResponseEntity<?> allArsenalType(){
        List<ArsenalType> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Arsenal Types Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Arsenal Type by ID")
    public ResponseEntity<?> arsenalTypeById(@PathVariable Integer id){
        ArsenalType arsenalType = service.findById(id);
        if (arsenalType == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arsenal Type "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(arsenalType);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    @ApiOperation(value = "Create new Arsenal Type")
    public ResponseEntity<?> createArsenalType(@RequestBody ArsenalType arsenalType){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(arsenalType));
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Arsenal Type by ID")
    public ResponseEntity<?> updateArsenalType(@RequestBody ArsenalType arsenalType, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(arsenalType, id));
    }
}
