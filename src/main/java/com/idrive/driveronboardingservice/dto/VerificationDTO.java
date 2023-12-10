package com.idrive.driveronboardingservice.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.idrive.driveronboardingservice.model.type.VerificationStatus;
import com.idrive.driveronboardingservice.model.type.VerificationType;
import com.idrive.driveronboardingservice.model.type.VerificationWorkFlowState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationDTO {

    private String verificationId;

    private String userId;

    private String docId;

    private int retryCount;

    private VerificationStatus verificationStatus;

    private VerificationWorkFlowState verificationWorkFlowState;

    private VerificationType verificationType;

}

