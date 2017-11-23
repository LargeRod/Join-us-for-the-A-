package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String[] choreList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out", "Eat the garbage"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //before having changed the signature to a fragment
        ListView listView= (ListView) findViewById(R.id.list);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this, choreList);
        listView.setAdapter(adapter);

    }
}
