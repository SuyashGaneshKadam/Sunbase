package com.sunbase.Backend.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    int id;
    String firstName;
    String lastName;
    String address;
    String City;
    String state;
    String email;
    String phone;
    String message;
    String street;
}