


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
import android.widget.TextView;
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
public class TasksFragment extends Fragment{


    public TasksFragment() {
        // Required empty public constructor
    }

    //EV: for firebase
    DatabaseReference databaseTasks;
    List<Task> tasks;
    ListView listViewTask;
    EditText newTaskName;
    ImageButton avatarButton;
    View customView;
    LinearLayout choreTextLayout;
    TextView taskName;
    Task currentTask;
    User currentUser;
    View view;



    private LinearLayout taskLayout;
    private ImageButton buttonAddTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //getting reference to the custom chore layout and setting listener
        customView = inflater.inflate(R.layout.custom_task_layout, container, false);
        choreTextLayout = (LinearLayout) customView.findViewById(R.id.choreTextLayout);

        databaseTasks = FirebaseDatabase.getInstance().getReference("tasks");
        view = inflater.inflate(R.layout.fragment_tasks, container, false);
        tasks = new ArrayList<>();
        listViewTask = (ListView) view.findViewById(R.id.taskList);

        newTaskName = (EditText) view.findViewById(R.id.newTaskName);

        //adding on click listener for the buttonAddTask button
        buttonAddTask = (ImageButton) view.findViewById(R.id.newTaskButton);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //called from a task being clicked
                //casting the findViewById to a text view, then getting text and converting to string
                if(!newTaskName.getText().toString().isEmpty()){
                    String taskName = newTaskName.getText().toString();
                    Intent intent = new Intent(getActivity(), TaskAddActivity.class);

                    Task task = addTask();
                    //add task returns null if the name isnt entered, only start activity if
                    //theres a new task name

                    //set the avatar in the task list view
                    avatarButton = (ImageButton) customView.findViewById(R.id.avatarImageButton);
                    String avtr = task.getUserAvatar();
                    int resID = getResources().getIdentifier(""+avtr, "drawable", getActivity().getPackageName());
                    avatarButton.setBackgroundResource(resID);


                    if(task!=null) {
                        //intent.putExtra passes task to the
                        intent.putExtra("passedTaskName",taskName);
                        startActivity(intent);

                    }
                }
                else{
                    Toast.makeText(getActivity(), "Please enter a new task name", Toast.LENGTH_LONG).show();
                }
            }

        });//end of the onclick listener




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
                TasksCustomAdapter taskAdapter = new TasksCustomAdapter(getActivity(), tasks);
                //attaching adapter to the listview
                listViewTask.setAdapter(taskAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//end of addValueEventListener
    }//end of onStart


    public Task addTask() {
        //getting the values to save
        String name = newTaskName.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Product
            String id = databaseTasks.push().getKey();

            //creating an Product Object
            Task task = new Task(name, id);

            //Saving the Product
            databaseTasks.child(id).setValue(task);

            //setting edittext to blank again
            newTaskName.setText("");

            return task;
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(getActivity(), "Please enter a new task name", Toast.LENGTH_LONG).show();
        }
        return null;
    }


    public void openTaskInfo(View view){
        //adding on click listener for clicking the task info
        choreTextLayout = (LinearLayout) customView.findViewById(R.id.choreTextLayout);
        TextView choreNameTextView =(TextView) view.findViewById(R.id.choreNameTextView);
        String taskName = choreNameTextView.getText().toString();
        Intent intent = new Intent(getActivity(), OpenedTaskActivity.class);
        intent.putExtra("passedTaskName",taskName);
        startActivity(intent);
    }
}
