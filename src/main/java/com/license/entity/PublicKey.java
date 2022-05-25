package com.license.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublicKey {
    private final String value;
}
