package com.license.graphql;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageInfo {
    private final int pageSize;
    private final int pageNumber;
    private final int totalPages;
}
