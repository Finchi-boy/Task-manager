package dev.maciejfrackiewicz.task_manager.category;

import dev.maciejfrackiewicz.task_manager.category.dto.CreateCategoryRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateCategoryRequestTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldFailWhenNameIsBlank() {
        CreateCategoryRequest request = new CreateCategoryRequest("", "test");
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenNameIsNull() {
        CreateCategoryRequest request = new CreateCategoryRequest(null, "test");
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenNameIsTooLong() {
        CreateCategoryRequest request = new CreateCategoryRequest("12345678901234567890abcdef", "test");
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenColorUsBlank() {
        CreateCategoryRequest request = new CreateCategoryRequest("test", "");
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenColorIsNull() {
        CreateCategoryRequest request = new CreateCategoryRequest("test", null);
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldPass() {
        CreateCategoryRequest request = new CreateCategoryRequest("test", "test");
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }


}