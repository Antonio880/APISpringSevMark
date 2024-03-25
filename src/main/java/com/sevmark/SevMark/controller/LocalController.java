package com.sevmark.SevMark.controller;

import java.util.List;
import com.sevmark.SevMark.DTO.LocalDTO;
import com.sevmark.SevMark.model.Local;
import com.sevmark.SevMark.repository.LocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locals")
public class LocalController {

    @Autowired
    private LocalService service;

    @GetMapping
    public ResponseEntity<List<LocalDTO>> getUsers() {
       List<LocalDTO> locals = service.getAllLocals();
       return ResponseEntity.ok(locals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        LocalDTO localDTO = service.getLocalById(id);
        if (localDTO != null) {
            return ResponseEntity.ok(localDTO); // Retornar o DTO se encontrado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local não encontrado para o ID fornecido"); // Retornar 404 com mensagem personalizada
        }
    }

    @PostMapping
    public ResponseEntity<LocalDTO> postUser(@RequestBody Local entity) {
        LocalDTO createdLocal = service.postLocal(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Local entity) {
        LocalDTO updatedLocal = service.updateLocal(id, entity);
        if (updatedLocal != null) {
            return ResponseEntity.ok(updatedLocal); // Retornar o DTO atualizado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local não encontrado para o ID fornecido"); // Retornar 404 com mensagem personalizada
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        service.deleteLocal(id);
        return ResponseEntity.status(HttpStatus.OK).body("Local Deletado com sucesso"); // Retornar 204 sem corpo de resposta
    }
}