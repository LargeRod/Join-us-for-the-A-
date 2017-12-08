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
 * Created by LargeRod on 2017-11-22.
 */

public class BroomClosetActivity extends AppCompatActivity {
    private ImageButton buttonAddTools;
    List<BroomClosetItems> broomClosetItems;
    DatabaseReference databaseTasks;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseTasks = FirebaseDatabase.getInstance().getReference("broomClosesItems");
        broomClosetItems = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listOfTools);
        setContentView(R.layout.activity_broom_closet);

        buttonAddTools = (ImageButton) findViewById(R.id.newToolButton);
        buttonAddTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMaterial();
            }
        });

    }

    protected void onStart() {

        super.onStart();
        //attaching value event listener
        databaseTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                broomClosetItems.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    BroomClosetItems item = postSnapshot.getValue(BroomClosetItems.class);
                    broomClosetItems.add(item);
                }
                listView = (ListView) findViewById(R.id.listOfTools);
                BroomClosetCustomAdapter adapter = new BroomClosetCustomAdapter(BroomClosetActivity.this, broomClosetItems );
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView = (ListView) findViewById(R.id.listOfTools);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                BroomClosetItems material = broomClosetItems.get(i);
                String id = material.getId();

                showUpdateDeleteDialog(id, material.getItemName());
                return true;
            }
        });
    }

    public void addMaterial() {
        EditText newItem = (EditText) findViewById(R.id.newToolText);
        String name = newItem.getText().toString();

        if (!TextUtils.isEmpty(name)) {

            String id = databaseTasks.push().getKey();

            BroomClosetItems item = new BroomClosetItems(id, name);

            databaseTasks.child(id).setValue(item);

            newItem.setText("");

            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter an item name", Toast.LENGTH_LONG).show();
        }
    }

    public void updateMaterial(String id, String name) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("broomClosesItems").child(id);
        BroomClosetItems items = new BroomClosetItems(id, name);
        dR.setValue(items);

        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
    }

    public void deleteMaterial(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("broomClosesItems").child(id);
        dR.removeValue();

        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
    }

    private void showUpdateDeleteDialog(final String id, String itemName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.broom_closet_update_dialog, null);
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
                    updateMaterial(id, name);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMaterial(id);
                b.dismiss();
            }
        });
    }
}
