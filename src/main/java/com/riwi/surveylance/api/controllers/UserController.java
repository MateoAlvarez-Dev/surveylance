package com.riwi.surveylance.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.surveylance.api.dto.request.UserRequest;
import com.riwi.surveylance.api.dto.response.UserResponse;
import com.riwi.surveylance.infraestructure.abstract_services.IUserService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    
    @Autowired
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        page = page - 1;
        return ResponseEntity.ok(this.userService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserRequest request) {
        return ResponseEntity.ok(this.userService.create(request));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable int id, @Validated @RequestBody UserRequest request) {
        return ResponseEntity.ok(this.userService.update(id, request));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
