package joinusforthea.choreproject.choremanager11;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by LargeRod on 2017-11-22.
 */

public class BroomClosetActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        final String[] toolList = {"Hammer", "Broom", "Shovel"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.broom_closet);

        ListView listView = (ListView) findViewById(R.id.itemList);
        ItemCustomAdapter adapter = new ItemCustomAdapter(this, toolList);
        listView.setAdapter(adapter);



    }
}