package com.test.calculator.core.common.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

    private String field;

    private SearchOperation operator;

    private Object value;

    public boolean isValid() {
        return (Objects.nonNull(field) && !field.isEmpty())
                && (Objects.nonNull(value) && !value.toString().isEmpty());
    }
}
