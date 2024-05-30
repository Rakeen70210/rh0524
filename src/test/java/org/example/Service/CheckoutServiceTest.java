package org.example.Service;

import org.example.Model.RentalAgreement;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutServiceTest {

    @Test
    public void testCheckoutServiceWithNoDiscount() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        int rentalDays = 6;
        String toolCode = "JAKD";
        int discountPercent = 0;

        CheckoutService checkoutService = new CheckoutService(checkoutDate, rentalDays, toolCode, discountPercent);
        RentalAgreement rentalAgreement = checkoutService.getRentalAgreement();

        assertEquals(toolCode, rentalAgreement.getToolCode().name());
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(checkoutDate.plusDays(rentalDays), rentalAgreement.getDueDate());
        assertEquals(2.99, rentalAgreement.getDailyRentalCharge());
        assertEquals(4, rentalAgreement.getChargeDays());
        assertEquals(11.96, rentalAgreement.getPreDiscountCharge());
        assertEquals(0.00, rentalAgreement.getDiscountAmount());
        assertEquals(11.96, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testCheckoutServiceWithDiscount() {
        LocalDate checkoutDate = LocalDate.of(2023, 10, 1);
        int rentalDays = 5;
        String toolCode = "CHNS";
        int discountPercent = 20;

        CheckoutService checkoutService = new CheckoutService(checkoutDate, rentalDays, toolCode, discountPercent);
        RentalAgreement rentalAgreement = checkoutService.getRentalAgreement();

        assertEquals(toolCode, rentalAgreement.getToolCode().name());
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(checkoutDate.plusDays(rentalDays), rentalAgreement.getDueDate());
        assertEquals(1.49, rentalAgreement.getDailyRentalCharge());
        assertEquals(5, rentalAgreement.getChargeDays());
        assertEquals(7.45, rentalAgreement.getPreDiscountCharge());
        assertEquals(1.49, rentalAgreement.getDiscountAmount());
        assertEquals(5.96, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testCheckoutServiceWithWeekendAndHolidayCharges() {
        LocalDate checkoutDate = LocalDate.of(2023, 10, 1);
        int rentalDays = 7;
        String toolCode = "JAKD";
        int discountPercent = 10;

        CheckoutService checkoutService = new CheckoutService(checkoutDate, rentalDays, toolCode, discountPercent);
        RentalAgreement rentalAgreement = checkoutService.getRentalAgreement();

        assertEquals(toolCode, rentalAgreement.getToolCode().name());
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(checkoutDate.plusDays(rentalDays), rentalAgreement.getDueDate());
        assertEquals(2.99, rentalAgreement.getDailyRentalCharge());
        assertEquals(5, rentalAgreement.getChargeDays()); // Assuming 2 weekend days are not charged
        assertEquals(14.95, rentalAgreement.getPreDiscountCharge());
        assertEquals(1.50, rentalAgreement.getDiscountAmount());
        assertEquals(13.45, rentalAgreement.getFinalCharge());
    }
}