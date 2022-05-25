package com.license.graphql;

import com.license.entity.FeatureType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureInput {
    private final String name;
    private final String value;
    private final FeatureType type;
}
