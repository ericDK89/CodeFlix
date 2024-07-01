package com.admin.catalog.domain.validation;


public abstract class Validator {

    private final ValidationHandler handler;

    protected Validator(final ValidationHandler handler) {
        this.handler = handler;
    }

    public abstract void validate();

    protected ValidationHandler validatorHandler() {
        return this.handler;
    }
}
