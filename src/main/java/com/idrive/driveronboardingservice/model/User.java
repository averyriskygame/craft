package com.idrive.driveronboardingservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.idrive.driveronboardingservice.model.type.UserStatus;
import com.idrive.driveronboardingservice.model.type.UserType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "User")
@Getter
@Setter

public class User {

    @Id
    @DynamoDBHashKey
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @DynamoDBTypeConvertedEnum
    private UserType userType;
    @DynamoDBTypeConvertedEnum
    private UserStatus userStatus;

}
