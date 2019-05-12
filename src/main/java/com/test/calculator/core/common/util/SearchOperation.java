package com.test.calculator.core.common.util;

import java.util.Arrays;

public enum SearchOperation {
    EQUALITY,
    NEGATION,
    GREATER_THAN,
    LESS_THAN,
    LIKE,
    STARTS_WITH,
    ENDS_WITH,
    CONTAINS,
    NUMBER_LESS_EQUAL_THAN,
    NUMBER_GREATER_EQUAL_THAN,
    COMPARABLE_LESS_EQUAL_THAN,
    COMPARABLE_GREATER_EQUAL_THAN,
    DATE_GREATER_EQUAL_THAN,
    DATE_LESS_EQUAL_THAN,
    IN,
    NOT_IN,
    IS_NULL,
    NOT_NULL,
    IS_EMPTY,
    IS_NULL_OR_EMPTY,
    IS_NOT_EMPTY;

    public static SearchOperation from(String operation) {
        return Arrays.stream(SearchOperation.values()).filter(
                searchOperation -> searchOperation.name().equalsIgnoreCase(operation))
                .findFirst().orElse(SearchOperation.EQUALITY);
    }
}
