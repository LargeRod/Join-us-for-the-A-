package joinusforthea.choreproject.choremanager11;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by m-elbaz on 2017-11-28.
 */

public class OpenedTaskActivity extends AppCompatActivity {

    DatabaseReference databaseTasks;
    Task currentTask;
    String taskName;
    TextView notes;


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
        notes = (TextView)findViewById(R.id.notesEditTextView);
        Toast.makeText(this, "notes is null?: "+(notes==null), Toast.LENGTH_SHORT).show();
        notes.setText(currentTask.getNotes());
        setTitle(taskName);
    }

    //calling the DatePickerFragment to display a date picker dialog
    public void showDatePickerDialog(View v) {

        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");

        //MUST OBTAIN DATE FROM DATEPICKERFRAGMENT AND CALL SETDATE WITH IT TO SET
        //IT IN THE VIEW
    }

    public void setDate(String date) {
        TextView dateText = (TextView) findViewById(R.id.dateText);
        dateText.setText(date);
    }

}
