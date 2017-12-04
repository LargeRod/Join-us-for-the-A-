package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by admin on 04/12/2017.
 */

public class TaskAddActivity extends AppCompatActivity{

    Task task;
    String taskId;
    Button doneButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task = (Task)getIntent().getSerializableExtra("Task");
        taskId = task.getId();
        setTitle(task.getTaskName());
        setContentView(R.layout.activity_add_task);

        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this will take everything on the activity_add_task and save it
                //in the task we're creating
                updateTask(taskId);
            }
        });//end of the onclick listener
    }

    private void updateTask(String id) {
        //getting the specified task reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tasks").child(id);
        //updating task

        //get the text written in notesEditText
        EditText notesEditText = (EditText) findViewById(R.id.notesEditText);
        String notes = notesEditText.getText().toString();
        task.setNotes(notes);
        Toast.makeText(getApplicationContext(), "updating notes to: "+task.getNotes(), Toast.LENGTH_LONG).show();
        //but i dont want to create a new task and replace everything....
        //Task task = new Task(id, name, price);
        dR.setValue(task);
        Toast.makeText(getApplicationContext(), "Task Updated", Toast.LENGTH_LONG).show();
    }
}
