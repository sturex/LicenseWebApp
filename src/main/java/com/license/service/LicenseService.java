package com.license.service;

import com.license.entity.KeyPair;
import com.license.entity.License;
import com.license.graphql.LicenseFilter;
import com.license.graphql.LicenseInput;
import com.license.graphql.LicensePage;
import com.license.graphql.Pagination;

public interface LicenseService {
    License createLicense(KeyPair keyPair, LicenseInput licenseInput);

    LicensePage licensePage(LicenseFilter licenseFilter, Pagination pagination);
}
