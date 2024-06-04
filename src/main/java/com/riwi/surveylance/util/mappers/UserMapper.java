package com.riwi.surveylance.util.mappers;

import com.riwi.surveylance.api.dto.request.UserRequest;
import com.riwi.surveylance.api.dto.response.UserResponse;
import com.riwi.surveylance.domain.entities.User;

public class UserMapper {
    
    public UserResponse entityToResponse(User entity){
        UserResponse response = UserResponse.builder()
                                .id(entity.getId())
                                .name(entity.getName())
                                .email(entity.getEmail())
                                .active(entity.isActive())
                                .build();
        return response;
    }

    public User requestToEntity(UserRequest request){
        User entity = User.builder()
                      .name(request.getName())
                      .email(request.getEmail())
                      .password(request.getPassword())
                      .active(request.isActive())
                      .build();

        return entity;
    }

}
