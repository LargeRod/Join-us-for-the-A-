package joinusforthea.choreproject.choremanager11;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by admin on 04/12/2017.
 */

public class TaskAddActivity extends AppCompatActivity{

    static String taskName;
    String taskId;
    Button doneButton;
    DialogFragment dateFragment;
    String dueDate;
    static Task currentTask;
    TextView firstName;
    TextView creatorName;
    ImageView profileAvatar;
    ImageView creatorAvatar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        taskName = getIntent().getStringExtra("passedTaskName");

        // makes view shift up when keyboard hides layout
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void onStart() {
        super.onStart();

//getting current task
        FirebaseDatabase.getInstance().getReference().child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            //ondatachange sets the current task
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Task task = snapshot.getValue(Task.class);
                    if (task.getTaskName().equals(taskName)) {
                        currentTask = task;
                        taskId = currentTask.getId();
                        updateTask(taskId);
                        setLayoutViews();
                    }
                }
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }//end of on start

    private void setLayoutViews() {
        setTitle(currentTask.getTaskName());


        //update assigned to fields
        firstName = (TextView) findViewById(R.id.firstName);
        firstName.setText(currentTask.getAssignedTo().getName());

        profileAvatar = (ImageView)findViewById(R.id.profileAvatar);
        String avtr = currentTask.getUserAvatar();
        int resID = this.getResources().getIdentifier(""+avtr, "drawable", this.getPackageName());
        profileAvatar.setBackgroundResource(resID);


        //set creator fields
        creatorName = (TextView) findViewById(R.id.creatorName);
        creatorName.setText(currentTask.getCreator().getName());

        creatorAvatar = (ImageView) findViewById(R.id.creatorAvatar);
        String creatorAvtr = currentTask.getCreator().getAvatar();
        int creatorResID = this.getResources().getIdentifier(""+creatorAvtr, "drawable", this.getPackageName());
        creatorAvatar.setBackgroundResource(creatorResID);



    }


    private void updateTask(String id) {
        //getting the specified task reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tasks").child(id);
        //updating task
        //get the text written in notesEditText
        EditText notesEditText = (EditText) findViewById(R.id.notesEditText);
        String notes = notesEditText.getText().toString();
        currentTask.setNotes(notes);

        TextView dateTextView = (TextView) findViewById(R.id.dateText);
        dateTextView.setText(dueDate);
        currentTask.setDueDate(dueDate);

        TextView creatorName = (TextView) findViewById(R.id.creatorName);
        creatorName.setText(currentTask.getCreator().getName());


        dR.setValue(currentTask);
    }

    //picking the date from the calendar
    //calling the DatePickerFragment to display a date picker dialog
    public void showDatePickerDialog(View v) {


        dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");
        //to string returns the date in proper format
        dueDate = dateFragment.toString();

        TextView dateTextInView = (TextView) findViewById(R.id.dateText);
        dateTextInView.setText(dueDate);
    }

    public void formatDueText(View v) {

    }

    public void choosePersonActivity(View view){
        Intent intent = new Intent(TaskAddActivity.this, ChooseUserActivity.class);
        intent.putExtra("passedTaskName", taskName);

        startActivity(intent);
    }

    public void openPeople(View v) {
        Intent intent = new Intent(TaskAddActivity.this, ProfilePageActivity.class);
        startActivity(intent);
    }



}
