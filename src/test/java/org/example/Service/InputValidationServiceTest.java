package org.example.Service;

import org.example.Model.ToolCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputValidationServiceTest {

    private final InputValidationService validationService = new InputValidationService();

    @Test
    public void testValidateToolCode_ValidCode() {
        assertTrue(validationService.validateToolCode(ToolCode.JAKR.name()));
        assertTrue(validationService.validateToolCode(ToolCode.JAKD.name()));
        assertTrue(validationService.validateToolCode(ToolCode.CHNS.name()));
        assertTrue(validationService.validateToolCode(ToolCode.LADW.name()));

    }

    @Test
    public void testValidateToolCode_InvalidCode() {
        assertFalse(validationService.validateToolCode("JAK"));
    }

    @Test
    public void testValidateRentalDayCount_ValidCount() {
        assertTrue(validationService.validateRentalDayCount(1));
        assertTrue(validationService.validateRentalDayCount(10));
    }

    @Test
    public void testValidateRentalDayCount_InvalidCount() {
        assertFalse(validationService.validateRentalDayCount(0));
        assertFalse(validationService.validateRentalDayCount(-1));
    }

    @Test
    public void testValidateDiscountPercent_ValidPercent() {
        assertTrue(validationService.validateDiscountPercent(0));
        assertTrue(validationService.validateDiscountPercent(50));
        assertTrue(validationService.validateDiscountPercent(100));
    }

    @Test
    public void testValidateDiscountPercent_InvalidPercent() {
        assertFalse(validationService.validateDiscountPercent(-1));
        assertFalse(validationService.validateDiscountPercent(101));
    }
}