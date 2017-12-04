//TODO: @Emilie make the add task screen add a real task
//TODO: @Emilie add firebase to all the other garbages if possible (y)


package joinusforthea.choreproject.choremanager11;

//EV: fragments inspired by Mitch Tabian

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
public class TasksFragment extends Fragment {


    public TasksFragment() {
        // Required empty public constructor
    }

    //EV: for firebase
    DatabaseReference databaseTasks;
    List<Task> tasks;
    ListView listViewTask;
    EditText taskNameEditTextView;

    private LinearLayout taskLayout;
    private ImageButton buttonAddTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //firebase instantiations
        databaseTasks = FirebaseDatabase.getInstance().getReference("tasks");
        tasks = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        listViewTask = (ListView) view.findViewById(R.id.taskList);

        taskNameEditTextView = (EditText) view.findViewById(R.id.newTaskName);

        //adding on click listener for the buttonAddTask button
        buttonAddTask = (ImageButton) view.findViewById(R.id.newTaskButton);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TaskAddActivity.class);
                getActivity().startActivity(intent);
                addTask();
            }
        });//end of the onclick listener


        //getting reference to the custom chore layout
        View customView = inflater.inflate(R.layout.custom_chore_layout, container, false);

        return view;
    }

    //taken from lab 5 firebase
    public void onStart() {
        super.onStart();
        //attaching value event listener
        databaseTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous task list?
                tasks.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting task
                    Task task = postSnapshot.getValue(Task.class);
                    //adding task to the list
                    tasks.add(task);
                }

                //creating adapter
                //EV: USING NUMBER 2 AS A TEST
                TaskCustomAdapter taskAdapter = new TaskCustomAdapter(getActivity(), tasks);
                //attaching adapter to the listview
                listViewTask.setAdapter(taskAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//    public void addItem(View view){
//       Toast.makeText(getContext(), "CLICKED ADD ITEM", Toast.LENGTH_SHORT).show();
//    }

    public void addTask() {
        //getting the values to save
        String name = taskNameEditTextView.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Product
            String id = databaseTasks.push().getKey();

            //creating an Product Object
            Task task = new Task(name);

            //Saving the Product
            databaseTasks.child(id).setValue(task);

            //setting edittext to blank again
            taskNameEditTextView.setText("");

            //displaying a success toast
            Toast.makeText(getActivity(), "Task created", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(getActivity(), "Please enter a new task name", Toast.LENGTH_LONG).show();
        }
    }


}
