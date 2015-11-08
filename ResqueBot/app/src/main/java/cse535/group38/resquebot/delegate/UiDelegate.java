package cse535.group38.resquebot.delegate;

import android.app.Activity;
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
            //TODO: not have this field at all if not required
//            TextView tv = (TextView) currentActivity.findViewById(R.id.newTriggerId);
//            task.setTriggerId(Integer.parseInt(tv.getText().toString()+"0"));
//            tv.setText("");

            TextView tv = (TextView) currentActivity.findViewById(R.id.newTriggerData);
            task.setTriggerData(tv.getText().toString());
            tv.setText("");

            Spinner sp = (Spinner) currentActivity.findViewById(R.id.newActionType);
            task.setActionType(Constants.REV_ACTION_TYPE_CONSTANTS.get(sp.getSelectedItem().toString().replaceAll("\\s+", "")));
            sp.setSelection(0);

            //TODO: not have this field at all if not required
//            tv = (TextView) currentActivity.findViewById(R.id.newActionData);
//            task.setActionData(tv.getText().toString());
//            tv.setText("");

            task.setStatusId(1);

        }catch (Exception e){
            System.out.println("Reading from UI failed : " + e);
            return null;
        }

        return task;
    }

    public static void populateTask(Activity currentActivity, Task task){

        try {
            //TODO: not have this field at all if not required
//            TextView tv = (TextView) currentActivity.findViewById(R.id.newTriggerId);
//            tv.setText((String.valueOf(task.getTriggerId())));

            TextView tv = (TextView) currentActivity.findViewById(R.id.newTriggerData);
            tv.setText((String.valueOf(task.getTriggerData())));

            Spinner sp = (Spinner) currentActivity.findViewById(R.id.newActionType);
            sp.setSelection(task.getActionType()-1);

            //TODO: not have this field at all if not required
//            tv = (TextView) currentActivity.findViewById(R.id.newActionData);
//            tv.setText((String.valueOf(task.getActionData())));

        }catch (Exception e){
            System.out.println("Populating the UI failed : " + e);
        }

    }

}
