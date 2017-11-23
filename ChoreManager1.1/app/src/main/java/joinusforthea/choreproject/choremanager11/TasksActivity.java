package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class TasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String[] choreList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out", "Eat the garbage"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tasks);

        //before having changed the signature to a fragment
        ListView listView = (ListView) findViewById(R.id.taskList);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this, choreList);
        listView.setAdapter(adapter);

        LinearLayout newTaskLayout = (LinearLayout) findViewById(R.id.newTaskLayout);

        newTaskLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
    }



    private void addTask(){
        Toast toast = Toast.makeText(this, "CLICKED THE ADD TASK BUTTON", Toast.LENGTH_SHORT);
        toast.show();
    }

}
