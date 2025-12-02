package com.example.democatalog.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookValidationTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void whenAllFieldsAreValid() {
        var book = new Book("1234567890", "title", "author", 10.2);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertEquals(0, violations.size());
    }

    @Test
    void whenIsbnIsInvalid() {
        var book = new Book("123", "title", "author", 10.2);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertEquals(1, violations.size());
        ConstraintViolation<Book> isbnViolation = violations.iterator().next();
        assertEquals("The ISBN format must be valid", isbnViolation.getMessage());
    }
}