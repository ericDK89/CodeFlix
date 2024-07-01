package com.admin.catalog.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(DefaultError error);

    ValidationHandler append(ValidationHandler handler);

    ValidationHandler validate(Validation validation);

    List<DefaultError> getErrors();

    default boolean hasErrors() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    public interface Validation {
        void validate();
    }
}
