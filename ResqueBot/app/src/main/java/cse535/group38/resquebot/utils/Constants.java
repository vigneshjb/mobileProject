package cse535.group38.resquebot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vignesh.jayabalan on 11/5/15.
 */
public class Constants {

    public static final Map<Integer, String> ACTION_TYPE_CONSTANTS = new HashMap<>();

    public static final Map<String, Integer> REV_ACTION_TYPE_CONSTANTS = new HashMap<>();

    static {
        ACTION_TYPE_CONSTANTS.put(1,"NormalProfile");
        ACTION_TYPE_CONSTANTS.put(2,"SilentProfile");
        ACTION_TYPE_CONSTANTS.put(3,"ReduceBrightness");
        ACTION_TYPE_CONSTANTS.put(4,"DefaultBrightness");

        REV_ACTION_TYPE_CONSTANTS.put("NormalProfile", 1);
        REV_ACTION_TYPE_CONSTANTS.put("SilentProfile", 2);
        REV_ACTION_TYPE_CONSTANTS.put("ReduceBrightness", 3);
        REV_ACTION_TYPE_CONSTANTS.put("DefaultBrightness", 4);
    }

}
