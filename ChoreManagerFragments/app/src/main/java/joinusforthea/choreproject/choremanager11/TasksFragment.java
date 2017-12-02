package joinusforthea.choreproject.choremanager11;

//EV: fragments inspired by Mitch Tabian

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
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
    private ImageButton buttonAddTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_tasks, container, false);

        final String[] choreList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out", "Eat the garbage"};

        TextView textView = new TextView(getActivity());
        textView.setText("Task Fragment");

        ListView listView= (ListView) view.findViewById(R.id.taskList);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(getActivity(), choreList);
        listView.setAdapter(adapter);

        buttonAddTask = (ImageButton) view.findViewById(R.id.newTaskButton);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "clicked add task",Toast.LENGTH_SHORT).show();
            }
        });


        //getting reference to the custom chore layout
        View customView  = inflater.inflate(R.layout.custom_chore_layout, container, false);

        return view ;
    }

    //TODO THIS IS A TEST REMOVE WHEN DONE
    public void addItem(View view){
        GridLayout gridLayout = (GridLayout) view.findViewById(R.id.materialsGrid);
        CheckBox cb = new CheckBox(getContext());
        cb.setText("testing");
        gridLayout.addView(cb);
        Toast.makeText(getContext(), "CLICKED ADD ITEM", Toast.LENGTH_SHORT).show();
    }

}
