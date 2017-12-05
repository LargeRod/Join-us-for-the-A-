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
 * Created by LargeRod on 2017-11-22.
 */

public class BroomClosetActivity extends AppCompatActivity {
    private ImageButton buttonAddTools;
    List<BroomClosetItems> materials;
    DatabaseReference databaseTasks;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseTasks = FirebaseDatabase.getInstance().getReference("material");
        materials = new ArrayList<>();
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
                materials.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    BroomClosetItems task = postSnapshot.getValue(BroomClosetItems.class);
                    materials.add(task);
                }
                listView = (ListView) findViewById(R.id.listOfTools);
                BroomClosetCustomAdapter adapter = new BroomClosetCustomAdapter(BroomClosetActivity.this, materials );
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addMaterial() {
        EditText newItem = (EditText) findViewById(R.id.newToolText);
        String name = newItem.getText().toString();

        if (!TextUtils.isEmpty(name)) {

            String id = databaseTasks.push().getKey();

            BroomClosetItems item = new BroomClosetItems(name);

            databaseTasks.child(id).setValue(item);

            newItem.setText("");

            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter an item name", Toast.LENGTH_LONG).show();
        }
    }
}
