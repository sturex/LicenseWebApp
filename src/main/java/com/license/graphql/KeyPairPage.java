package com.license.graphql;

import com.license.entity.KeyPair;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KeyPairPage {
    private final List<KeyPair> keyPairs;
    private final PageInfo pageInfo;
}
