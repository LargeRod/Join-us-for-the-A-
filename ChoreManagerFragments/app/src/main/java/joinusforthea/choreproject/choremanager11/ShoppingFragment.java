package joinusforthea.choreproject.choremanager11;

//EV: fragments inspired by Mitch Tabian

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
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
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {



    List<String> foodList  = new ArrayList<String>();
    List<String> matList  = new ArrayList<String>();


    DatabaseReference databaseMaterials;

    ListView gridViewMaterials;
    GridView matGrid;
    private ImageButton addMaterial;
    EditText materialEditText;

    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_shopping, container, false);


        //Firebase
        databaseMaterials = FirebaseDatabase.getInstance().getReference("materials");
        matList = new ArrayList<String>();

        //populating materials grid
         matGrid = (GridView) view.findViewById(R.id.materialsGrid);
        CheckboxCustomAdapter matAdapter = new CheckboxCustomAdapter(getActivity(), matList);
        matGrid.setAdapter(matAdapter);

        //populating groceries grid
        GridView groGrid = (GridView) view.findViewById(R.id.groceriesGrid);
        CheckboxCustomAdapter groAdapter = new CheckboxCustomAdapter(getActivity(), foodList);
        groGrid.setAdapter(groAdapter);

        //edittext of adding a material/grocery
        materialEditText = (EditText) view.findViewById(R.id.newItemName);

        //adding on click listener for the buttonAddTask button
        addMaterial = (ImageButton) view.findViewById(R.id.newItemButton);
        addMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getActivity(), TaskAddActivity.class);
                //getActivity().startActivity(intent);
                addMat();
            }
        });//end of the onclick listener

        return view ;
    }
//Attempt at implementing popup for choosing Material vs Grocery
    public void buttonClicked(View view) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.shopping_list_dialogue, null);
        //final EditText etUsername = alertLayout.findViewById(R.id.testBoy);


        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Info");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //String user = etUsername.getText().toString();

                Toast.makeText(getActivity().getBaseContext(), "Username: " , Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }





    public void onStart() {
        super.onStart();
        //attaching value event listener
        databaseMaterials.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous task list?
                matList.clear();

                //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting task
                    String mat = postSnapshot.getValue().toString();
                    //adding task to the list
                    matList.add(mat);
                }

                //creating adapter
                //EV: USING NUMBER 2 AS A TEST
                CheckboxCustomAdapter shopAdapter = new CheckboxCustomAdapter(getActivity(), matList);
                //attaching adapter to the listview
                matGrid.setAdapter(shopAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void addMat() {
        //getting the values to save
        String name = materialEditText.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Product
            String id = databaseMaterials.push().getKey();

            //creating an Product Object
            String material = new String(name);

            //Saving the Product
            databaseMaterials.child(id).setValue(material);

            //setting edittext to blank again
            materialEditText.setText("");

            //displaying a success toast
            Toast.makeText(getActivity(), "Material created", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(getActivity(), "Please enter a new material name", Toast.LENGTH_LONG).show();
        }
    }


}
