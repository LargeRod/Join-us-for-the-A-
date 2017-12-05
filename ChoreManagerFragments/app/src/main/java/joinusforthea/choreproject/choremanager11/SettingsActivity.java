package joinusforthea.choreproject.choremanager11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    //DatabaseReference databasePeople;
    List<User> users;
    ListView peopleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //databasePeople = FirebaseDatabase.getInstance().getReference("users");
        setContentView(R.layout.activity_settings);

    }



    public void addNewUser(View v) {
        Intent intent = new Intent(SettingsActivity.this, CreateUserActivity.class);
        startActivity(intent);
    }

//    public void deleteUser(View v) {
//        //IMPLEMENT DELETING A USER IN HERE
//        //SHOULD CAUSE ALL THEIR TASKS TO BECOME "UNASSIGNED"
//    }
}
