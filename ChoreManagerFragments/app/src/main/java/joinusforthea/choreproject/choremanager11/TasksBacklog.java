package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by m-elbaz on 2017-11-24.
 */

public class TasksBacklog extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {

        final String[] completedChoreList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out", "Eat the garbage"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_backlog);

        ListView listView = (ListView) findViewById(R.id.listOfCompletedTasks);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this, completedChoreList);
        listView.setAdapter(adapter);
    }
}