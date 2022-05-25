package com.license.service;

import com.license.entity.KeyPair;
import com.license.entity.PrivateKey;
import com.license.graphql.KeyPairFilter;
import com.license.graphql.KeyPairInput;
import com.license.graphql.KeyPairPage;
import com.license.graphql.Pagination;

public interface KeyPairService {
    KeyPair createKeyPair(KeyPairInput keyPairInput);

    KeyPairPage keyPairPage(KeyPairFilter keyPairFilter, Pagination pagination);

    KeyPair findByPrivateKey(PrivateKey privateKey);
}
