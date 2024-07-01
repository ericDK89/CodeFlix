package com.admin.catalog.domain.category;

import com.admin.catalog.domain.validation.DefaultError;
import com.admin.catalog.domain.validation.ValidationHandler;
import com.admin.catalog.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler validationHandler) {
        super(validationHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkName();
    }

    private void checkName() {
        final var name = this.category.getName();

        final var MAX_LENGTH_NAME = 255;
        final var MIN_LENGTH_NAME = 3;

        if (name == null) {
            this.validatorHandler().append(new DefaultError("'name' should not be null"));
            return;
        }

        if (name.isEmpty()) {
            this.validatorHandler().append(new DefaultError("'name' should not be empty"));
            return;
        }

        if (name.isBlank()) {
            this.validatorHandler().append(new DefaultError("'name' should not be blank"));
            return;
        }

        if (name.length() > MAX_LENGTH_NAME || name.trim().length() < MIN_LENGTH_NAME) {
            this.validatorHandler().append(new DefaultError("'name' must be between 3 and 255 characters"));
        }
    }
}
