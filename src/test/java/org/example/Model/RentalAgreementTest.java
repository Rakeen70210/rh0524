package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalAgreementTest {
    private final ToolCode toolCode = ToolCode.CHNS;
    private final ToolType toolType = ToolType.Ladder;
    private final ToolBrand toolBrand = ToolBrand.Werner;
    private final int rentalDays = 3;
    private final LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
    private final LocalDate dueDate = LocalDate.of(2020, 7, 5);
    private final double dailyRentalCharge = 1.99;
    private final int chargeDays = 2;
    private final double preDiscountCharge = 3.98;
    private final int discountPercent = 10;
    private final double discountAmount = 0.40;
    private final double finalCharge = 3.58;
    private RentalAgreement rentalAgreement;

    @BeforeEach
    void setUp() {
        rentalAgreement = new RentalAgreement(toolCode, toolType, toolBrand, rentalDays, checkoutDate, dueDate,
                dailyRentalCharge, chargeDays, preDiscountCharge, discountPercent,
                discountAmount, finalCharge);
    }

    @Test
    void getToolCode() {
        assertEquals(toolCode, rentalAgreement.getToolCode());
    }

    @Test
    void getToolType() {
        assertEquals(toolType, rentalAgreement.getToolType());
    }

    @Test
    void getToolBrand() {
        assertEquals(toolBrand, rentalAgreement.getToolBrand());
    }

    @Test
    void getRentalDays() {
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
    }

    @Test
    void getCheckoutDate() {
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
    }

    @Test
    void getDueDate() {
        assertEquals(dueDate, rentalAgreement.getDueDate());
    }

    @Test
    void getDailyRentalCharge() {
        assertEquals(dailyRentalCharge, rentalAgreement.getDailyRentalCharge());
    }

    @Test
    void getChargeDays() {
        assertEquals(chargeDays, rentalAgreement.getChargeDays());
    }

    @Test
    void getPreDiscountCharge() {
        assertEquals(preDiscountCharge, rentalAgreement.getPreDiscountCharge());
    }

    @Test
    void getDiscountPercent() {
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
    }

    @Test
    void getDiscountAmount() {
        assertEquals(discountAmount, rentalAgreement.getDiscountAmount());
    }

    @Test
    void getFinalCharge() {
        assertEquals(finalCharge, rentalAgreement.getFinalCharge());
    }
}