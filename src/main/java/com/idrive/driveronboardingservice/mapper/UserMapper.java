package com.idrive.driveronboardingservice.mapper;

import com.idrive.driveronboardingservice.dto.UserDTO;
import com.idrive.driveronboardingservice.model.User;

public class UserMapper {

    // Convert User JPA Entity into UserDto
    public static UserDTO mapToUserDto(User user) {
        UserDTO userDto = new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType(),
                user.getUserStatus()
        );
        return userDto;
    }

    // Convert UserDto into User JPA Entity
    public static User mapToUser(UserDTO userDto) {
        User user = new User(
                userDto.getUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getUserType(),
                userDto.getUserStatus()
        );
        return user;
    }
}