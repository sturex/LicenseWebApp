package com.license.graphql;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeyPairInput {
    private final String name;
}
