package org.example.Service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeekendHolidayServiceTest {

    @Test
    public void testGetWeekdayCount() {
        WeekendHolidayService service = new WeekendHolidayService(LocalDate.of(2023, 7, 1), 10);
        assertEquals(6, service.getWeekdayCount());
    }

    @Test
    public void testGetHolidayCount() {
        WeekendHolidayService service = new WeekendHolidayService(LocalDate.of(2023, 7, 1), 10);
        assertEquals(1, service.getHolidayCount()); // July 4th is within this range
    }

    @Test
    public void testGetWeekendCount() {
        WeekendHolidayService service = new WeekendHolidayService(LocalDate.of(2023, 7, 1), 10);
        assertEquals(4, service.getWeekendCount());
    }

    @Test
    public void testLaborDay() {
        WeekendHolidayService service = new WeekendHolidayService(LocalDate.of(2023, 9, 1), 7);
        assertEquals(1, service.getHolidayCount()); // Labor Day is within this range
    }

    @Test
    public void testIndependenceDayObservedOnFriday() {
        WeekendHolidayService service = new WeekendHolidayService(LocalDate.of(2020, 7, 1), 5);
        assertEquals(2, service.getHolidayCount()); // July 4th observed on Friday, July 3rd
    }

    @Test
    public void testIndependenceDayObservedOnMonday() {
        WeekendHolidayService service = new WeekendHolidayService(LocalDate.of(2021, 7, 1), 5);
        assertEquals(2, service.getHolidayCount()); // July 4th observed on Monday, July 5th
    }
}