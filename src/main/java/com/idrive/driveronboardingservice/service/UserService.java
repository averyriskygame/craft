package com.idrive.driveronboardingservice.service;

import com.idrive.driveronboardingservice.dto.UserDTO;
import com.idrive.driveronboardingservice.exception.CustomException;
import com.idrive.driveronboardingservice.mapper.UserMapper;
import com.idrive.driveronboardingservice.model.User;
import com.idrive.driveronboardingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository UserRepository;

    public UserDTO getUserById(String id) throws Exception {
        Optional<User> userOptional = UserRepository.findById(id);
        if (userOptional.isPresent()) {
            User user=userOptional.get();
            return UserMapper.mapToUserDto(user);
        }
        throw new CustomException("user-not-found","User with id=%id not found", HttpStatus.NOT_FOUND);
    }

    public User saveUser(UserDTO userDTO) {
        User user= UserMapper.mapToUser(userDTO);
        return UserRepository.save(user);
    }

}
