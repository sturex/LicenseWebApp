package com.license.service;

import com.license.entity.KeyPair;
import com.license.entity.PrivateKey;
import com.license.exception.LicenseModuleException;
import com.license.graphql.*;
import com.license.repository.KeyPairRepository;
import javax0.license3j.crypto.LicenseKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

@Service
public class KeyPairServiceImpl implements KeyPairService {

    public static final int KEY_SIZE = 2048;
    public static final String ALGORITHM = "RSA";

    private final KeyPairRepository keyPairRepository;

    @Autowired
    public KeyPairServiceImpl(KeyPairRepository keyPairRepository) {
        this.keyPairRepository = keyPairRepository;
    }

    @Override
    @Transactional
    public KeyPair createKeyPair(KeyPairInput keyPairInput) {
        try {
            LicenseKeyPair keyPair = LicenseKeyPair.Create.from(ALGORITHM, KEY_SIZE);
            KeyPair pair = KeyPair.builder()
                    .createdAt(Instant.now().toEpochMilli())
                    .privateKey(Base64.getEncoder().encodeToString(keyPair.getPrivate()))
                    .publicKey(Base64.getEncoder().encodeToString(keyPair.getPublic()))
                    .name(keyPairInput.getName())
                    .licenses(new ArrayList<>())
                    .build();
            return keyPairRepository.save(pair);
        } catch (NoSuchAlgorithmException e) {
            throw LicenseModuleException.error("The cryptographic algorithm is requested but is not available in the environment");
        }
    }

    @Override
    public KeyPairPage keyPairPage(KeyPairFilter keyPairFilter, Pagination pagination) {
        Specification<KeyPair> specification = KeyPairFilter.toSpecification(keyPairFilter);
        PageRequest pageRequest = Pagination.toPageRequest(pagination);
        Page<KeyPair> page = keyPairRepository.findAll(specification, pageRequest.withSort(Sort.by("createdAt")));
        return KeyPairPage.builder()
                .keyPairs(page.toList())
                .pageInfo(PageInfo.builder()
                        .pageSize(page.getSize())
                        .totalPages(page.getTotalPages())
                        .pageNumber(page.getNumber())
                        .build())
                .build();
    }

    @Override
    public KeyPair findByPrivateKey(PrivateKey privateKey) {
        return keyPairRepository.findByPrivateKey(privateKey.getValue())
                .orElseThrow(() -> LicenseModuleException.error("KeyPair not found"));
    }
}
