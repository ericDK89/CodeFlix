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

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(category.getName(), expectedName);
        Assertions.assertEquals(category.getDescription(), expectedDescription);
        Assertions.assertEquals(category.getIsActive(), expectedIsActive);
        Assertions.assertTrue(category.getIsActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAValidParamsWithoutDescription_whenCallANewCategory_thenCreateACategory() {
        final var expectedName = "Test";
        final var expectedIsActive = true;

        var category = Category.newCategory(expectedName, null, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(category.getName(), expectedName);
        Assertions.assertNull(category.getDescription());
        Assertions.assertEquals(category.getIsActive(), expectedIsActive);
        Assertions.assertTrue(category.getIsActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAValidParamsFalseIsActive_whenCallANewCategory_thenCreateACategory() {
        final var expectedName = "Test";
        final var expectedDescription = "Test";
        final var expectedIsActive = false;

        var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(category.getName(), expectedName);
        Assertions.assertEquals(category.getDescription(), expectedDescription);
        Assertions.assertEquals(category.getIsActive(), expectedIsActive);
        Assertions.assertFalse(category.getIsActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNotNull(category.getDeletedAt());
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

    @Test
    public void givenAnInvalidEmptyName_whenCallANewCategoryAndValidate_thenShouldThrowAnError() {
        final String expectedName = "";
        final var expectedDescription = "Test";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            category.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals("'name' should not be empty", exception.getErrors().getFirst().message());
        Assertions.assertEquals(1, exception.getErrors().size());
    }

    @Test
    public void givenAnInvalidBlankName_whenCallANewCategoryAndValidate_thenShouldThrowAnError() {
        final String expectedName = "  ";
        final var expectedDescription = "Test";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            category.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals("'name' should not be blank", exception.getErrors().getFirst().message());
        Assertions.assertEquals(1, exception.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallANewCategoryAndValidate_thenShouldThrowAnError() {
        final String expectedName = "Te ";
        final var expectedDescription = "Test";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            category.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals("'name' must be between 3 and 255 characters", exception.getErrors().getFirst().message());
        Assertions.assertEquals(1, exception.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallANewCategoryAndValidate_thenShouldThrowAnError() {
        final String expectedName = "Test".repeat(256);
        final var expectedDescription = "Test";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            category.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals("'name' must be between 3 and 255 characters", exception.getErrors().getFirst().message());
        Assertions.assertEquals(1, exception.getErrors().size());
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnAInactiveCategory() {
        final String expectedName = "Test";
        final var expectedDescription = "Test";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var oldUpdatedAt = category.getUpdatedAt();

        category.deactivate();

        final var newUpdatedAt = category.getUpdatedAt();

        Assertions.assertDoesNotThrow(ThrowsValidationHandler::new);
        Assertions.assertFalse(category.getIsActive());
        Assertions.assertNotNull(category.getDeletedAt());
        Assertions.assertNotEquals(category.getUpdatedAt(), oldUpdatedAt);
        Assertions.assertEquals(category.getUpdatedAt(), newUpdatedAt);
    }

    @Test
    public void givenAValidDeactivateCategory_whenCallActivate_thenReturnAActiveCategory() {
        final String expectedName = "Test";
        final var expectedDescription = "Test";
        final var expectedIsActive = false;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var oldUpdatedAt = category.getUpdatedAt();

        category.activate();

        final var newUpdatedAt = category.getUpdatedAt();

        Assertions.assertDoesNotThrow(ThrowsValidationHandler::new);
        Assertions.assertTrue(category.getIsActive());
        Assertions.assertNull(category.getDeletedAt());
        Assertions.assertNotEquals(category.getUpdatedAt(), oldUpdatedAt);
        Assertions.assertEquals(category.getUpdatedAt(), newUpdatedAt);
    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thenReturnAnUpdatedCategory() {
        final String oldName = "Test";
        final var oldDescription = "Test";
        final var oldIsActive = false;

        final var category = Category.newCategory(oldName, oldDescription, oldIsActive);
        final var oldUpdatedAt = category.getUpdatedAt();

        final var newName = "New Test";
        final var newDescription = "New Description";
        final var newIsActive = true;

        category.update(newName, newDescription, newIsActive);

        final var newUpdatedAt = category.getUpdatedAt();

        Assertions.assertDoesNotThrow(ThrowsValidationHandler::new);
        Assertions.assertEquals(category.getName(), newName);
        Assertions.assertEquals(category.getDescription(), newDescription);
        Assertions.assertNotEquals(category.getName(), oldName);
        Assertions.assertNotEquals(category.getDescription(), oldDescription);
        Assertions.assertEquals(category.getUpdatedAt(), newUpdatedAt);
        Assertions.assertNotEquals(category.getUpdatedAt(), oldUpdatedAt);
        Assertions.assertEquals(category.getIsActive(), newIsActive);
        Assertions.assertNotEquals(category.getIsActive(), oldIsActive);
    }

    @Test
    public void givenAValidCategory_whenCallUpdateToInactive_thenReturnAnUpdatedCategory() {
        final String oldName = "Test";
        final var oldDescription = "Test";
        final var oldIsActive = true;

        final var category = Category.newCategory(oldName, oldDescription, oldIsActive);
        final var oldUpdatedAt = category.getUpdatedAt();

        final var newName = "New Test";
        final var newDescription = "New Description";
        final var newIsActive = false;

        category.update(newName, newDescription, newIsActive);

        final var newUpdatedAt = category.getUpdatedAt();

        Assertions.assertDoesNotThrow(ThrowsValidationHandler::new);
        Assertions.assertEquals(category.getName(), newName);
        Assertions.assertEquals(category.getDescription(), newDescription);
        Assertions.assertNotEquals(category.getName(), oldName);
        Assertions.assertNotEquals(category.getDescription(), oldDescription);
        Assertions.assertEquals(category.getUpdatedAt(), newUpdatedAt);
        Assertions.assertNotEquals(category.getUpdatedAt(), oldUpdatedAt);
        Assertions.assertEquals(category.getIsActive(), newIsActive);
        Assertions.assertNotEquals(category.getIsActive(), oldIsActive);
        Assertions.assertFalse(category.getIsActive());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategory() {
        final String oldName = "Test";
        final var oldDescription = "Test";
        final var oldIsActive = true;

        final var category = Category.newCategory(oldName, oldDescription, oldIsActive);
        final var oldUpdatedAt = category.getUpdatedAt();

        final String newName = null;
        final var newDescription = "New Description";
        final var newIsActive = false;

        category.update(newName, newDescription, newIsActive);

        final var newUpdatedAt = category.getUpdatedAt();

        Assertions.assertDoesNotThrow(ThrowsValidationHandler::new);
        Assertions.assertEquals(category.getName(), newName);
        Assertions.assertEquals(category.getDescription(), newDescription);
        Assertions.assertNotEquals(category.getName(), oldName);
        Assertions.assertNotEquals(category.getDescription(), oldDescription);
        Assertions.assertEquals(category.getUpdatedAt(), newUpdatedAt);
        Assertions.assertNotEquals(category.getUpdatedAt(), oldUpdatedAt);
        Assertions.assertEquals(category.getIsActive(), newIsActive);
        Assertions.assertNotEquals(category.getIsActive(), oldIsActive);
        Assertions.assertFalse(category.getIsActive());
    }
}
