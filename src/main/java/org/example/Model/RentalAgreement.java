package org.example.Model;

import java.time.LocalDate;

/**
 * The RentalAgreement class is a model class that represents the rental agreement
 * @author rakeen huq
 */
public class RentalAgreement {
    private final ToolType toolType;
    private final ToolBrand toolBrand;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final double dailyRentalCharge;
    private final int chargeDays;
    private final double preDiscountCharge;
    private final int discountPercent;
    private final double discountAmount;
    private final double finalCharge;

    /**
     * Constructor for the RentalAgreement class
     * @param toolType            the tool type
     * @param toolBrand           the tool brand
     * @param rentalDays          the number of rental days
     * @param checkoutDate         the checkout date
     * @param dueDate              the due date
     * @param dailyRentalCharge    the daily rental charge
     * @param chargeDays           the charge days
     * @param preDiscountCharge    the pre discount charge
     * @param discountPercent      the discount percent
     * @param discountAmount       the discount amount
     * @param finalCharge          the final charge
     */
    public RentalAgreement(ToolType toolType,
     ToolBrand toolBrand, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, double dailyRentalCharge, int chargeDays, double preDiscountCharge, int discountPercent, double discountAmount, double finalCharge) {
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public ToolBrand getToolBrand() {
        return toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }
}