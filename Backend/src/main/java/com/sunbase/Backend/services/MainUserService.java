package com.sunbase.Backend.services;

import com.sunbase.Backend.customExceptions.UserNotFoundException;
import com.sunbase.Backend.dtos.request.UserRequest;
import com.sunbase.Backend.dtos.response.UserResponse;
import com.sunbase.Backend.models.User;
import com.sunbase.Backend.respositories.UserRepo;
import com.sunbase.Backend.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainUserService implements UserService {
    final UserRepo userRepository;
    @Autowired
    public MainUserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse addUser(UserRequest userRequestDto) {
        User user = UserTransformer.userFromUserRequestDto(userRequestDto);
        User savedUser = userRepository.save(user);
        UserResponse userResponseDto = UserTransformer.userResponseDtoFromUser(savedUser);
        userResponseDto.setMessage("User data has been saved Successfully!!");
        return userResponseDto;
    }

    @Override
    public UserResponse updateUser(int id, UserRequest userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            UserResponse dto = addUser(userRequestDto);
            dto.setMessage("Invalid ID. New account has been created!");
            return dto;
        }
        User user = optionalUser.get();
        user = UserTransformer.userFromUserDto(user, userRequestDto);
        User savedUser = userRepository.save(user);
        UserResponse responseDto = UserTransformer.userResponseDtoFromUser(savedUser);
        responseDto.setMessage("User data has been updated successfully!");
        return responseDto;
    }

    @Override
    public UserResponse deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new UserNotFoundException("Unable to find the user");
        userRepository.deleteById(id);
        User user = optionalUser.get();
        UserResponse responseDto = UserTransformer.userResponseDtoFromUser(user);
        responseDto.setMessage("User deleted Successfully!!");
        return responseDto;
    }

    @Override
    public List<UserResponse> getUsersBy(String search, String value) {
        List<User> userList;
        switch (search) {
            case "city": {
                userList = userRepository.findByCity(value);
                break;
            }
            case "phone": {
                userList = userRepository.findByPhone(value);
                break;
            }
            case "first": {
                userList = userRepository.findByFirstName(value);
                break;
            }
            case "email": {
                userList = userRepository.findByEmail(value);
                break;
            }
            default: {
                userList=userRepository.findAll();
            }
        }

        List<UserResponse> userResponseDtos = userList.stream()
                .map(ele -> UserTransformer.userResponseDtoFromUser(ele))
                .collect(Collectors.toList());

        return userResponseDtos;
    }

    @Override
    public UserResponse getUser(int id) {
        Optional<User>optionalUser=userRepository.findById(id);
        if(optionalUser.isEmpty())throw new UserNotFoundException("Unable to find the user");

        User user=optionalUser.get();
        UserResponse dto=UserTransformer.userResponseDtoFromUser(user);
        dto.setMessage("User Found");
        return dto;
    }
}
