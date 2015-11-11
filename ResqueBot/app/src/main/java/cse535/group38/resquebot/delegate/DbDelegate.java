package cse535.group38.resquebot.delegate;

import android.content.Context;

import java.util.List;

import cse535.group38.resquebot.model.Log;
import cse535.group38.resquebot.dao.UploadLogsDAO;
import cse535.group38.resquebot.model.Task;
import cse535.group38.resquebot.dao.DAO;

/**
 * Created by vignesh.jayabalan on 10/19/15.
 */
public class DbDelegate {

    //_________________________________________________________________TASSK OPERATIONS_____________________________________________________________________________________________

    public static boolean writeTaskToDb(Task task, Context context) {
        if (task == null)
            return false;
        DAO dbObj = new DAO(context);
        try {
            dbObj.insertTask(task);
        } catch (Exception e) {
            System.out.println("Writing to Db failed in DbDelegate : " + e);
            return false;
        }
        return true;
    }

    public static boolean updateTask(Task task, Context context) {
        if (task == null)
            return false;
        DAO dbObj = new DAO(context);
        try {
            if (dbObj.updateTask(task) < 1)
                throw new Exception("Some Error");
        } catch (Exception e) {
            System.out.println("Writing to Db failed in DbDelegate : " + e);
            return false;
        }
        return true;
    }

    public static boolean deleteTask(int id, Context context) {
        DAO dbObj = new DAO(context);
        try {
            dbObj.deleteTask(id);
        } catch (Exception e) {
            System.out.println("Writing to Db failed in DbDelegate : " + e);
            return false;
        }
        return true;
    }

    //_________________________________________________________________LOG OPERATIONS_____________________________________________________________________________________________
    public static boolean writeLogToDb(Log log, Context context) {
        if (log == null)
            return false;
        DAO dbObj = new DAO(context);
        try {
            dbObj.insertLog(log);
        } catch (Exception e) {
            System.out.println("Writing UploadLogsDAO to Db failed in DbDelegate : " + e);
            return false;
        }
        return true;
    }

    public static UploadLogsDAO getAllLogsFromDb(Context context) {
        DAO dbObj = new DAO(context);
        UploadLogsDAO allUploadLogsDAO = new UploadLogsDAO();
        List<Log> logs;
        try {
            logs = dbObj.getAllLogs();
        } catch (Exception e) {
            System.out.println("Getting all UploadLogsDAO from DB failed in DbDelegate : " + e);
            return null;
        }
//        allUploadLogsDAO.setSomeLogs(); // This can be used to test
        allUploadLogsDAO.setLogs(logs);
        return allUploadLogsDAO;
    }

    public static boolean clearLogsInDb(Context context) {
        DAO dbObj = new DAO(context);
        try {
            dbObj.clearLogs();
        } catch (Exception e) {
            System.out.println("Clearing UploadLogsDAO in Db failed in DbDelegate : " + e);
            return false;
        }
        return true;
    }


}
