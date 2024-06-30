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
        if (this.category.getName() == null) {
            this.validatorHandler().append(new DefaultError("'name' should not be null"));
        }
    }
}
