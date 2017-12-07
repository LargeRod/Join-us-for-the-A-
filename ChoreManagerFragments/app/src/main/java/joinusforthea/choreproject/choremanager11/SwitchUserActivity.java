package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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

public class SwitchUserActivity extends AppCompatActivity{

    DatabaseReference databasePeople;
    List<User> peopleArray;
    ListView listView;
    User selectedUser;
    String userName; //the name of the user selected

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        databasePeople = FirebaseDatabase.getInstance().getReference("users");
        setContentView(R.layout.activity_choose_user);
        peopleArray = new ArrayList<>();
        ((TextView)findViewById(R.id.title)).setText("Switch To...");

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
                     ChooseUserCustomAdapter adapter = new ChooseUserCustomAdapter(SwitchUserActivity.this, peopleArray);
                     //attaching adapter to the listview
                     listView.setAdapter(adapter);
                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });//end of firebase thing

    }

    public void selectedUser(View view){
        TextView tv = (TextView) view.findViewById(R.id.personNameTextView);
        userName = tv.getText().toString();


        //finding current user
        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if(user.getName().equals(userName)){
                        selectedUser = user;
                        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("selectedUser");
                        dR.removeValue();
                        dR.setValue(user);
                    }
                }
            }
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //note: currentTask is null in this method
        //close window when user is selected
        finish();
    }

}