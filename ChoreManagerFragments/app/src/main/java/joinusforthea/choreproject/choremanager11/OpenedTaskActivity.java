package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by m-elbaz on 2017-11-28.
 */

public class OpenedTaskActivity extends AppCompatActivity{

    DatabaseReference databaseTasks;
    Task currentTask;
    String taskName;
    EditText notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskName = getIntent().getStringExtra("Task Name");


        //getting current task
        FirebaseDatabase.getInstance().getReference().child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Task task = snapshot.getValue(Task.class);
                            if(task.getTaskName().equals(taskName)){
                                currentTask = task;
                                Toast.makeText(OpenedTaskActivity.this, "currentTask = task;", Toast.LENGTH_SHORT).show();
                                updateInfo();
                            }
                        }
                    }
            @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });




        //getting valuse from the firebase database
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//      //iterating through all the nodes
    }

    private void updateInfo() {
        setContentView(R.layout.activity_opened_task);
        notes = (EditText)findViewById(R.id.notesEditTextView);
        Toast.makeText(this, "notes is null?: "+(notes==null), Toast.LENGTH_SHORT).show();
        notes.setText(currentTask.getNotes());
        setTitle(taskName);
    }

}
