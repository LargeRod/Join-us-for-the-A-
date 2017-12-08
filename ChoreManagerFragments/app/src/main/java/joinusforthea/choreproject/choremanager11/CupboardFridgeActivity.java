package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
                addFood();
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

        listView = (ListView) findViewById(R.id.foodList);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                CupboardFridgeItems food = products.get(position);
                String id = food.getId();

                showUpdateDeleteDialog(id, food.getfoodName());
                return true;
            }
        });
    }

    private void addFood() {
        EditText newItem = (EditText) findViewById(R.id.newFoodText);
        String name = newItem.getText().toString();

        if (!TextUtils.isEmpty(name)) {

            String id = databaseItems.push().getKey();

            CupboardFridgeItems item = new CupboardFridgeItems(id, name);

            databaseItems.child(id).setValue(item);

            Toast.makeText(this, "added "+id, Toast.LENGTH_SHORT).show();

            newItem.setText("");

            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter an item name", Toast.LENGTH_LONG).show();
        }
    }

    public void updateFood(String id, String name) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("items").child(id);
        CupboardFridgeItems items = new CupboardFridgeItems (id, name);
        dR.setValue(items);

    }

    public void deleteFood(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("items").child(id);
        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Deleting "+ dR.getKey(), Toast.LENGTH_LONG).show();
    }

    private void showUpdateDeleteDialog(final String id, String itemName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fridge_update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);

        dialogBuilder.setTitle(itemName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();

                if (!TextUtils.isEmpty(name)) {
                    updateFood(id, name);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFood(id);
                b.dismiss();
            }
        });
    }

}