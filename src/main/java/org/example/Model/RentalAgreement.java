package org.example.Model;

import java.time.LocalDate;

/**
 * The RentalAgreement class is a model class that represents the rental agreement
 * The Rental Agreement is printed through the CheckoutView class
 * @author rakeen huq
 */
public class RentalAgreement {
    private final ToolCode toolCode;
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
     * @param toolCode            the tool code
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
    public RentalAgreement(ToolCode toolCode, ToolType toolType,
                           ToolBrand toolBrand, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, double dailyRentalCharge, int chargeDays, double preDiscountCharge, int discountPercent, double discountAmount, double finalCharge) {
        this.toolCode = toolCode;
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

    /**
     * Returns the tool code
     *
     * @return the tool code
     */
    public ToolCode getToolCode() {
        return toolCode;
    }

    /**
     * Returns the tool type
     *
     * @return the tool type
     */
    public ToolType getToolType() {
        return toolType;
    }

    /**
     * Returns the tool brand
     *
     * @return the tool brand
     */
    public ToolBrand getToolBrand() {
        return toolBrand;
    }

    /**
     * Returns the rental days
     *
     * @return the rental days
     */
    public int getRentalDays() {
        return rentalDays;
    }

    /**
     * Returns the checkout date
     *
     * @return the checkout date
     */
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * Returns the due date
     *
     * @return the due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns the daily rental charge
     *
     * @return the daily rental charge
     */
    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    /**
     * Returns the charge days
     *
     * @return the charge days
     */
    public int getChargeDays() {
        return chargeDays;
    }

    /**
     * Returns the pre discount charge
     *
     * @return the pre discount charge
     */
    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    /**
     * Returns the discount percent
     *
     * @return the discount percent
     */
    public int getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Returns the discount amount
     *
     * @return the discount amount
     */
    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Returns the final charge
     *
     * @return the final charge
     */
    public double getFinalCharge() {
        return finalCharge;
    }
}