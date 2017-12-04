package joinusforthea.choreproject.choremanager11;

/**
 * Created by Miguel Garzón on 2017-05-09.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskCustomAdapter extends ArrayAdapter<Task> {
    private Activity context;
    List<Task> tasks;

    public TaskCustomAdapter(Activity context, List<Task> tasks) {
        super(context, R.layout.custom_chore_layout, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //accessing the view
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.custom_chore_layout, null, true);

        TextView choreName = (TextView) listViewItem.findViewById(R.id.choreNameTextView);

        //setting the chore name
        Task task = tasks.get(position);
        choreName.setText(task.getTaskName());
        return listViewItem;
    }
}

