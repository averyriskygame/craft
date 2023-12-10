package com.idrive.driveronboardingservice.model.type;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

public enum VerificationStatus {
    IN_PROGRESS,
    COMPLETE,
    FAILED
}
