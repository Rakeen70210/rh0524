package org.example.Service;

import org.example.Model.*;
import org.example.Repository.ToolMap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents the checkout process
 * 
 */
public class CheckoutService {
    private final WeekendHolidayService weekendHolidayService;
    private final RentalAgreement rentalAgreement;

    /**
     * Constructor for the checkout service
     * 
     * @param checkoutDate     the checkout date
     * @param rentalDays       the number of rental days
     * @param toolCode         the tool code
     * @param discountPercent  the discount percent
     */
    public CheckoutService(LocalDate checkoutDate, int rentalDays, String toolCode, int discountPercent) {
        this.weekendHolidayService = new WeekendHolidayService(checkoutDate, rentalDays);
        ToolMap toolMap = ToolMap.getInstance();
        Tool tool = toolMap.getTool(ToolCode.valueOf(toolCode));

        LocalDate dueDate = calculateDueDate(checkoutDate, rentalDays);
        ToolType toolType = tool.getToolType();
        ToolBrand toolBrand = tool.getToolBrand();
        double dailyRentalCharge = toolType.getDailyCharge();
        int chargeDays = calculateChargeDays(checkoutDate, dueDate, toolType);
        double preDiscountCharge = calculatePreDiscountCharge(chargeDays, dailyRentalCharge);
        double discountAmount = calculateDiscountAmount(discountPercent, preDiscountCharge);
        double finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);

        this.rentalAgreement = new RentalAgreement(
                ToolCode.valueOf(toolCode), toolType, toolBrand, rentalDays, checkoutDate, dueDate,
                dailyRentalCharge, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge
        );
    }

    /**
     * Get the rental agreement
     * 
     * @return the rental agreement
     */
    public RentalAgreement getRentalAgreement() {
        return rentalAgreement;
    }

    private double calculateFinalCharge(double preDiscountCharge, double discountAmount) {
        BigDecimal finalCharge = BigDecimal.valueOf(preDiscountCharge)
                .subtract(BigDecimal.valueOf(discountAmount))
                .setScale(2, RoundingMode.HALF_UP);
        return finalCharge.doubleValue();
    }

    /**
     * Calculate the due date based on the checkout date and rental days
     *
     * @param checkoutDate the start date of the rental
     * @param rentalDays   the number of rental days
     * @return the due date
     */
    private LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDays) {
        return checkoutDate.plusDays(rentalDays);
    }

    /**
     * Calculate the number of chargeable days
     * 
     * @param checkoutDate the start date of the rental
     * @param dueDate      the due date of the rental
     * @param toolType     the type of tool being rented
     * @return the number of chargeable days
     */
    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, ToolType toolType) {
        long totalDays = ChronoUnit.DAYS.between(checkoutDate, dueDate) + 1; // Include endDate

        int weekdays = weekendHolidayService.getWeekdayCount();
        int weekends = weekendHolidayService.getWeekendCount();
        int holidays = weekendHolidayService.getHolidayCount();

        if (!toolType.isWeekendCharge()) {
            totalDays -= weekends;
        }
        if (!toolType.isHolidayCharge()) {
            totalDays -= holidays;
        }
        if (!toolType.isWeekdayCharge()) {
            totalDays -= weekdays;
        }

        return (int) totalDays;
    }

    /**
     * Calculate the pre-discount charge
     *
     * @param chargeDays        the number of chargeable days
     * @param dailyRentalCharge the daily rental charge
     * @return the pre-discount charge
     */
    private double calculatePreDiscountCharge(int chargeDays, double dailyRentalCharge) {
        BigDecimal preDiscountCharge = BigDecimal.valueOf(dailyRentalCharge * chargeDays)
                .setScale(2, RoundingMode.HALF_UP);

        return preDiscountCharge.doubleValue();
    }

    /**
     * Calculate the discount amount
     *
     * @param discountPercent   the discount percent
     * @param preDiscountCharge the pre-discount charge
     * @return the discount amount
     */
    private double calculateDiscountAmount(int discountPercent, double preDiscountCharge) {
        BigDecimal discountAmount = BigDecimal.valueOf(preDiscountCharge)
                .multiply(BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100)))
                .setScale(2, RoundingMode.HALF_UP);

        return discountAmount.doubleValue();
    }
}