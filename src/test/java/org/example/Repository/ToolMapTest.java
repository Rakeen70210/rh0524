package org.example.Repository;

import org.example.Model.Tool;
import org.example.Model.ToolBrand;
import org.example.Model.ToolCode;
import org.example.Model.ToolType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToolMapTest {

    @Test
    public void testSingletonInstance() {
        ToolMap instance1 = ToolMap.getInstance();
        ToolMap instance2 = ToolMap.getInstance();
        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    public void testGetTool() {
        ToolMap toolMap = ToolMap.getInstance();

        Tool chainsaw = toolMap.getTool(ToolCode.CHNS);
        assertNotNull(chainsaw, "Chainsaw should not be null");
        assertEquals(ToolType.Chainsaw, chainsaw.getToolType());
        assertEquals(ToolBrand.Stihl, chainsaw.getToolBrand());

        Tool ladder = toolMap.getTool(ToolCode.LADW);
        assertNotNull(ladder, "Ladder should not be null");
        assertEquals(ToolType.Ladder, ladder.getToolType());
        assertEquals(ToolBrand.Werner, ladder.getToolBrand());

        Tool jackhammerDewalt = toolMap.getTool(ToolCode.JAKD);
        assertNotNull(jackhammerDewalt, "Jackhammer (Dewalt) should not be null");
        assertEquals(ToolType.Jackhammer, jackhammerDewalt.getToolType());
        assertEquals(ToolBrand.Dewalt, jackhammerDewalt.getToolBrand());

        Tool jackhammerRidgid = toolMap.getTool(ToolCode.JAKR);
        assertNotNull(jackhammerRidgid, "Jackhammer (Ridgid) should not be null");
        assertEquals(ToolType.Jackhammer, jackhammerRidgid.getToolType());
        assertEquals(ToolBrand.Ridgid, jackhammerRidgid.getToolBrand());
    }

    @Test
    public void testGetTool_InvalidCode() {
        ToolMap toolMap = ToolMap.getInstance();
        Tool invalidTool = toolMap.getTool(null);
        assertNull(invalidTool, "Invalid tool code should return null");
    }
}