package com.valorant.api.controller;

import com.valorant.api.model.GalleryMap;
import com.valorant.api.service.GalleryMapService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/map-gallery")
public class GalleryMapController {

    @Autowired
    private GalleryMapService service;

    @GetMapping
    public ResponseEntity<?> allMapGallery(){
        List<GalleryMap> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Map Gallery Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> galleryMapById(@PathVariable Integer id){
        GalleryMap galleryMap = service.findById(id);
        if (galleryMap == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gallery Map "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(galleryMap);
    }

    //@PostMapping
    public ResponseEntity<?> create(@RequestBody GalleryMap galleryMap){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(galleryMap));
    }

    //@PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody GalleryMap galleryMap, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(galleryMap, id));
    }
}
