package com.riwi.surveylance.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.surveylance.api.dto.request.UserRequest;
import com.riwi.surveylance.api.dto.response.UserResponse;
import com.riwi.surveylance.domain.entities.User;
import com.riwi.surveylance.domain.repositories.UserRepository;
import com.riwi.surveylance.infraestructure.abstract_services.IUserService;
import com.riwi.surveylance.util.exceptions.IdNotFoundException;
import com.riwi.surveylance.util.mappers.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService extends UserMapper implements IUserService{

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if(page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.userRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public UserResponse create(UserRequest request) {
        User userToSave = this.requestToEntity(request);
        User userSaved = this.userRepository.save(userToSave);
        UserResponse response = this.entityToResponse(userSaved);

        return response;
    }

    @Override
    public UserResponse update(Integer id, UserRequest request) {
        this.findById(id);

        User userToUpdate = this.requestToEntity(request);
        userToUpdate.setId(id);
        User userUpdated = this.userRepository.save(userToUpdate);
        UserResponse response = this.entityToResponse(userUpdated);

        return response;
    }

    @Override
    public void delete(Integer id) {
        User userToDelete = this.findById(id);
        this.userRepository.delete(userToDelete);
    }

    @Override
    public UserResponse getById(Integer id) {
        return this.entityToResponse(this.findById(id));
    }

    // UTILS

    private User findById(Integer id){
        return this.userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("User", id));
    }
    
}
