package com.license.exception;

import graphql.ErrorClassification;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LicenseErrorType implements ErrorClassification {
    GENERAL_ERROR("General error");

    private final String message;
}
