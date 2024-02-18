package com.sunbase.Backend.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    String firstName;
    String lastName;
    String street;
    String address;
    String City;
    String state;
    String email;
    String phone;
}
