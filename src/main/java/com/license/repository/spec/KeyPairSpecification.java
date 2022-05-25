package com.license.repository.spec;

import com.license.entity.KeyPair;
import com.license.graphql.TimestampInterval;
import org.springframework.data.jpa.domain.Specification;

public enum KeyPairSpecification {
    ;

    public static Specification<KeyPair> emptySpec() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static Specification<KeyPair> nameContains(String chars) {
        return (root, query, criteriaBuilder) ->
                chars == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(root.get("name"), "%" + chars + "%");
    }

    public static Specification<KeyPair> createdAtBetween(TimestampInterval createdAtBetween) {
        return (root, query, criteriaBuilder) ->
                createdAtBetween == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.between(root.get("createdAt"), createdAtBetween.getBegin(), createdAtBetween.getEnd());
    }


}
