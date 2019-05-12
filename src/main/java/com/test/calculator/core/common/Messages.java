package com.test.calculator.core.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Messages {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String id) {
        return messageSource.getMessage(id, null, id, LocaleContextHolder.getLocale());
    }

    public String getMessage(String id, Object[] params) {
        return messageSource.getMessage(id, params, id, LocaleContextHolder.getLocale());
    }
}
