package com.riwi.surveylance.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.surveylance.api.dto.request.QuestionPatch;
import com.riwi.surveylance.api.dto.request.QuestionRequest;
import com.riwi.surveylance.api.dto.response.QuestionResponse;
import com.riwi.surveylance.infraestructure.abstract_services.IQuestionService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/questions")
@AllArgsConstructor
public class QuestionController {
    @Autowired
    private final IQuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<QuestionResponse>> getAll(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        page = page - 1;
        return ResponseEntity.ok(this.questionService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(this.questionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> create(@Validated @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(this.questionService.create(request));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<QuestionResponse> update(@PathVariable int id, @Validated @RequestBody QuestionPatch request) {
        return ResponseEntity.ok(this.questionService.update(id, request));
    }

    @PutMapping("/{id}/options")
    public ResponseEntity<QuestionResponse> updateFull(@PathVariable int id, @Validated @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(this.questionService.updateFull(id, request));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.questionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
