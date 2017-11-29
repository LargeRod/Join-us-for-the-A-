package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
=======
import android.view.View;
import android.widget.ListView;
>>>>>>> b5a4411a54337629c1fad0ea248a19398618c4cf

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
<<<<<<< HEAD
=======

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
>>>>>>> b5a4411a54337629c1fad0ea248a19398618c4cf
    }
}
