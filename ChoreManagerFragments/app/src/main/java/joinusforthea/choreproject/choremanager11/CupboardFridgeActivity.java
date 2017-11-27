package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
/**
 * Created by Digo9 on 2017-11-24.
 */

public class CupboardFridgeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        final String[] toolList = {"Eggs", "Milk", "Cheese", "Granola Bars"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupboard_fridge);

        ListView listView = (ListView) findViewById(R.id.foodList);
        CupboardFridgeAdapter adapter = new CupboardFridgeAdapter(this, toolList);
        listView.setAdapter(adapter);
    }
}