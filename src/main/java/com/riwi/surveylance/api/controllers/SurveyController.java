package com.riwi.surveylance.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.surveylance.api.dto.request.SurveyRequest;
import com.riwi.surveylance.api.dto.response.SurveyResponse;
import com.riwi.surveylance.api.dto.response.SurveyResponseFull;
import com.riwi.surveylance.infraestructure.abstract_services.ISurveyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/surveys")
@AllArgsConstructor
public class SurveyController {
    @Autowired
    private final ISurveyService surveyService;

    @GetMapping
    public ResponseEntity<Page<SurveyResponse>> getAll(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        page = page - 1;
        return ResponseEntity.ok(this.surveyService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponseFull> getById(@PathVariable int id) {
        return ResponseEntity.ok(this.surveyService.getByIdFull(id));
    }

    @PostMapping
    public ResponseEntity<SurveyResponse> create(@Validated @RequestBody SurveyRequest request) {
        return ResponseEntity.ok(this.surveyService.create(request));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponse> update(@PathVariable int id, @Validated @RequestBody SurveyRequest request) {
        return ResponseEntity.ok(this.surveyService.update(id, request));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.surveyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
