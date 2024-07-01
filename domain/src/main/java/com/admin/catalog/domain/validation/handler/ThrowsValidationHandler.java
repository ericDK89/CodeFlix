package com.admin.catalog.domain.validation.handler;


import com.admin.catalog.domain.exceptions.DomainException;
import com.admin.catalog.domain.validation.DefaultError;
import com.admin.catalog.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(DefaultError error) {
        throw DomainException.with(List.of(error));
    }

    @Override
    public ValidationHandler append(ValidationHandler handler) {
        return null;
    }

    @Override
    public ValidationHandler validate(Validation validation) {
        return null;
    }

    @Override
    public List<DefaultError> getErrors() {
        return List.of();
    }
}
