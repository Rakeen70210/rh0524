package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolTest {
    private Tool tool;
    private ToolType toolType;
    private ToolBrand toolBrand;

    @BeforeEach
    public void setUp() {
        toolType = ToolType.Jackhammer; // Assuming ToolType.HAMMER is a valid enum constant
        toolBrand = ToolBrand.Dewalt; // Assuming ToolBrand.DEWALT is a valid enum constant
        tool = new Tool(toolType, toolBrand);
    }

    @Test
    public void testGetToolType() {
        assertEquals(toolType, tool.getToolType(), "The tool type should match the one set in constructor");
    }

    @Test
    public void testGetToolBrand() {
        assertEquals(toolBrand, tool.getToolBrand(), "The tool brand should match the one set in constructor");
    }
}