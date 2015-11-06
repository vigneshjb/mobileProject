package cse535.group38.resquebot.utils;

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
import cse535.group38.resquebot.model.Task;

public class WifiService extends Service {

    DAO dbUtil;

    String ssid;
    //TODO: delete this temporary tasks and already storedSsids. Decide how to store tasks as numbers and associate that number with a name/ or directly store as names etc
    ArrayList<String> storedSsids = new ArrayList<String>();
    //TODO: Temporary mapping of ssids to tasks
    //HashMap<String, ArrayList<Task>> ssidToTasks = new HashMap<String, ArrayList<Task>>();
    ArrayList<Task> tasksForSsid = new ArrayList<Task>();

    @Override
    public void onCreate() {
        dbUtil = new DAO(getApplicationContext());
        /*Task taskSilentProfile = new Task();
        Task taskNormalProfile = new Task();
        Task taskReduceBrightness = new Task();
        Task taskIncreaseBrightness= new Task();
        //TODO: Testing all tasks temporarily
        taskNormalProfile.setName("NormalProfile");
        tasksForSsid.add(taskNormalProfile);
        taskSilentProfile.setName("SilentProfile");
        tasksForSsid.add(taskSilentProfile);
        taskReduceBrightness.setName("ReduceBrightness");
        tasksForSsid.add(taskReduceBrightness);
        taskIncreaseBrightness.setName("DefaultBrightness");
        tasksForSsid.add(taskIncreaseBrightness);*/
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
            //Can do some tasks here based on already known ssid's. If its a new SSId, we can prompt user to register this ssid as Home/school etc and suggest tasks
            //TODO: Must have list of tasks (for now silence phone, reduce/increase brightness) in DB
            //TODO: Must have all SSID's and there associated tasks for the particular SSID in DB (may be something like a hashmap for easy searching of SSID's)

            ssid = myWifiInfo.getSSID();
            //ssidToTasks.put(ssid, tasksForSsid);
            //TODO: Temporarily added ssid
            storedSsids.add(ssid);
            if (storedSsids.contains(ssid)) {
                //Get the associated tasks for that SSID
                List<Task> tasksToBePerformed = dbUtil.getActionList(ssid);
                PerformTasks taskObj = new PerformTasks();
                taskObj.performTasks(tasksToBePerformed, getApplicationContext());
            } else {
                storedSsids.add(ssid);
                //TODO: promptuser some tasks and also perform those tasks
            }
            Toast.makeText(context, "ResqueBot Msg: Wifi Connected to " + ssid, Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        public IBinder onBind (Intent arg0){
            // TODO Auto-generated method stub
            return null;
        }
    }

