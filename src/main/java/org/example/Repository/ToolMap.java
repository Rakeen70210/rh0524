package org.example.Repository;


import org.example.Model.Tool;
import org.example.Model.ToolBrand;
import org.example.Model.ToolCode;
import org.example.Model.ToolType;

import java.util.HashMap;
import java.util.Map;

/**
 * The ToolMap class is used to create a map of tool codes and their respective tools.
 *
 * @author rakeen huq
 */
public class ToolMap {
    private static ToolMap instance;
    private final Map<ToolCode, Tool> toolMap = new HashMap<>();

    private ToolMap() {
        toolMap.put(ToolCode.CHNS, new Tool(ToolType.Chainsaw, ToolBrand.Stihl));
        toolMap.put(ToolCode.LADW, new Tool(ToolType.Ladder, ToolBrand.Werner));
        toolMap.put(ToolCode.JAKD, new Tool(ToolType.Jackhammer, ToolBrand.Dewalt));
        toolMap.put(ToolCode.JAKR, new Tool(ToolType.Jackhammer, ToolBrand.Ridgid));
    }


    /**
     * Returns the singleton instance of the ToolMap class.
     *
     * @return the singleton instance of the ToolMap class
     */
    public static ToolMap getInstance() {
        if (instance == null) {
            instance = new ToolMap();
        }
        return instance;
    }

    /**
     * Returns the tool based on the tool code.
     *
     * @param toolCode
     * @return a Tool object representing a tool and its brand
     */
    public Tool getTool(ToolCode toolCode) {
        return toolMap.get(toolCode);
    }
}
