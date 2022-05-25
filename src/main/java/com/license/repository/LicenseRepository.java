package com.license.repository;

import com.license.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface LicenseRepository extends JpaRepository<License, UUID>, JpaSpecificationExecutor<License> {
}