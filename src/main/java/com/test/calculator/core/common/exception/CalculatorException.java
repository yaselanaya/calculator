package com.test.calculator.core.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CalculatorException extends Exception {

    private HttpStatus httpStatus;

    public CalculatorException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.httpStatus = httpStatus;
    }

    public CalculatorException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
