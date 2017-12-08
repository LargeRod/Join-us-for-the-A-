package joinusforthea.choreproject.choremanager11;

//EV: fragments inspired by Mitch Tabian

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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



    List<String> groList  = new ArrayList<String>();
    List<String> matList  = new ArrayList<String>();


    DatabaseReference databaseMaterials;
    DatabaseReference databaseGroceries;

    ListView gridViewMaterials;
    GridView materialGrid;
    Button doneMaterialButton;
    EditText newMaterialName;
    ImageButton newMaterialButton;

    GridView groceryGrid;
    Button doneGroceryButton;
    EditText newGroceryName;
    ImageButton newGroceryButton;



    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_shopping, container, false);


        //Firebase
        databaseMaterials = FirebaseDatabase.getInstance().getReference("materials");
        databaseGroceries = FirebaseDatabase.getInstance().getReference("groceries");
        matList = new ArrayList<String>();

        //populating materials grid
         materialGrid  = (GridView) view.findViewById(R.id.materialsGrid);
        CheckboxCustomAdapter matAdapter = new CheckboxCustomAdapter(getActivity(), matList);
        materialGrid.setAdapter(matAdapter);

        //populating groceries grid
        groceryGrid = (GridView) view.findViewById(R.id.groceriesGrid);
        CheckboxCustomAdapter groAdapter = new CheckboxCustomAdapter(getActivity(), groList);
        groceryGrid.setAdapter(groAdapter);

        //edittext of adding a material
        newMaterialName = (EditText) view.findViewById(R.id.newMaterialName);

        //edittext of adding a grocery
        newGroceryName = (EditText) view.findViewById(R.id.newGroceryName);

        //adding on click listener for the newMaterialButton button
        newMaterialButton = (ImageButton) view.findViewById(R.id.newMaterialButton);
        newMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMat();
            }
        });//end of the onclick listener

        //adding on click listener for the newGroceryButton button
        newGroceryButton = (ImageButton) view.findViewById(R.id.newGroceryButton);
        newGroceryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGro();
            }
        });//end of the onclick listener

        return view ;
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
                CheckboxCustomAdapter shopAdapter = new CheckboxCustomAdapter(getActivity(), matList);
                //attaching adapter to the listview
                materialGrid.setAdapter(shopAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseGroceries.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous task list?
                groList.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting grocery
                    String gro = postSnapshot.getValue().toString();
                    //adding grocery to the list
                    groList.add(gro);
                }

                //creating adapter
                CheckboxCustomAdapter shopAdapter = new CheckboxCustomAdapter(getActivity(), groList);
                //attaching adapter to the listview
                groceryGrid.setAdapter(shopAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void addMat() {
        //getting the values to save
        String name = newMaterialName.getText().toString().trim();

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
            newMaterialName.setText("");

            //displaying a success toast
            Toast.makeText(getActivity(), "Material created", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(getActivity(), "Please enter a new material name", Toast.LENGTH_LONG).show();
        }
    }

    public void addGro() {
        //getting the values to save
        String name = newGroceryName.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Product
            String id = databaseGroceries.push().getKey();

            //creating an Product Object
            String grocery = new String(name);

            //Saving the Product
            databaseGroceries.child(id).setValue(grocery);

            //setting edittext to blank again
            newGroceryName.setText("");

            //displaying a success toast
            Toast.makeText(getActivity(), "Grocery created", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(getActivity(), "Please enter a new grocery name", Toast.LENGTH_LONG).show();
        }
    }


}
