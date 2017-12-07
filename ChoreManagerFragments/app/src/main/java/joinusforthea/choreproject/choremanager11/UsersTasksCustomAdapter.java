package joinusforthea.choreproject.choremanager11;

/**
 * Created by Miguel Garzón on 2017-05-09.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class UsersTasksCustomAdapter extends ArrayAdapter<Task> {
    private final User user;
    private Activity context;
    List<Task> tasks;
    ImageButton avatarButton;

    public UsersTasksCustomAdapter(Activity context, List<Task> tasks, User user) {
        super(context, R.layout.custom_task_layout, tasks);
        this.context = context;
        this.tasks = tasks;
        this.user = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //accessing the view
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.custom_task_layout, null, true);

        TextView choreName = (TextView) listViewItem.findViewById(R.id.choreNameTextView);


            //setting the chore name
        Task task = tasks.get(position);
        if(task.getAssignedTo().equals(user.getName())) {
            choreName.setText(task.getTaskName());
            avatarButton = (ImageButton) listViewItem.findViewById(R.id.avatarImageButton);
            String avtr = task.getAssignedTo().getAvatar();
            int resID = getContext().getResources().getIdentifier("" + avtr, "drawable", getContext().getPackageName());
            avatarButton.setBackgroundResource(resID);
        }
        return listViewItem;
    }
}

