package cse535.group38.resquebot.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cse535.group38.resquebot.model.Log;
import cse535.group38.resquebot.model.Task;

/**
 * Created by vignesh.jayabalan on 10/11/15.
 */
public class DAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "resque";
    private static final String TASKS_TABLE_NAME = "tasks";
    private static final String LOGS_TABLE_NAME = "logs";

    public DAO(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TASK_CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TASKS_TABLE_NAME +
                " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, TRIGGER_ID INTEGER, " +
                "ACTION_TYPE INTEGER, TRIGGER_DATA VARCHAR(20), ACTION_DATA VARCHAR(20), " +
                "STATUS_ID INTEGER )";
        db.execSQL(TASK_CREATE_STATEMENT);

        String LOG_CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + LOGS_TABLE_NAME +
                " ( DATE VARCHAR(20), DESCRIPTION VARCHAR(20) )" ;
        db.execSQL(LOG_CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // _____________________________________________ TASK OPERATIONS ____________________________________________

    public List<Task> getAllTasks(){
        List<Task> allTasks = new ArrayList<Task>();
        String selectQuery = "SELECT  * FROM " + TASKS_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTriggerId(Integer.parseInt(cursor.getString(1)));
                task.setActionType(Integer.parseInt(cursor.getString(2)));
                task.setTriggerData(cursor.getString(3));
                task.setActionData(cursor.getString(4));
                task.setStatusId(Integer.parseInt(cursor.getString(5)));
                allTasks.add(task);
            } while (cursor.moveToNext());
        }
        return allTasks;
    }

    //SELECT * FROM TASKS, EVENTDESP WHERE EVENTDESP.EVENTID = TASK.EVENTID

    public void insertTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TRIGGER_ID", task.getTriggerId());
        values.put("ACTION_TYPE", task.getActionType());
        values.put("TRIGGER_DATA", task.getTriggerData());
        values.put("ACTION_DATA", task.getActionData());
        values.put("STATUS_ID", task.getStatusId());
        db.insert(TASKS_TABLE_NAME, null, values);
        db.close();
    }

    public List<Task> getActionList(String triggerData){
        List<Task> returnData = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TASKS_TABLE_NAME + " WHERE TRIGGER_DATA = " + triggerData;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTriggerId(Integer.parseInt(cursor.getString(1)));
                task.setActionType(Integer.parseInt(cursor.getString(2)));
                task.setTriggerData(cursor.getString(3));
                task.setActionData(cursor.getString(4));
                task.setStatusId(Integer.parseInt(cursor.getString(5)));
                returnData.add(task);
            } while (cursor.moveToNext());
        }
        return returnData;
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("TRIGGER_ID", task.getTriggerId());
        values.put("TRIGGER_DATA", task.getTriggerData());
        values.put("ACTION_TYPE", task.getActionType());
        values.put("ACTION_DATA", task.getActionData());
        values.put("STATUS_ID", task.getStatusId());

        return db.update(TASKS_TABLE_NAME, values, "id" + " = ?",
                new String[] { String.valueOf(task.getId()) });
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASKS_TABLE_NAME, "id" + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    // _____________________________________________ LOG OPERATIONS _____________________________________________

    //Gets all logs
    public List<Log> getAllLogs()
    {
        List<Log> allLogs = new ArrayList<Log>();
        String selectQuery = "SELECT  * FROM " + LOGS_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Log log = new Log();
                log.setTimestamp(cursor.getString(0));
                log.setDescription(cursor.getString(1));
                allLogs.add(log);
            } while (cursor.moveToNext());
        }
        return allLogs;
    }

    public void insertLog(Log log){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DATE", log.getTimestamp());
        values.put("DESCRIPTION", log.getDescription());
        db.insert(LOGS_TABLE_NAME, null, values);
        db.close();
    }

    public void clearLogs() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + LOGS_TABLE_NAME);
        db.close();
    }


}