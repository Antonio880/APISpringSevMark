package com.sevmark.SevMark.controller;

import com.sevmark.SevMark.DTO.SportDTO;
import com.sevmark.SevMark.model.Sport;
import com.sevmark.SevMark.services.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sports")
public class SportController {

    @Autowired
    private SportService service;

    @GetMapping
    private ResponseEntity<List<SportDTO>> getSports(){
        List<SportDTO> sports = service.getAllSports();
        return ResponseEntity.ok(sports);
    }

    @PostMapping
    private ResponseEntity<SportDTO> postSport(Sport entity){
        return ResponseEntity.status(201).body(service.createSport(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSport(@PathVariable Long id) {
        service.deleteSport(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
