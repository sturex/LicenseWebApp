package com.license.graphql;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@Data
@Builder
public class Pagination {
    public static final int DEFAULT_PAGE_SIZE = 100;
    private final int pageSize;
    private final int pageNumber;

    public static PageRequest toPageRequest(Pagination pagination) {
        return Optional.ofNullable(pagination)
                .map(p -> PageRequest.of(p.getPageNumber(), p.getPageSize()))
                .orElse(PageRequest.ofSize(DEFAULT_PAGE_SIZE));
    }
}
