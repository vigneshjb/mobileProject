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

            task.setStatusId(1);

        }catch (Exception e){
            System.out.println("Reading from UI failed : " + e);
            return null;
        }

        return task;
    }

    public static void populateTask(Activity currentActivity, Task task){

        try {
            TextView tv = (TextView) currentActivity.findViewById(R.id.newTriggerId);
            tv.setText((String.valueOf(task.getTriggerId())));

            tv = (TextView) currentActivity.findViewById(R.id.newActionType);
            tv.setText((String.valueOf(task.getActionType())));

            tv = (TextView) currentActivity.findViewById(R.id.newTriggerData);
            tv.setText((String.valueOf(task.getTriggerData())));

            tv = (TextView) currentActivity.findViewById(R.id.newActionData);
            tv.setText((String.valueOf(task.getActionData())));

        }catch (Exception e){
            System.out.println("Populating the UI failed : " + e);
        }

    }

}
