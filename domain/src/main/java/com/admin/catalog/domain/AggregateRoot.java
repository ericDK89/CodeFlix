package com.admin.catalog.domain;

import com.admin.catalog.domain.validation.ValidationHandler;

public class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
