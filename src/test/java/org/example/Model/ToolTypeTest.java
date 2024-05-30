package org.example.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolTypeTest {

    @Test
    public void testChainsawProperties() {
        assertEquals(1.49, ToolType.Chainsaw.getDailyCharge());
        assertEquals(true, ToolType.Chainsaw.isWeekdayCharge());
        assertEquals(false, ToolType.Chainsaw.isWeekendCharge());
        assertEquals(true, ToolType.Chainsaw.isHolidayCharge());
    }

    @Test
    public void testLadderProperties() {
        assertEquals(1.99, ToolType.Ladder.getDailyCharge());
        assertEquals(true, ToolType.Ladder.isWeekdayCharge());
        assertEquals(true, ToolType.Ladder.isWeekendCharge());
        assertEquals(false, ToolType.Ladder.isHolidayCharge());
    }

    @Test
    public void testJackhammerProperties() {
        assertEquals(2.99, ToolType.Jackhammer.getDailyCharge());
        assertEquals(true, ToolType.Jackhammer.isWeekdayCharge());
        assertEquals(false, ToolType.Jackhammer.isWeekendCharge());
        assertEquals(false, ToolType.Jackhammer.isHolidayCharge());
    }
}