package com.idrive.driveronboardingservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.idrive.driveronboardingservice.model.type.VerificationStatus;
import com.idrive.driveronboardingservice.model.type.VerificationType;
import com.idrive.driveronboardingservice.model.type.VerificationWorkFlowState;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@DynamoDBTable(tableName = "Verification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Verification {

    @DynamoDBHashKey
    @Id
    private String verificationId;
    private String userId;
    private String docId;
    private int retryCount;

    @DynamoDBTypeConvertedEnum
    private VerificationStatus verificationStatus;

    @DynamoDBTypeConvertedEnum
    private VerificationWorkFlowState verificationWorkFlowState;

    @DynamoDBTypeConvertedEnum
    private VerificationType verificationType;


}
