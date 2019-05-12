package com.test.calculator.core.common.util;

import com.google.common.collect.Maps;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.exception.CalculatorValidationException;
import com.test.calculator.core.common.message.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@ControllerAdvice
public class CalculatorErrorHandler {

    private final Messages messages;

    public CalculatorErrorHandler(Messages messages) {
        this.messages = messages;
    }

    @ExceptionHandler(CalculatorException.class)
    @ResponseBody
    public ResponseEntity<Map> processValidationError(CalculatorException exception) {
        HttpStatus httpStatus = exception.getHttpStatus();
        Map<String, Object> body = getResponseBody(exception.getHttpStatus(),
                exception.getMessage());
        body.put("error", httpStatus.getReasonPhrase());
        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(CalculatorValidationException.class)
    @ResponseBody
    public ResponseEntity<Map> processValidation(CalculatorValidationException exception) {
        HttpStatus httpStatus = exception.getHttpStatus();
        Map<String, Object> body = getResponseBody(httpStatus, exception.getMessage());
        body.put("validation", exception.getErrors());
        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map> processValidation(Exception exception) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, Object> body = getResponseBody(
                httpStatus, messages.getMessage("exception.internal.server.exception")
        );
        return ResponseEntity.status(httpStatus).body(body);
    }

    private Map<String, Object> getResponseBody(HttpStatus httpStatus, String message) {
        Map<String, Object> response = Maps.newHashMap();
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        response.put("status", httpStatus.value());
        response.put("message", message);
        return response;
    }
}
