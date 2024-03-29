package cse535.group38.resquebot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cse535.group38.resquebot.delegate.DbDelegate;
import cse535.group38.resquebot.delegate.UiDelegate;
import cse535.group38.resquebot.model.Task;

/**
 * Created by vignesh.jayabalan on 11/7/15.
 */
public class TaskRudActivity extends AppCompatActivity{

    private Task currentTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_rud);

        Toolbar toolbar = (Toolbar) findViewById(R.id.taskRudToolbar1);
        toolbar.setTitle("Edit Task");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        currentTask = (Task) intent.getExtras().getSerializable("taskObject");
        UiDelegate.populateTask(this, currentTask);
    }

    private void showSnackBar(String message){
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                .show();
    }

    private void endActivity(String message){
        MainActivity.message = message;
        finish();
    }

    //UI event Listeners

    public void updateTask(View view) {
        Task updatedTask = UiDelegate.getNewTask(this);
        updatedTask.setId(currentTask.getId());
        if (DbDelegate.updateTask(updatedTask, getApplicationContext())){
            endActivity("Task Updated");
        }
        else
            showSnackBar("Task Update failed");
    }

    public void deleteTask(View view){
        if (DbDelegate.deleteTask(currentTask.getId(), getApplicationContext())) {
            endActivity("Task Deleted");
        }
        else
            showSnackBar("Task Delete failed");
    }

}
