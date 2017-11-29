package joinusforthea.choreproject.choremanager11;

//EV: fragments inspired by Mitch Tabian
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {


    public ShoppingFragment() {
        // Required empty public constructor
    }

    private LinearLayout taskLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_shopping, container, false);

        return view ;
    }


    private void clickedCreateNewTask(View view) {
        Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1",Toast.LENGTH_SHORT).show();
    }
}
