package com.license.controller;

import com.license.entity.KeyPair;
import com.license.entity.License;
import com.license.entity.PrivateKey;
import com.license.entity.PublicKey;
import com.license.graphql.*;
import com.license.service.KeyPairService;
import com.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQlController {

    private final KeyPairService keyPairService;
    private final LicenseService licenseService;

    @Autowired
    public GraphQlController(KeyPairService keyPairService,
                             LicenseService licenseService) {
        this.keyPairService = keyPairService;
        this.licenseService = licenseService;
    }

    @MutationMapping("keyPair")
    public KeyPair keyPair(@Argument KeyPairInput keyPairInput) {
        return keyPairService.createKeyPair(keyPairInput);
    }

    @SchemaMapping(typeName = "KeyPair", field = "publicKey")
    public PublicKey publicKey(KeyPair keyPair) {
        return PublicKey.builder().value(keyPair.getPublicKey()).build();
    }

    @SchemaMapping(typeName = "KeyPair", field = "privateKey")
    public PrivateKey privateKey(KeyPair keyPair) {
        return PrivateKey.builder().value(keyPair.getPrivateKey()).build();
    }

    @SchemaMapping(typeName = "PrivateKey", field = "license")
    public License license(PrivateKey privateKey, @Argument LicenseInput licenseInput) {
        KeyPair keyPair = keyPairService.findByPrivateKey(privateKey);
        return licenseService.createLicense(keyPair, licenseInput);
    }

    @QueryMapping
    public KeyPairPage keyPairPage(@Argument KeyPairFilter keyPairFilter,
                                @Argument Pagination pagination) {
        return keyPairService.keyPairPage(keyPairFilter, pagination);
    }

    @QueryMapping
    public LicensePage licensePage(@Argument LicenseFilter licenseFilter,
                                @Argument Pagination pagination) {
        return licenseService.licensePage(licenseFilter, pagination);
    }

}
