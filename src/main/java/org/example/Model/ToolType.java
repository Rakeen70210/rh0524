package org.example.Model;

/**
 * Represents the different types of tools available
 * @author rakeen huq
 */
public enum ToolType {
    Chainsaw(1.49, true, false, true),
    Ladder(1.99, true, true, false),
    Jackhammer(2.99, true, false, false);


    private final double dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    /**
     * Constructor for the ToolType enum
     * @param dailyCharge the daily charge for the tool
     * @param weekdayCharge whether the tool is chargeable on weekdays
     * @param weekendCharge whether the tool is chargeable on weekends
     * @param holidayCharge whether the tool is chargeable on holidays
     */
    ToolType(double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    /**
     * Get the daily charge for the tool
     * @return the daily charge for the tool
     */
    public double getDailyCharge() {
        return dailyCharge;
    }

    /**
     * Check if the tool is chargeable on weekdays
     * @return true if the tool is chargeable on weekdays, false otherwise
     */
    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    /**
     * Check if the tool is chargeable on weekends
     * @return true if the tool is chargeable on weekends, false otherwise
     */
    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    /**
     * Check if the tool is chargeable on holidays
     * @return true if the tool is chargeable on holidays, false otherwise
     */
    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}