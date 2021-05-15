package com.valorant.api.controller;

import com.valorant.api.model.Map;
import com.valorant.api.service.MapService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/maps")
public class MapController {

    @Autowired
    private MapService service;

    @GetMapping
    public ResponseEntity<?> allMaps(){
        List<Map> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Maps Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mapById(@PathVariable Integer id){
        Map map = service.findById(id);
        if (map == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Map "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    //@PostMapping
    public ResponseEntity<?> createMap(@RequestBody Map map){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(map));
    }

    //@PutMapping("/{id}")
    public ResponseEntity<?> updateMap(@RequestBody Map map, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(map, id));
    }
}
