package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
 * Created by Digo9 on 2017-11-24.
 */

public class CupboardFridgeActivity extends AppCompatActivity {
    private ImageButton buttonAddFood;
    List<CupboardFridgeItems> products;
    DatabaseReference databaseItems;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseItems = FirebaseDatabase.getInstance().getReference("items");
        products = new ArrayList<>();
        listView = (ListView) findViewById(R.id.foodList);

        setContentView(R.layout.activity_cupboard_fridge);

        buttonAddFood = (ImageButton) findViewById(R.id.newFoodButton);
        buttonAddFood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addProduct();
            }
        });
    }


    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                products.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CupboardFridgeItems item = postSnapshot.getValue(CupboardFridgeItems.class);
                    products.add(item);
                }
                listView = (ListView) findViewById(R.id.foodList);
                CupboardFridgeAdapter adapter = new CupboardFridgeAdapter(CupboardFridgeActivity.this, products );
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addProduct() {
        EditText newItem = (EditText) findViewById(R.id.newFoodText);
        String name = newItem.getText().toString();

        if (!TextUtils.isEmpty(name)) {

            String id = databaseItems.push().getKey();

            CupboardFridgeItems item = new CupboardFridgeItems(name);

            databaseItems.child(id).setValue(item);

            newItem.setText("");

            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter an item name", Toast.LENGTH_LONG).show();
        }
    }

}