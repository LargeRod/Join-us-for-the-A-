package joinusforthea.choreproject.choremanager11;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
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
    TextView dateText;
    TextView firstName;
    ImageView profileIcon;
    DialogFragment dateFragment;
    String dueDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting current task
        taskName = getIntent().getStringExtra("passedTaskName");
        //taskName = getIntent().getStringExtra("Task Name");
        FirebaseDatabase.getInstance().getReference().child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Task task = snapshot.getValue(Task.class);
                    if(task.getTaskName().equals(taskName)){
                        currentTask = task;
                        updateInterface();
                        Toast.makeText(OpenedTaskActivity.this, "updated interface", Toast.LENGTH_LONG).show();
                    }
                }
            }
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    private void updateInterface() {

        setContentView(R.layout.activity_opened_task);
        setTitle(taskName);

        notes = (TextView)findViewById(R.id.notesEditTextView);
        notes.setText(currentTask.getNotes());

        dateText = (TextView) findViewById(R.id.dateText);
        dateText.setText(currentTask.getDueDate());

        //User specific
        User user = currentTask.getAssignedTo();

        profileIcon = (ImageView)findViewById(R.id.profileIcon);
        String avtr = user.getAvatar();
        int resID = this.getResources().getIdentifier(""+avtr, "drawable", this.getPackageName());
        profileIcon.setBackgroundResource(resID);

        firstName = (TextView) findViewById(R.id.firstName);
        firstName.setText(user.getName());




    }

//    //calling the DatePickerFragment to display a date picker dialog
//    public void showDatePickerDialog(View v) {
//
//        dateFragment = new DatePickerFragment();
//        dateFragment.show(getFragmentManager(), "datePicker");
//        Toast.makeText(this, "Date from showDatePickerDialog: " + dateFragment.toString(), Toast.LENGTH_LONG).show();
//        setDate(dateFragment.toString());
//        updateInfo();
//    }
//
//    public void setDate(String date) {
//        dueDate = date;
//        Toast.makeText(this, "this is the date: "+date, Toast.LENGTH_SHORT).show();
//    }


}
