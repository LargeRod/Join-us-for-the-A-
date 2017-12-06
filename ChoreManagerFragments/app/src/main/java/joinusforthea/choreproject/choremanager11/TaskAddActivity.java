package joinusforthea.choreproject.choremanager11;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    DialogFragment dateFragment;
    String dueDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        task = (Task)getIntent().getSerializableExtra("Task");

        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this will take everything on the activity_add_task and save it
                //in the task we're creating
                setTitle(task.getTaskName());
                taskId = task.getId();
                updateTask(taskId);
                //closes the activity when done is pressed
                TaskAddActivity.super.onBackPressed();
            }
        });//end of the onclick listener

//        ImageView profileButton = (ImageView) findViewById(R.id.profileIcon);
//        profileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(TaskAddActivity.this, ChooseUserActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void updateTask(String id) {
        //getting the specified task reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tasks").child(id);
        //updating task
        //get the text written in notesEditText
        EditText notesEditText = (EditText) findViewById(R.id.notesEditText);
        String notes = notesEditText.getText().toString();
        task.setNotes(notes);

        TextView dateTextView = (TextView) findViewById(R.id.dateText);
        dateTextView.setText(dueDate);
        task.setDueDate(dueDate);

        dR.setValue(task);
        Toast.makeText(getApplicationContext(), "Task Updated", Toast.LENGTH_LONG).show();
    }

    //picking the date from the calendar
    //calling the DatePickerFragment to display a date picker dialog
    public void showDatePickerDialog(View v) {

        dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");
        Toast.makeText(this, "Date from TaskAddActivity: " + dateFragment.toString(), Toast.LENGTH_LONG).show();
        //to string returns the date in proper format
        dueDate = dateFragment.toString();
    }

    public void choosePersonActivity(View view){
        Toast.makeText(TaskAddActivity.this, "is task.getTaskName() null???  "+(task.getTaskName()==null), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TaskAddActivity.this, ChooseUserActivity.class);
        intent.putExtra("task name", task.getTaskName());
        startActivity(intent);
        Toast.makeText(this, "clicked user button", Toast.LENGTH_SHORT).show();
    }

}
