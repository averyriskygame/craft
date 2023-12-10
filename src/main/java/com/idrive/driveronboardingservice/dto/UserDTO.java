package com.idrive.driveronboardingservice.dto;

import com.idrive.driveronboardingservice.model.type.UserStatus;
import com.idrive.driveronboardingservice.model.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDTO {

    @NotBlank
    private String userId;

    @NotBlank(message = "First Name can not be empty")
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;

    private UserType userType;

    private UserStatus userStatus;
}
