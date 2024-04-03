package com.sevmark.SevMark.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevmark.SevMark.DTO.AvailableTimesDTO;
import com.sevmark.SevMark.model.AvailableTimes;
import com.sevmark.SevMark.services.AvailableTimesService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/available-times")
public class AvailableTimesController {
    
    @Autowired
    private AvailableTimesService service;

    @GetMapping
    public ResponseEntity<List<AvailableTimesDTO>> getAvailableTimes() {
       List<AvailableTimesDTO> availableTimes = service.getAllAvailableTimes();
       return ResponseEntity.ok(availableTimes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAvailableTimesById(@PathVariable Long id) {
        AvailableTimesDTO availableTimesDTO = service.getAvailableTimesById(id);
        if (availableTimesDTO != null) {
            return ResponseEntity.ok(availableTimesDTO); // Retornar o DTO se encontrado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horários Disponíveis não encontrados para o ID fornecido"); // Retornar 404 com mensagem personalizada
        }
    }

    @PostMapping
    public ResponseEntity<List<AvailableTimesDTO>> postAvailableTimes(@RequestBody List<AvailableTimes> entities) {
        List<AvailableTimesDTO> createdAvailableTimes = new ArrayList<>();
        entities.forEach(a -> createdAvailableTimes.add(service.postAvailableTimes(a)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAvailableTimes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAvailableTime(@PathVariable Long id, @RequestBody AvailableTimes entity) {
        AvailableTimesDTO updatedAvailableTime = service.updateAvailableTimes(id, entity);
        if (updatedAvailableTime != null) {
            return ResponseEntity.ok(updatedAvailableTime); // Retornar o DTO atualizado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horário não encontrado para o ID fornecido"); // Retornar 404 com mensagem personalizada
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAvailableTime(@PathVariable Long id) {
        service.deleteAvailableTimes(id);
        return ResponseEntity.status(HttpStatus.OK).body("Horário Deletado com sucesso"); // Retornar 204 sem corpo de resposta
    }
}
