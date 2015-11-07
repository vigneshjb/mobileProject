package cse535.group38.resquebot.delegate;

import android.content.Context;
import cse535.group38.resquebot.model.Task;
import cse535.group38.resquebot.utils.DAO;

/**
 * Created by vignesh.jayabalan on 10/19/15.
 */
public class DbDelegate {

    public static boolean writeTaskToDb(Task task, Context context){
        if (task == null)
            return false;
        DAO dbObj = new DAO(context);
        try {
            dbObj.insertTask(task);
        }catch (Exception e){
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
            if (dbObj.updateTask(task)<1)
                throw new Exception("Some Error");
        }catch (Exception e){
            System.out.println("Writing to Db failed in DbDelegate : " + e);
            return false;
        }
        return true;
    }

    public static boolean deleteTask(int id, Context context) {
        DAO dbObj = new DAO(context);
        try {
            dbObj.deleteTask(id);
        }catch (Exception e){
            System.out.println("Writing to Db failed in DbDelegate : " + e);
            return false;
        }
        return true;
    }
}
