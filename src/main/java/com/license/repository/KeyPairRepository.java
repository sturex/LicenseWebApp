package com.license.repository;

import com.license.entity.KeyPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface KeyPairRepository extends JpaRepository<KeyPair, UUID>, JpaSpecificationExecutor<KeyPair> {
    Optional<KeyPair> findByPrivateKey(String privateKey);
}