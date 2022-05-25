package com.license.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@Builder
@NoArgsConstructor
public class LicenseModuleException extends RuntimeException implements GraphQLError {

    private LicenseErrorType errorType = LicenseErrorType.GENERAL_ERROR;
    private String message = "General error";

    private LicenseModuleException(LicenseErrorType errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return Collections.singletonList(SourceLocation.EMPTY);
    }

    @Override
    public ErrorClassification getErrorType() {
        return errorType;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return new HashMap<>();
    }

    public static LicenseModuleException error(String message) {
        return LicenseModuleException.builder()
                .message(message)
                .build();
    }
    public static LicenseModuleException error() {
        return LicenseModuleException.builder()
                .build();
    }

}
