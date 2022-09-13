package com.restaurant.stoke.stoke.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restaurant.stoke.stoke.entities.Stoke;
import com.restaurant.stoke.stoke.services.StokeService;

@RestController
@RequestMapping(value = "/stoke")
public class MainController {
    
    @Autowired
    private StokeService service;

    @PostMapping(value="/save")
    public ResponseEntity<Stoke> saveStoke(@RequestBody Stoke obj){
        obj = service.saveStoke(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping(value="/getAllStoke")
    public ResponseEntity<List<Stoke>> getAllStoke(){
        List<Stoke> obj = service.getAllStoke();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value="/getByIdStoke/{id}")
    public ResponseEntity<Stoke> getByIdStoke(@PathVariable Long id){
        Stoke obj = service.getByIdStoke(id);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value="/updateStoke/{id}")
    public ResponseEntity<Stoke> updateStoke(@PathVariable Long id, @RequestBody Stoke obj){
        obj = service.updateStoke(id, obj);
        return ResponseEntity.ok().body(obj);
    }


    @DeleteMapping(value="/deleteStoke/{id}")
    public ResponseEntity<Void> deleteStoke(@PathVariable Long id){
        service.deleteStoke(id);
        return ResponseEntity.noContent().build();
    }
}
