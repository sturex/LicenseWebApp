package com.license.graphql;


import com.license.entity.KeyPair;
import com.license.repository.spec.KeyPairSpecification;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

@Data
@Builder
public class KeyPairFilter {
    private final String name;
    private final TimestampInterval createdBetween;

    public static Specification<KeyPair> toSpecification(KeyPairFilter keyPairFilter) {
        Specification<KeyPair> specification = KeyPairSpecification.emptySpec();
        return Optional.ofNullable(keyPairFilter)
                .map(filter -> specification
                        .and(KeyPairSpecification.nameContains(filter.name))
                        .and(KeyPairSpecification.createdAtBetween(filter.createdBetween)))
                .orElse(specification);
    }
}
