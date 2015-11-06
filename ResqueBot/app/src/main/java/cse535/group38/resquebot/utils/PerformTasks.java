package cse535.group38.resquebot.utils;

import cse535.group38.resquebot.model.Task;
import java.util.*;
import android.media.AudioManager;
import android.content.Context;
import 	android.content.ContentResolver;
import android.provider.Settings;
import android.provider.Settings.System;
import java.lang.*;


/**
 * Created by nikki on 10/23/2015.
 */
public class PerformTasks {
    public void performTasks(List<Task> tasks, Context context) {
        try {

            for (Task task : tasks) {
                switch (task.getName()) {

                    case "SilentProfile":
                        AudioManager audioManagerVibrate = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                        //Can also be RINGER_MODE_SILENT instead of Vibrate
                        audioManagerVibrate.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        break;
                    //TODO: Change name of normal profile
                    case "NormalProfile":
                        AudioManager audioManagerNormal = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                        audioManagerNormal.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        break;
                    case "ReduceBrightness":
                        //TODO: Can adjust this value
                        ContentResolver contentResolver = context.getContentResolver();
                        int brightness = System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
                        brightness = 10;
                        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
                        break;
                    case "DefaultBrightness":
                        //A Default brightness value
                        brightness = 105;
                        contentResolver = context.getContentResolver();
                        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
                        break;
                }

            }
        } catch (Exception e) {
            java.lang.System.out.println("Task failed : " + e);
        }
    }
}
