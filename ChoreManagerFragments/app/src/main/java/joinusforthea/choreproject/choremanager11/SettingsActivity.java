package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    DatabaseReference databasePeople;
    List<User> users;
    ListView peopleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databasePeople = FirebaseDatabase.getInstance().getReference("users");
        setContentView(R.layout.activity_settings);

    }



    public void addNewUser(View v) {
                //EV: creating the unassigned user//getting a unique id using push().getKey() method
        //it will create a unique id and we will use it as the Primary Key for our Product
        String id = databasePeople.push().getKey();
        //creating an Product Object
        User u = new User("Parker","@drawable/man4.png", id);
        //Saving the Product
        databasePeople.child(id).setValue(u);
        Toast.makeText(this, "added user called "+u.getName(), Toast.LENGTH_SHORT).show();
    }

    public void deleteUser(View v) {
        //IMPLEMENT DELETING A USER IN HERE
        //SHOULD CAUSE ALL THEIR TASKS TO BECOME "UNASSIGNED"
    }
}
