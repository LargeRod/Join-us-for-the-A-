package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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
    PeopleCustomAdapter adapter;
    private String[] avatarArray = {"man.png","man1.png" ,"man2.png" ,"man3.png" ,"man4.png","girl.png","girl1.png","boy.png","boy1.png"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databasePeople = FirebaseDatabase.getInstance().getReference("items");
        peopleArray = new ArrayList<>();
        listView = (ListView) findViewById(R.id.peopleList);
        adapter = new PeopleCustomAdapter(ChooseUserActivity.this,peopleArray);

        setContentView(R.layout.activity_choose_user);
//        personLayout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                selectUser();
//            }
//        });
    }

    public void onStart() {
        super.onStart();
        //attaching value event listener
        databasePeople.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous list?
                peopleArray.clear();

                //iterating through all the nodes
                //EV: this fills the array with the database objects
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting users
                    User user = postSnapshot.getValue(User.class);
                    //adding user to the list
                    peopleArray.add(user);
                }

                //creating adapter
                PeopleCustomAdapter adapter = new PeopleCustomAdapter(ChooseUserActivity.this, peopleArray);
                //attaching adapter to the listview
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//end of addValueEventListener
    }//end of onStart
 }
