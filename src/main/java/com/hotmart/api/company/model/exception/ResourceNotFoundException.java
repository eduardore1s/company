package com.hotmart.api.company.model.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends Exception {

    private String field;
    private String message;

    public ResourceNotFoundException(String field, String message){
        super(message);
        this.field = field;
        this.message = message;
    }
}
