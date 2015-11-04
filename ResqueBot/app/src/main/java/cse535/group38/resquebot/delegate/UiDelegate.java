package cse535.group38.resquebot.delegate;

import android.app.Activity;
import android.widget.TextView;
import cse535.group38.resquebot.R;
import cse535.group38.resquebot.model.Task;

/**
 * Created by vignesh.jayabalan on 10/19/15.
 */
public class UiDelegate {

    public static Task getNewTask(Activity currentActivity){
        Task task = new Task();

        try {
            TextView tv = (TextView) currentActivity.findViewById(R.id.newTriggerId);
            task.setTriggerId(Integer.parseInt(tv.getText().toString()));
            tv.setText("");

            tv = (TextView) currentActivity.findViewById(R.id.newActionType);
            task.setActionType(Integer.parseInt(tv.getText().toString()));
            tv.setText("");

            tv = (TextView) currentActivity.findViewById(R.id.newTriggerData);
            task.setTriggerData(tv.getText().toString());
            tv.setText("");

            tv = (TextView) currentActivity.findViewById(R.id.newActionData);
            task.setActionData(tv.getText().toString());
            tv.setText("");

            tv = (TextView) currentActivity.findViewById(R.id.newStatusId);
            task.setStatusId(Integer.parseInt(tv.getText().toString()));
            tv.setText("");

        }catch (Exception e){

            System.out.println("Reading from UI failed : " + e);
            return null;
        }

        return task;
    }

}
