package com.license.graphql;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimestampInterval {
    private final long begin;
    private final long end;
}
