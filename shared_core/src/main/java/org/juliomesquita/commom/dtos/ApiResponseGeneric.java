package org.juliomesquita.commom.dtos;

import java.util.List;
import java.util.Map;

public record ApiResponseGeneric<T>(
        Map<String, Object> summary,
        List<T> data,
        PaginationGeneric pagination
) {
}
