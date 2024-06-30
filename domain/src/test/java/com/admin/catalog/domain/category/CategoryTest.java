package com.admin.catalog.domain.category;

import com.admin.catalog.domain.exceptions.DomainException;
import com.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallANewCategory_thenCreateACategory() {
        final var expectedName = "Test";
        final var expectedDescription = "Test";
        final var expectedIsActive = true;

        var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(category.getName(), expectedName);
        Assertions.assertEquals(category.getDescription(), expectedDescription);
        Assertions.assertEquals(category.getIsActive(), expectedIsActive);
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullParams_whenCallANewCategoryAndValidate_thenShouldThrowAnError() {
        final String expectedName = null;
        final var expectedDescription = "Test";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            category.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals("'name' should not be null", exception.getErrors().getFirst().message());
        Assertions.assertEquals(1, exception.getErrors().size());
    }
}
