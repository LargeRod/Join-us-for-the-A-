package joinusforthea.choreproject.choremanager11;

//EV: fragments inspired by Mitch Tabian

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {


    String[] matList = {"Plunger", "Laxatives", "Toilet seat","Bleach","Rubber gloves","Gas mask", "Laxatives", "Toilet seat","Bleach","Rubber gloves","Gas mask", "Laxatives", "Toilet seat","Bleach","Rubber gloves","Gas mask", "Laxatives", "Toilet seat","Bleach","Rubber gloves","Gas mask", "Laxatives", "Toilet seat","Bleach","Rubber gloves","Gas mask", "Laxatives", "Toilet seat","Bleach","Rubber gloves","Gas mask", "Laxatives", "Toilet seat","Bleach","Rubber gloves","Gas mask", "Laxatives", "Toilet seat","Bleach","Rubber gloves","Gas mask"};

    String[] foodList = {"Eggs", "Milk", "Cheese", "Food grade kitty litter", "Granola Bars","Bleach","Diet Bleach"};

    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_shopping, container, false);

        //populating materials grid
        GridView matGrid = (GridView) view.findViewById(R.id.materialsGrid);
        CheckboxCustomAdapter matAdapter = new CheckboxCustomAdapter(getActivity(), matList);
        matGrid.setAdapter(matAdapter);

        //populating groceries grid
        GridView groGrid = (GridView) view.findViewById(R.id.groceriesGrid);
        CheckboxCustomAdapter groAdapter = new CheckboxCustomAdapter(getActivity(), foodList);
        groGrid.setAdapter(groAdapter);

        return view ;
    }



}
