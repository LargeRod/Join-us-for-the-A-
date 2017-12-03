package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Digo9 on 2017-11-24.
 */
//!!!!!!WARNING not working as intended! DO NOT COPY CODE FROM HERE YET!!!!!!!!!!
    //
    //
    //
    //
    //
public class CupboardFridgeActivity extends AppCompatActivity {
    String[] toolList = {"Eggs", "Milk", "Cheese", "Granola Bars"};
    private ImageButton buttonAddFood;

    protected void onCreate(final Bundle savedInstanceState) {


//        final List<String> toolList = new ArrayList<String>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupboard_fridge);

        buttonAddFood = (ImageButton) findViewById(R.id.newFoodButton);
        buttonAddFood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText newItem = (EditText) findViewById(R.id.newFoodText);
                String message = newItem.getText().toString();
                String[] newToolList = Arrays.copyOf(toolList, toolList.length + 1 );
                newToolList[newToolList.length -1 ] = message;
                Toast.makeText(getApplicationContext(), "Added new item", Toast.LENGTH_SHORT).show();
                toolList = newToolList;

                ListView listView = (ListView) findViewById(R.id.foodList);
                CupboardFridgeAdapter adapter = new CupboardFridgeAdapter(getApplicationContext(), toolList );
                listView.setAdapter(adapter);

            }
        });
        ListView listView = (ListView) findViewById(R.id.foodList);
        CupboardFridgeAdapter adapter = new CupboardFridgeAdapter(this, toolList );

        listView.setAdapter(adapter);

    }


    public void addNewItem(Bundle savedInstanceState) {
        String[] toolList = {"Eggs", "Milk", "Cheese", "Granola Bars"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupboard_fridge);
        ListView listView = (ListView) findViewById(R.id.foodList);
        CupboardFridgeAdapter adapter = new CupboardFridgeAdapter(this, toolList);
        listView.setAdapter(adapter);
    }
}