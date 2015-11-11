package cse535.group38.resquebot.delegate;

import android.app.Activity;
import android.support.v7.widget.SwitchCompat;
import android.widget.Spinner;
import android.widget.TextView;
import cse535.group38.resquebot.R;
import cse535.group38.resquebot.model.Task;
import cse535.group38.resquebot.utils.Constants;

/**
 * Created by vignesh.jayabalan on 10/19/15.
 */
public class UiDelegate {

    public static Task getNewTask(Activity currentActivity){
        Task task = new Task();

        try {
            TextView tv = (TextView) currentActivity.findViewById(R.id.newTriggerData);
            task.setTriggerData(tv.getText().toString());
            tv.setText("");

            Spinner sp = (Spinner) currentActivity.findViewById(R.id.newActionType);
            task.setActionType(Constants.REV_ACTION_TYPE_CONSTANTS.get(sp.getSelectedItem().toString().replaceAll("\\s+", "")));
            sp.setSelection(0);

            SwitchCompat sc = (SwitchCompat) currentActivity.findViewById(R.id.newStatusId);
            if (null == sc || sc.isChecked())
                task.setStatusId(1);
            else
                task.setStatusId(0);

        }catch (Exception e){
            System.out.println("Reading from UI failed : " + e);
            return null;
        }

        return task;
    }

    public static void populateTask(Activity currentActivity, Task task){

        try {
            TextView tv = (TextView) currentActivity.findViewById(R.id.newTriggerData);
            tv.setText((String.valueOf(task.getTriggerData())));

            Spinner sp = (Spinner) currentActivity.findViewById(R.id.newActionType);
            sp.setSelection(task.getActionType()-1);

            SwitchCompat sc = (SwitchCompat) currentActivity.findViewById(R.id.newStatusId);
            sc.setChecked(task.getStatusId()==1);

        }catch (Exception e){
            System.out.println("Populating the UI failed : " + e);
        }

    }

}
