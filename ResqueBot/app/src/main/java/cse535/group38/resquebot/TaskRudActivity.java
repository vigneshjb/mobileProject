package cse535.group38.resquebot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cse535.group38.resquebot.delegate.UiDelegate;
import cse535.group38.resquebot.model.Task;
import cse535.group38.resquebot.utils.DAO;

/**
 * Created by vignesh.jayabalan on 11/7/15.
 */
public class TaskRudActivity extends AppCompatActivity{

    private Task currentTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);
        Intent intent = getIntent();
        currentTask = (Task) intent.getSerializableExtra("taskObject");
        UiDelegate.populateTask(this,currentTask);
    }

}
