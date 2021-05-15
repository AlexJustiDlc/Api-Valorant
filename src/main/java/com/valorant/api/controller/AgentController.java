package com.valorant.api.controller;

import com.valorant.api.model.Agent;
import com.valorant.api.service.AgentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentService service;

    @GetMapping
    public ResponseEntity<?> allAgents(){
        List<Agent> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Agents Records.");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> agentById(@PathVariable Integer id){
        Agent agent = service.findById(id);
        if (agent == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Agent "+id+" not found.");
        return ResponseEntity.status(HttpStatus.OK).body(agent);
    }

    //@PostMapping
    public ResponseEntity<?> createAgent(@RequestBody Agent agent){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(agent));
    }

    //@PutMapping("/{id}")
    public ResponseEntity<?> updateAgent(@RequestBody Agent agent, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(agent, id));
    }
}
