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

    DatabaseReference databasePeople;
    DatabaseReference databaseTasks;
    List<Task> tasks;
    ListView listViewTask;
    String userAvatar;
    Task currentTask;
    User currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tasks = new ArrayList<>();
        User currentUser;
        databasePeople = FirebaseDatabase.getInstance().getReference("users");
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


        //EV: ONSTART USING TASK NAME TO FIND USER, TERRIBLE IDEA
        //openning from main activity
        userAvatar = getIntent().getStringExtra("passedUserInfo");
        //attaching value event listener
        databasePeople.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous task list?
                tasks.clear();

                //iterating through all the nodes
                //setting the current user from the user avatar
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting task
                    User user = postSnapshot.getValue(User.class);
                    //adding task to the list
                    if(user.getAvatar().equals(userAvatar)){
                        currentUser = user;
                    }
                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//end of addValueEventListener for databasePeople

        databaseTasks.addValueEventListener(new ValueEventListener(){
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                         //adding task to the list iff it's assigned to the current user
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting task
                    Task task = postSnapshot.getValue(Task.class);
                    if(task.getAssignedTo().equals(currentUser)){
                        tasks.add(task);
                    }
                }

                //creating adapter
                TasksCustomAdapter taskAdapter = new TasksCustomAdapter(ProfilePageActivity.this, tasks);
                //attaching adapter to the listview
                listViewTask.setAdapter(taskAdapter);

                //update the rest of the view

                updateView();
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });//end of addValueEventListener for databaseTasks




    }//end of onStart


    //this method sets the various user specific fields to the proper
    //values in the profile page xml file activity_profile
    public void updateView(){

//        ImageView avatarImageView = (ImageView) findViewById(R.id.avatarImageView);
//        String avtr = currentUser.getAvatar();
//        int resID = this.getResources().getIdentifier(""+avtr, "drawable", this.getPackageName());
//        avatarImageView.setBackgroundResource(resID);

    }

}
