package joinusforthea.choreproject.choremanager11;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class TasksFragment extends Fragment {


    public TasksFragment() {
        // Required empty public constructor
    }

    private LinearLayout taskLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        final String[] choreList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out", "Eat the garbage"};

        TextView textView = new TextView(getActivity());
        textView.setText("Task Fragment");

        // doenst work with fragments
       /* ListView listView= (ListView) container.findViewById(R.id.taskList);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this, choreList);
        listView.setAdapter(adapter);*/

        return textView;
    }


    private void clickedCreateNewTask(View view) {
        Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1",Toast.LENGTH_SHORT).show();
    }
}
