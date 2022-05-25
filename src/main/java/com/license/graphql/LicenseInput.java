package com.license.graphql;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LicenseInput {
    private final List<FeatureInput> features;
    private final long expires;
    private final String name;
}
