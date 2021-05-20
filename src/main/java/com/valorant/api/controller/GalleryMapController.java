package com.valorant.api.controller;

import com.valorant.api.model.GalleryMap;
import com.valorant.api.service.GalleryMapService;
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
@RequestMapping("/map-gallery")
public class GalleryMapController {

    @Autowired
    private GalleryMapService service;

    @GetMapping
    @ApiOperation(value = "Get All images from Map Gallery")
    public ResponseEntity<?> allMapGallery(){
        List<GalleryMap> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Map Gallery Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Image by ID from Map Gallery")
    public ResponseEntity<?> galleryMapById(@PathVariable Integer id){
        GalleryMap galleryMap = service.findById(id);
        if (galleryMap == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gallery Map "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(galleryMap);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    @ApiOperation(value = "Save new Image to Map Gallery")
    public ResponseEntity<?> create(@RequestBody GalleryMap galleryMap){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(galleryMap));
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Image by ID from Map Gallery")
    public ResponseEntity<?> update(@RequestBody GalleryMap galleryMap, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(galleryMap, id));
    }
}
