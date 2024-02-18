package com.sunbase.Backend.transformers;

import com.sunbase.Backend.dtos.request.UserRequest;
import com.sunbase.Backend.dtos.response.UserResponse;
import com.sunbase.Backend.models.User;

public class UserTransformer {
    public static User userFromUserRequestDto(UserRequest userRequestDto){
        return User.
                builder()
                .email(userRequestDto.getEmail())
                .phone(userRequestDto.getPhone())
                .state(userRequestDto.getState())
                .city(userRequestDto.getCity())
                .address(userRequestDto.getAddress())
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .street(userRequestDto.getStreet())
                .build();

    }
    public static UserResponse userResponseDtoFromUser(User user){
        return UserResponse.builder()
                .id(user.getId())
                .City(user.getCity())
                .address(user.getAddress())
                .email(user.getEmail())
                .phone(user.getPhone())
                .state(user.getState())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .street(user.getStreet())
                .build();
    }
    public static User userFromUserDto(User user,UserRequest dto){
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setStreet(user.getStreet());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setState(dto.getState());
        return user;

    }
}
