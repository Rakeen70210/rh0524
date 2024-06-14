package org.example.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Service class to calculate number of weekdays, weekends, and holidays
 *
 * @author rakeen huq
 */
public class WeekendHolidayService {
    private final LocalDate checkoutDate;
    private final LocalDate endDate;


    /**
     * Constructor for initializing checkout date and rental day count
     *
     * @param checkoutDate the checkout date
     * @param rentalDayCount the rental day count
     */
    public WeekendHolidayService(LocalDate checkoutDate, int rentalDayCount) {
        this.checkoutDate = checkoutDate;
        this.endDate = checkoutDate.plusDays(rentalDayCount);
    }

    /**
     * Calculate the number of weekdays in the date range
     *
     * @return the number of weekdays
     */
    public int getWeekdayCount() {
        int weekdays = 0;

        for (LocalDate currentDate = checkoutDate; currentDate.isBefore(endDate.plusDays(1)); currentDate = currentDate.plusDays(1)) {
            if (!isWeekend(currentDate) && !isLaborDay(currentDate) && !isIndependenceDay(currentDate)) {
                weekdays++;
            }
        }

        return weekdays;
    }


    /**
     * Get number of holidays
     *
     * @return the number of holidays
     */
    public int getHolidayCount(){
        int holidays = 0;

        // ignore checkoutDate as it is not counted as a rental day
        for (LocalDate currentDate = checkoutDate.plusDays(1); currentDate.isBefore(endDate.plusDays(1)); currentDate = currentDate.plusDays(1)) {
            if (!isWeekend(currentDate) && (isLaborDay(currentDate) || isIndependenceDay(currentDate))) {
                holidays++;
            }
        }

        return holidays;
    }   

    /**
     * Get number of weekends
     *
     * @return the number of weekends
     */
    public int getWeekendCount(){
        int weekends = 0;

        // ignore checkoutDate as it is not counted as a rental day
        for (LocalDate currentDate = checkoutDate.plusDays(1); currentDate.isBefore(endDate.plusDays(1)); currentDate = currentDate.plusDays(1)) {
            if (isWeekend(currentDate)) {
                weekends++;
            }
        }

        return weekends;

    }


    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }


    private  boolean isLaborDay(LocalDate date) {
        // Check if September and first Monday
        // Labor Day is always celebrated annually on the first Monday in September
        return date.getMonth() == Month.SEPTEMBER && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7;
    }

    private  boolean isIndependenceDay(LocalDate date) {
        // Adjust for weekend observance
        if (date.getMonth() == Month.JULY) {
            if (date.getDayOfMonth() == 4) {
                return true;
            } else if (date.getDayOfMonth() == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                return true; // Observed on Friday if July 4th is on Saturday
            } else if (date.getDayOfMonth() == 5 && date.getDayOfWeek() == DayOfWeek.MONDAY) {
                return true; // Observed on Monday if July 4th is on Sunday
            }
        }
        return false;
    }
}
