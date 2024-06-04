package com.riwi.surveylance.infraestructure.abstract_services;

import com.riwi.surveylance.api.dto.request.UserRequest;
import com.riwi.surveylance.api.dto.response.UserResponse;

public interface IUserService extends CRUDService<UserRequest, UserResponse, Integer>{
    
}
