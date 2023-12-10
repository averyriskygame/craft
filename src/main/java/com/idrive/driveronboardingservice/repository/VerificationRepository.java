package com.idrive.driveronboardingservice.repository;

import com.idrive.driveronboardingservice.model.Verification;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface VdataRepo extends CrudRepository<Verification, String> {

    Optional<Verification> findById(String id);

    Optional<Verification> findByUserId(String userId);
}
