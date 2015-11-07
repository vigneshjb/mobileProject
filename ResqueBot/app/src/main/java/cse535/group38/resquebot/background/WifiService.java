package cse535.group38.resquebot.background;

/**
 * Created by nikki on 10/19/2015.
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;
import android.content.Intent;
import android.os.IBinder;

import java.util.*;

import cse535.group38.resquebot.delegate.DbDelegate;
import cse535.group38.resquebot.model.Task;
import cse535.group38.resquebot.utils.DAO;
import cse535.group38.resquebot.utils.PerformTasks;

public class WifiService extends Service {
    DAO dbUtil;
    String ssid;
    ArrayList<String> storedSsids = new ArrayList<String>();

    @Override
    public void onCreate() {
        dbUtil = new DAO(getApplicationContext());
        this.registerReceiver(this.myWifiReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private BroadcastReceiver myWifiReceiver
            = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent intent) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                printWifiConnectivity();
            }
        }
    };

    private void printWifiConnectivity() {

        ConnectivityManager connectManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo myWifiInfo = wifiManager.getConnectionInfo();

        final Context context = getApplicationContext();
        if (networkInfo.isConnected()) {
            ssid = myWifiInfo.getSSID();
            List<Task> tasksToBePerformed = dbUtil.getActionList(ssid);
            if (tasksToBePerformed.size() == 0) {
                createTaskNewSSID(ssid);
                Toast.makeText(context, "ResqueBot Msg: New Wifi SSID Detected :" + ssid, Toast.LENGTH_SHORT).show();
            } else {
                PerformTasks taskObj = new PerformTasks();
                taskObj.performTasks(tasksToBePerformed, getApplicationContext());
                Toast.makeText(context, "ResqueBot Msg: Existing Wifi Detected :" + ssid, Toast.LENGTH_SHORT).show();
            }
        }
    }

    //TODO: Onslide Listener: automatically list tasks in the "TaskList" tab of the App
    private void createTaskNewSSID(String ssid) {
        final Context context = getApplicationContext();
        DbDelegate dbDelegate = new DbDelegate();
        ssid = ssid.substring(1, ssid.length() - 1);
        //analyze the ssid name
        //TODO: change "asu" to "home"
        if (ssid.toLowerCase().matches(".*asu.*") || ssid.toLowerCase().matches(".*house.*") || ssid.toLowerCase().matches(".*public.*")) {
            //TODO: Temporary parameters 1->triggerId, actionData
            Task task = new Task(0, 1, ssid, "temp", 0); //Normal Profile - ActionType =1, statusId= 0(Will be 1 when user activates it).
            dbDelegate.writeTaskToDb(task, context);
            task = new Task(0, 4, ssid, "temp", 0); //Default Brightness - ActionType =4, statusId= 0(Will be 1 when user activates it).
            dbDelegate.writeTaskToDb(task, context);
            Toast.makeText(context, "Possible Home/Public Network Identified :" + ssid + "Possible tasks suggested", Toast.LENGTH_SHORT).show();
        }
        if (ssid.toLowerCase().matches(".*guest.*")) {
            Task task = new Task(0, 2, ssid, "temp", 0);
            dbDelegate.writeTaskToDb(task, context);
            task = new Task(0, 3, ssid, "temp", 0);
            dbDelegate.writeTaskToDb(task, context);
            Toast.makeText(context, "Possible guest Network Identified :" + ssid, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }
}

