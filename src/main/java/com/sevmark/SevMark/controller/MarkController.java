package com.sevmark.SevMark.controller;

import java.util.List;

import com.sevmark.SevMark.services.MarkService;
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

import com.sevmark.SevMark.DTO.MarkDTO;
import com.sevmark.SevMark.model.Mark;


@RestController
@RequestMapping("/marks")
public class MarkController {

    @Autowired
    private MarkService markService;

    @GetMapping
    public ResponseEntity<List<MarkDTO>> getAllMarks() {
        List<MarkDTO> marks = markService.getAllMarks();
        return ResponseEntity.ok(marks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarkDTO> getMarkById(@PathVariable Long id) {
        MarkDTO mark = markService.getMarkById(id);
        if (mark != null) {
            return ResponseEntity.ok(mark);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MarkDTO> createMark(@RequestBody Mark mark) {
        MarkDTO createdMark = markService.createMark(mark);
        if (createdMark != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMark);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarkDTO> updateMark(@PathVariable Long id, @RequestBody Mark mark) {
        MarkDTO updatedMark = markService.updateMark(id, mark);
        if (updatedMark != null) {
            return ResponseEntity.ok(updatedMark);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable Long id) {
        markService.deleteMark(id);
        return ResponseEntity.noContent().build();
    }
}
