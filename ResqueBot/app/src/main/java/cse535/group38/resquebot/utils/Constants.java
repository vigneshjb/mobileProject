package cse535.group38.resquebot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vignesh.jayabalan on 11/5/15.
 */
public class Constants {

    public static final Map<Integer, String> ACTION_CONSTANTS = new HashMap<>();
    static {
        ACTION_CONSTANTS.put(1,"NormalProfile");
        ACTION_CONSTANTS.put(2,"SilentProfile");
        ACTION_CONSTANTS.put(3,"ReduceBrightness");
        ACTION_CONSTANTS.put(4,"DefaultBrightness");
    }

}
