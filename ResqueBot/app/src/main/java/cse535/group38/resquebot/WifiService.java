package cse535.group38.resquebot;

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
//TODO: create a new package called backgroundService and move this class into that.
public class WifiService extends Service {

    String ssid;

    @Override
    public void onCreate()
    {
        this.registerReceiver(this.myWifiReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    private BroadcastReceiver myWifiReceiver
            = new BroadcastReceiver(){

        @Override
        public void onReceive(Context arg0, Intent intent) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                printWifiConnectivity();
            }
        }};

    private void printWifiConnectivity(){

        ConnectivityManager connectManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo myWifiInfo = wifiManager.getConnectionInfo();

        Context context = getApplicationContext();
        if (networkInfo.isConnected()){
            //Can do some tasks here based on already known ssid's. If its a new SSId, we can prompt user to register this ssid as Home/school etc and suggest tasks
            ssid = myWifiInfo.getSSID();
            Toast.makeText(context, "ResqueBot Msg: Wifi Connected to "+ssid, Toast.LENGTH_SHORT).show();
        }
        else{
            //Do something if Wifi not available
            Toast.makeText(context, "ResqueBot Msg: Wifi Disconnected to "+ssid, Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }
}
