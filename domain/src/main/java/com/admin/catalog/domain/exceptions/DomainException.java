package com.admin.catalog.domain.exceptions;

import com.admin.catalog.domain.validation.DefaultError;

import java.util.List;

public class DomainException extends RuntimeException {

    private final List<DefaultError> errors;

    private DomainException(final List<DefaultError> errors) {
        super("", null, true, false);
        this.errors = errors;
    }

    public static DomainException with(final List<DefaultError> errors) {
        return new DomainException(errors);
    }

    public List<DefaultError> getErrors() {
        return errors;
    }
}
