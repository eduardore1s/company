package com.hotmart.api.company.model.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericErrorException extends RuntimeException {

    private String field;
    private String message;

    public GenericErrorException(String field, String message) {
        super(message);
        this.field = field;
        this.message = message;
    }
}
