package com.valorant.api.controller;

import com.valorant.api.model.Role;
import com.valorant.api.service.RoleService;
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
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    @ApiOperation(value = "Get All Roles")
    public ResponseEntity<?> allRoles(){
        List<Role> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Roles Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Role by ID")
    public ResponseEntity<?> roleById(@PathVariable Integer id){
        Role role = service.findById(id);
        if (role == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    @ApiOperation(value = "Create new Role")
    public ResponseEntity<?> createRole(@RequestBody Role role){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(role));
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Role by ID")
    public ResponseEntity<?> updateRole(@RequestBody Role role, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(role, id));
    }
}
