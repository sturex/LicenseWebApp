package com.license.graphql;


import com.license.entity.KeyPair;
import com.license.entity.License;
import com.license.repository.spec.KeyPairSpecification;
import com.license.repository.spec.LicenseSpecification;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

@Data
@Builder
public class LicenseFilter {
    private final String name;
    private final TimestampInterval createdBetween;

    public static Specification<License> toSpecification(LicenseFilter licenseFilter) {
        Specification<License> specification = LicenseSpecification.emptySpec();
        return Optional.ofNullable(licenseFilter)
                .map(filter -> specification
                        .and(LicenseSpecification.nameContains(filter.name))
                        .and(LicenseSpecification.createdAtBetween(filter.createdBetween)))
                .orElse(specification);
    }
}
