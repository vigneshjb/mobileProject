package cse535.group38.resquebot.utils;

import cse535.group38.resquebot.delegate.DbDelegate;
import cse535.group38.resquebot.model.Log;
import cse535.group38.resquebot.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
                if (task.getStatusId() == 1) {
                    Integer a = task.getActionType();
                    Log log;
                    DbDelegate dbDelegate = new DbDelegate();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    String actionKey = Constants.ACTION_TYPE_CONSTANTS.get(a) == null ? Constants.ACTION_TYPE_CONSTANTS.get(4) : Constants.ACTION_TYPE_CONSTANTS.get(a);
                    switch (actionKey) {

                        case "SilentProfile":
                            AudioManager audioManagerVibrate = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                            audioManagerVibrate.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                            //Updating the Logs Table
                            log = new Log(dateFormat.format(date), "RINGER_MODE_CHANGED_TO_SILENT");
                            // java.lang.System.out.println("*********************RINGER_MODE_CHANGED_TO_SILENT***************************************************"+ dateFormat.format(date));
                            dbDelegate.writeLogToDb(log, context);
                            break;
                        case "NormalProfile":
                            AudioManager audioManagerNormal = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                            audioManagerNormal.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                            //Updating the Logs Table
                            log = new Log(dateFormat.format(date), "RINGER_MODE_CHANGED_TO_NORMAL");
                            // java.lang.System.out.println("*********************RINGER_MODE_CHANGED_TO_NORMAL***************************************************"+ dateFormat.format(date));
                            dbDelegate.writeLogToDb(log, context);
                            break;
                        case "ReduceBrightness":
                            ContentResolver contentResolver = context.getContentResolver();
                            int brightness = System.getInt(contentResolver, System.SCREEN_BRIGHTNESS);
                            brightness = 10;
                            System.putInt(contentResolver, System.SCREEN_BRIGHTNESS, brightness);
                            //Updating the Logs Table
                            log = new Log(dateFormat.format(date), "BRIGHTNESS_REDUCED_TO_10");
                            //java.lang.System.out.println("*********************BRIGHTNESS_REDUCED_TO_10***************************************************"+ dateFormat.format(date));
                            dbDelegate.writeLogToDb(log, context);
                            break;
                        case "DefaultBrightness":
                            brightness = 105;
                            contentResolver = context.getContentResolver();
                            System.putInt(contentResolver, System.SCREEN_BRIGHTNESS, brightness);
                            log = new Log(dateFormat.format(date), "BRIGHTNESS_INCREASED_TO_105");
                            //java.lang.System.out.println("*********************BRIGHTNESS_INCRESED_TO_105***************************************************"+ dateFormat.format(date));
                            dbDelegate.writeLogToDb(log, context);
                            break;
                    }

                }
                else
                {
                    //do not execute the task as the task is not activated.
                }
            }
        } catch (Exception e) {
            java.lang.System.out.println("Task failed : " + e);
        }
    }
}
