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

public class TasksCustomAdapter extends ArrayAdapter<Task> {
    private Activity context;
    List<Task> tasks;
    ImageButton avatarButton;

    public TasksCustomAdapter(Activity context, List<Task> tasks) {
        super(context, R.layout.custom_task_layout, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //accessing the view
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.custom_task_layout, null, false);

        TextView choreName = (TextView) listViewItem.findViewById(R.id.choreNameTextView);

        //setting the chore name
        Task task = tasks.get(position);
        choreName.setText(task.getTaskName());

        avatarButton = (ImageButton) listViewItem.findViewById(R.id.avatarImageButton);
        String avtr = task.getUserAvatar();
        avatarButton.setTag(avtr);
        int resID = getContext().getResources().getIdentifier(""+avtr, "drawable", getContext().getPackageName());
        avatarButton.setBackgroundResource(resID);

        return listViewItem;
    }
}

