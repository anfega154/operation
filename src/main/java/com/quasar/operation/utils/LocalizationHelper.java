package com.quasar.operation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocalizationHelper {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource source) {
        LocalizationHelper.messageSource = source;
    }

    public static String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
