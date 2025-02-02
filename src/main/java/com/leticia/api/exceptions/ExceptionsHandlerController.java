package com.leticia.api.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlerController {

    private MessageSource messageSource;

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
//            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
//        });
//    }


}
