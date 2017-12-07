package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by m-elbaz on 2017-11-24.
 */

public class TasksBacklog extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {

        final String[] completedChoreList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out", "Eat the garbage"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_backlog);

       // EV: commenging out untill i figure out what the parameters should be
//        ListView listView = (ListView) findViewById(R.id.listOfCompletedTasks);
//        TasksCustomAdapter adapter = new TasksCustomAdapter(this, completedChoreList);
//        listView.setAdapter(adapter);
    }
}