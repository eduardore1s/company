package com.hotmart.api.company.controller;

import com.hotmart.api.company.controller.vo.ErrorVo;
import com.hotmart.api.company.model.exception.GenericErrorException;
import com.hotmart.api.company.model.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandlerError {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorVo> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        final List<ErrorVo> errorsDto = new ArrayList<>();

        final List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        errors.forEach(e -> {
            final String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            final ErrorVo errorVo =
                    ErrorVo.builder().field(e.getField()).message(message).build();

            errorsDto.add(errorVo);
        });

        return errorsDto;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorVo handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ErrorVo.builder().field(exception.getField()).message(exception.getMessage()).build();
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(GenericErrorException.class)
    public ErrorVo handleGenericErrorException(GenericErrorException exception) {
        return ErrorVo.builder().field(exception.getField()).message(exception.getMessage()).build();
    }

}
