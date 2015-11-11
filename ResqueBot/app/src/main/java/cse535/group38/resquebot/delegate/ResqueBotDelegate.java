package cse535.group38.resquebot.delegate;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import cse535.group38.resquebot.MainActivity;
import cse535.group38.resquebot.dao.UploadLogsDAO;
import cse535.group38.resquebot.utils.Constants;

/**
 * Created by vignesh.jayabalan on 11/9/15.
 */
public class ResqueBotDelegate {

    public static void uploadLogsToServer(Context context, MainActivity a)throws Exception{
        String logsToUpload = getLogsAsString(context);
        if (null==logsToUpload){
            throw new Exception("No Logs to update");
        }
        PostLogs restOperation = new PostLogs(a);
        restOperation.execute(Constants.URL, logsToUpload);
    }

    private static String getLogsAsString(Context context){
        Gson gson = new Gson();
        UploadLogsDAO uploadLogsDAO = DbDelegate.getAllLogsFromDb(context);
        if (uploadLogsDAO.size()==0)
            return null;
        return gson.toJson(uploadLogsDAO);
    }

    private static class PostLogs extends AsyncTask<String, Void, Void> {

        private MainActivity current_activity;

        PostLogs(MainActivity activity){
            current_activity = activity;
        }

        @Override
        protected Void doInBackground(String... data) {
            String requestURL = data[0];
            String postDataParams = data[1];
            URL url;

            try {
                url = new URL(requestURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(postDataParams);
                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    current_activity.showSnackBar("Logs Uploaded Successfully");
//                    DbDelegate.clearLogsInDb(current_activity);
                } else {
                    current_activity.showSnackBar("Logs Upload Failed : Server Failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                current_activity.showSnackBar("Logs Upload Failed : Exception Caught ");
            }
            return null;
        }
    }
}