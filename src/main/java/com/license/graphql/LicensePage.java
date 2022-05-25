package com.license.graphql;

import com.license.entity.KeyPair;
import com.license.entity.License;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LicensePage {
    private final List<License> licenses;
    private final PageInfo pageInfo;
}
