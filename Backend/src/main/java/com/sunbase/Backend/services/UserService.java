package com.sunbase.Backend.services;

import com.sunbase.Backend.dtos.request.UserRequest;
import com.sunbase.Backend.dtos.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse addUser(UserRequest userRequestDto);
    UserResponse updateUser(int id,UserRequest userRequestDto);
    UserResponse deleteUser(int id);
    List<UserResponse>getUsersBy(String search,String value);
    UserResponse getUser(int id);
}