package joinusforthea.choreproject.choremanager11;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {


    public PeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_people, container, false);

        final String[] peopleList = {"Jim","Pam","Dwight","Stanley","Angela","Kevin","Your Mom","My Dog"};


        ListView listView= (ListView) view.findViewById(R.id.peopleList);
        PeopleCustomAdapter adapter = new PeopleCustomAdapter(getActivity(), peopleList);
        listView.setAdapter(adapter);


        return view ;
    }


    private void clickedCreateNewTask(View view) {
        Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1",Toast.LENGTH_SHORT).show();
    }
}
