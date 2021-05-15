package com.valorant.api.controller;

import com.valorant.api.model.Role;
import com.valorant.api.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<?> allRoles(){
        List<Role> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Roles Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> roleById(@PathVariable Integer id){
        Role role = service.findById(id);
        if (role == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    //@PostMapping
    public ResponseEntity<?> createRole(@RequestBody Role role){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(role));
    }

    //@PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@RequestBody Role role, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(role, id));
    }
}
