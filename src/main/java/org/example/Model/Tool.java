package org.example.Model;

/**
 * Represents a tool and its brand
 * @author rakeen huq
 */
public class Tool {
    private final ToolType toolType;
    private final ToolBrand toolBrand;

    /**
     * instantiates the tool and brand
     * @param toolType
     * @param toolBrand
     */
    public Tool(ToolType toolType, ToolBrand toolBrand) {
        this.toolType = toolType;
        this.toolBrand = toolBrand;
    }

    /**
     * Returns the tool type
     * @return the tool type
     */
    public ToolType getToolType() {
        return toolType;
    }

    /**
     * Returns the tool brand
     * @return the tool brand
     */
    public ToolBrand getToolBrand() {
        return toolBrand;
    }
}
