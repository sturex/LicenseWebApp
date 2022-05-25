package com.license.service;

import com.license.entity.KeyPair;
import com.license.entity.License;
import com.license.exception.LicenseModuleException;
import com.license.graphql.*;
import com.license.repository.LicenseRepository;
import javax0.license3j.crypto.LicenseKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.lang.reflect.Modifier;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
public class LicenseServiceImpl implements LicenseService {

    public static final String SHA_512 = "SHA-512";
    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    @Transactional
    public License createLicense(KeyPair keyPair, LicenseInput licenseInput) {
        javax0.license3j.License license3j = new javax0.license3j.License();
        licenseInput.getFeatures().forEach(featureInput -> license3j.add(featureInput.getType().create(featureInput.getName(), featureInput.getValue())));
        byte[] bytes = Base64.getDecoder().decode(keyPair.getPrivateKey());
        try {
            LicenseKeyPair licenseKeyPair = LicenseKeyPair.Create.from(bytes, Modifier.PRIVATE);
            license3j.setExpiry(Date.from(Instant.ofEpochMilli(licenseInput.getExpires())));
            license3j.sign(licenseKeyPair.getPair().getPrivate(), SHA_512);
            String signature = Base64.getEncoder().encodeToString(license3j.getSignature());
            License license = License.builder()
                    .createdAt(Instant.now().toEpochMilli())
                    .name(licenseInput.getName())
                    .body(license3j.toString())
                    .keyPair(keyPair)
                    .signature(signature)
                    .build();
            return licenseRepository.save(license);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw LicenseModuleException.error("Could not generate license");
        }
    }

    @Override
    public LicensePage licensePage(LicenseFilter licenseFilter, Pagination pagination) {
        Specification<License> specification = LicenseFilter.toSpecification(licenseFilter);
        PageRequest pageRequest = Pagination.toPageRequest(pagination);
        Page<License> page = licenseRepository.findAll(specification, pageRequest.withSort(Sort.by("createdAt")));
        return LicensePage.builder()
                .licenses(page.toList())
                .pageInfo(PageInfo.builder()
                        .pageSize(page.getSize())
                        .totalPages(page.getTotalPages())
                        .pageNumber(page.getNumber())
                        .build())
                .build();
    }

}
