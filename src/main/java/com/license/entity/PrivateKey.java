package com.license.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrivateKey {
    private final String value;
}
