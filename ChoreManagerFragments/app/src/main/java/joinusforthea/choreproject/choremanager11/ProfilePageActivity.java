package joinusforthea.choreproject.choremanager11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfilePageActivity extends AppCompatActivity {

    DatabaseReference databaseTasks;
    List<Task> tasks;
    ListView listViewTask;
    String currentTaskName;
    Task currentTask;
    User currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tasks = new ArrayList<>();
        User currentUser;
        databaseTasks = FirebaseDatabase.getInstance().getReference("tasks");
        listViewTask = (ListView) findViewById(R.id.usersTaskList);


        //creating adapter
        TasksCustomAdapter taskAdapter = new TasksCustomAdapter(ProfilePageActivity.this, tasks);
        //attaching adapter to the listview
        listViewTask.setAdapter(taskAdapter);
    }//end of onCreate()

    public void openMessages(View v) {
        Intent intent = new Intent(ProfilePageActivity.this, MessageActivity.class);
        startActivity(intent);
    }

    //taken from lab 5 firebase
    public void onStart() {
        super.onStart();

        currentTaskName = getIntent().getStringExtra("passedTaskName");
        //attaching value event listener
        databaseTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous task list?
                tasks.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting task
                    Task task = postSnapshot.getValue(Task.class);
                    //adding task to the list
                    if(task.getTaskName().equals(currentTaskName)){
                        currentTask = task;
                        currentUser = currentTask.getAssignedTo();
                    }
                }

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting task
                    Task task = postSnapshot.getValue(Task.class);
                    //adding task to the list iff it's assigned to the current user
                    if(task.getAssignedTo().getName().equals(currentUser.getName())){
                        tasks.add(task);
                    }
                }

                //creating adapter
                TasksCustomAdapter taskAdapter = new TasksCustomAdapter(ProfilePageActivity.this, tasks);
                //attaching adapter to the listview
                listViewTask.setAdapter(taskAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//end of addValueEventListener


    }//end of onStart

}
