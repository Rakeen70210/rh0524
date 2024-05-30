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
 * @author rakeen huq
 */
public class CheckoutService {
    private WeekendHolidayService weekendHolidayService;
    private ToolMap toolMap;
    private Tool tool;
    private RentalAgreement rentalAgreement;

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
        this.toolMap = ToolMap.getInstance();
        this.tool = toolMap.getTool(ToolCode.valueOf(toolCode));

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        ToolType toolType = tool.getToolType();
        ToolBrand toolBrand = tool.getToolBrand();
        double dailyRentalCharge = toolType.getDailyCharge();
        int chargeDays = getChargeDays(checkoutDate, dueDate, toolType);
        double preDiscountCharge = getPreDiscountCharge(chargeDays, toolType);
        double discountAmount = getDiscountAmount(discountPercent, preDiscountCharge);
        double finalCharge = preDiscountCharge - discountAmount;

        this.rentalAgreement = new RentalAgreement(ToolCode.valueOf(toolCode), toolType, toolBrand, rentalDays, checkoutDate, dueDate,
                dailyRentalCharge, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);

    }

    /**
     * Get the rental agreement
     * 
     * @return the rental agreement
     */
    public RentalAgreement getRentalAgreement() {
        return rentalAgreement;
    }

    /**
     * Get the number of chargeable days
     * 
     * @param checkoutDate the start date of the rental
     * @param dueDate   the due date of the rental
     * @param toolType  the type of tool being rented
     * @return the number of chargeable days
     */
    private int getChargeDays(LocalDate checkoutDate, LocalDate dueDate, ToolType toolType) {

        long totalDays = ChronoUnit.DAYS.between(checkoutDate, dueDate) + 1; // Include endDate

        int weekdays = weekendHolidayService.getWeekdayCount();
        int weekends = weekendHolidayService.getWeekendCount();
        int holidays = weekendHolidayService.getHolidayCount();

        // calculate which days are chargeable
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
     * Calculated as charge days X daily charge. Resulting total rounded half up
     * to cents
     * 
     * @param chargeDays the number of chargeable days
     * @param toolType   the type of tool being rented
     * @return the pre-discount charge
     */
    private double getPreDiscountCharge(int chargeDays, ToolType toolType) {
        BigDecimal preDiscountCharge = BigDecimal.valueOf(toolType.getDailyCharge() * chargeDays)
                .setScale(2, RoundingMode.HALF_UP);

        return preDiscountCharge.doubleValue();

    }

    /**
     * calculated from discount % and pre-discount charge. Resulting amount
     * rounded half up to cents
     * 
     * @param discountPercent the discount percent
     * @return the discount amount
     */
    private double getDiscountAmount(int discountPercent, double preDiscountCharge) {
        BigDecimal discountAmount = BigDecimal.valueOf(preDiscountCharge)
                .multiply(BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100)))
                .setScale(2, RoundingMode.HALF_UP);

        return discountAmount.doubleValue();
    }

}
