package joinusforthea.choreproject.choremanager11;

//EV: fragments inspired by Mitch Tabian
import android.content.Intent;
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


    private void openTaskActivity(View view) {
        //open opened task activity in this method
//        Intent intent = new Intent(ScheduleActivity.this, OpenedTaskActivity.class);
//        startActivity(intent);
    }
}
