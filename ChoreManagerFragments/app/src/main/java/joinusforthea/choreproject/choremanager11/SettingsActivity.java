package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    public User switchUser(View v) {
        //IMPLEMENT SWITCH USER HERE
        User notRealUser = new User("ImNotReal", "PleaseFukinFixMe M8y");
        return notRealUser;
    }

    public void addNewUser(View v) {
        //IMPLEMENT ADDING NEW USERS HERE
        return ;
    }

    public void deleteUser(View v) {
        //IMPLEMENT DELETING A USER IN HERE

        //SHOULD CAUSE ALL THEIR TASKS TO BECOME "UNASSIGNED"
    }
}
