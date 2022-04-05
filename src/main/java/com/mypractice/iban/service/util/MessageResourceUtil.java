package com.mypractice.iban.service.util;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class MessageResourceUtil {
    private final MessageSource messageSource;

    public String getMessage(final String key){
        return  messageSource.getMessage(key, null, Locale.getDefault());
    }
}
