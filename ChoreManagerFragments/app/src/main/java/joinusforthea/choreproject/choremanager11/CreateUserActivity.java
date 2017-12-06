package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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

public class CreateUserActivity extends AppCompatActivity{

    EditText nameView;
    DatabaseReference databasePeople;
    List<User> peopleArray;
    ListView listView;
    PeopleCustomAdapter adapter;
    ImageButton[] imageButtons;
    String selectedAvatar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databasePeople = FirebaseDatabase.getInstance().getReference("users");
        peopleArray = new ArrayList<>();
        adapter = new PeopleCustomAdapter(CreateUserActivity.this,peopleArray);
        setContentView(R.layout.activity_create_user);

        ImageButton[] ib = {(ImageButton)findViewById(R.id.a0),
                (ImageButton)findViewById(R.id.a1),
                (ImageButton)findViewById(R.id.a2),
                (ImageButton)findViewById(R.id.a3),
                (ImageButton)findViewById(R.id.a4),
                (ImageButton)findViewById(R.id.a5),
                (ImageButton)findViewById(R.id.a6),
                (ImageButton)findViewById(R.id.a7),
                (ImageButton)findViewById(R.id.a8)};
        imageButtons = ib;

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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//end of addValueEventListener
    }//end of onStart


    public void highlightAvatar(View v) {
        for (ImageButton butt: imageButtons) {
            float scale = getResources().getDisplayMetrics().density;
            int dpAsPixels = (int) (10*scale + 0.5f);
            butt.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
        }
        switch (v.getId()){
            case R.id.a0:
                v.setPadding(0,0,0,0);
                setSelectedAvatar((ImageButton)v);
                break;
            case R.id.a1:
                v.setPadding(0,0,0,0);
                setSelectedAvatar((ImageButton)v);
                break;
            case R.id.a2:
                v.setPadding(0,0,0,0);
                setSelectedAvatar((ImageButton)v);
                break;
            case R.id.a3:
                v.setPadding(0,0,0,0);
                setSelectedAvatar((ImageButton)v);
                Toast.makeText(this, "selected a3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a4:
                v.setPadding(0,0,0,0);
                setSelectedAvatar((ImageButton)v);
                break;
            case R.id.a5:
                v.setPadding(0,0,0,0);
                setSelectedAvatar((ImageButton)v);
                break;
            case R.id.a6:
                v.setPadding(0,0,0,0);
                setSelectedAvatar((ImageButton)v);
                break;
            case R.id.a7:
                v.setPadding(0,0,0,0);
                setSelectedAvatar((ImageButton)v);
                break;
            case R.id.a8:
                v.setPadding(0,0,0,0);
                setSelectedAvatar((ImageButton)v);
                break;
        }
    }

    //This class sets the clicked avatar to be higlighted, and will deslect any other


    public String getSelectedAvatar() {
        return selectedAvatar;
    }

    public void setSelectedAvatar(ImageButton v) {
        String tag = v.getTag().toString();
        //selectedAvatar = Resources.getSystem(tag.toString());
        //taken from https://stackoverflow.com/questions/15488238/using-android-getidentifier
        selectedAvatar = tag;

    }

    public void clickedDone(View v){
        nameView = (EditText)findViewById(R.id.nameTextEdit);
        String name = nameView.getText().toString();
        String avatar = getSelectedAvatar();

        if(name.equals("")){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
        }
        else if(avatar==null){
            Toast.makeText(this, "Please select an avatar", Toast.LENGTH_LONG).show();
        }
        else {
            //EV: creating the unassigned user//getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Product
            String id = databasePeople.push().getKey();
            //creating a User Object
            User u = new User(name, avatar, id);
            //Saving the User
            databasePeople.child(id).setValue(u);
            Toast.makeText(this, "added user called " + u.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
