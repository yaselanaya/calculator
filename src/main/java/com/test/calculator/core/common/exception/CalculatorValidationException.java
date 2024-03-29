package com.test.calculator.core.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter
public class CalculatorValidationException extends CalculatorException{

    private List<Map<String, Object>> errors;

    public CalculatorValidationException(HttpStatus httpStatus, List<Map<String, Object>> errors) {
        super(httpStatus);
        this.errors = errors;
    }

}
