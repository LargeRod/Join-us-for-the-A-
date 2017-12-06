package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 04/12/2017.
 */

public class ChooseUserActivity extends AppCompatActivity{


    DatabaseReference databasePeople;
    List<User> peopleArray;
    ListView listView;
    User selectedUser;
    String taskName;  //the title of the task
    static Task currentTask; //the task object
    String userName; //the name of the user selected


    protected void onCreate(Bundle savedInstanceState) {
        taskName = getIntent().getStringExtra("passedTaskName");
        super.onCreate(savedInstanceState);
        databasePeople = FirebaseDatabase.getInstance().getReference("users");
        setContentView(R.layout.activity_choose_user);
        peopleArray = new ArrayList<>();


        //EV: the task thing used to go here, i moved it to updateTaskAndUser


    }

    public void onStart() {
        super.onStart();


        //attaching value event listener
        databasePeople.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //clearing the previous list
                peopleArray.clear();

                //iterating through all the nodes
                //EV: this fills the array with the database objects
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting users
                    User user = postSnapshot.getValue(User.class);
                    //adding user to the list
                    peopleArray.add(user);
                }
                listView = (ListView) findViewById(R.id.choosePeopleList);
                //creating adapter
                ChooseUserCustomAdapter adapter = new ChooseUserCustomAdapter(ChooseUserActivity.this, peopleArray);
                //attaching adapter to the listview
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//end of firebase thing


        //getting current task
        FirebaseDatabase.getInstance().getReference().child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Task task = snapshot.getValue(Task.class);
                    if(task.getTaskName().equals(taskName)){
                        setCurrentTask(task);
                    }
                }
            }
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }//end of onStart



    private void updateTaskAndUser() {


        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean set = false;

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        if (user.getName().equals(userName)) {
                            selectedUser = user;
                            set = true;
                        }
                    }
                    if (set) {

                        //EV: firebase doesnt like array lists... :( so ill comment out for now
                        //selectedUser.addTask(currentTask);
                        currentTask.setAssignedTo(selectedUser);

                        updateUser(selectedUser, selectedUser.getId());
                        updateTask(currentTask,currentTask.getId());
                     }
            }

            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChooseUserActivity.this, "error!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
            }
        });




//        selectedUser.addTask(currentTask);
//        currentTask.setAssignedTo(selectedUser);
//        Toast.makeText(this, "Updated objects", Toast.LENGTH_SHORT).show();
//        updateFirebase is the old method
//        updateFirebase(selectedUser, selectedUser.getId(), "users");
//        updateFirebase(currentTask, currentTask.getId(), "tasks");

    }

    public void selectedUser(View view){
        TextView tv = (TextView) view.findViewById(R.id.personNameTextView);
        userName = tv.getText().toString();

        //note: currentTask is null in this method
        updateTaskAndUser();
        //close window when user is selected
        finish();
    }

    private void updateUser(User u, String id) {
        //getting the specified task reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("users").child(id);
        //updating user
        dR.setValue(u);

    }
    private void updateTask(Task t, String id) {
        //getting the specified task reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tasks").child(id);
        //updating task
        dR.setValue(t);
        Toast.makeText(getApplicationContext(), "Updated Firebase task "+t.getAssignedTo(), Toast.LENGTH_SHORT).show();
    }

    //probably wont help
    public void setCurrentTask(Task task){
        currentTask = task;
        Toast.makeText(this, "task set to "+(currentTask.getTaskName()), Toast.LENGTH_LONG).show();
    }

}
