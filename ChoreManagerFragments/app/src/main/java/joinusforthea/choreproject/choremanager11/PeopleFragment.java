package joinusforthea.choreproject.choremanager11;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
//EV: fragments inspired by Mitch Tabian
/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    //static final String[] peopleList = {"Jim","Pam","Dwight","Stanley","Angela","Kevin","Your Mom","My Dog"};

    DatabaseReference databasePeople;
    List<User> users;
    ListView peopleList;

    public PeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        databasePeople = FirebaseDatabase.getInstance().getReference("people");
        View view  = inflater.inflate(R.layout.fragment_people, container, false);



        ListView listView= (ListView) view.findViewById(R.id.peopleList);
        PeopleCustomAdapter adapter = new PeopleCustomAdapter(getActivity(), users);
        listView.setAdapter(adapter);


        return view ;
    }

//    public static List<User> getPeople(){
//        return users;
//    }


    private void addUser(View view) {

    }

    //taken from lab 5 firebase
    public void onStart() {
        super.onStart();
        //attaching value event listener
        databasePeople.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous people list?
                users.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting Users
                    User u = postSnapshot.getValue(User.class);
                    //adding user to the list
                    users.add(u);
                }

                //creating adapter
                //EV: USING NUMBER 2 AS A TEST
                PeopleCustomAdapter userAdapter = new PeopleCustomAdapter(getActivity(), users);
                //attaching adapter to the listview
                peopleList.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//end of addValueEventListener
    }//end of onStart

}
