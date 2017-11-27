package joinusforthea.choreproject.choremanager11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by admin on 07/11/2017.
 */

public class PeopleCustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] peopleNames;

    public PeopleCustomAdapter(Context context, String[] names) {
        super(context, R.layout.custom_people_layout, names);
        this.context = context;
        this.peopleNames = names;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_people_layout, parent, false);
        TextView personName = (TextView) rowView.findViewById(R.id.personNameTextView);
        TextView numTasks = (TextView) rowView.findViewById(R.id.numTasksTextView);
        TextView nextTask = (TextView) rowView.findViewById(R.id.nextTaskTextView);

        personName.setText(peopleNames[position]);
        numTasks.setText("300");
        nextTask.setText("you know what you have to do");


        return rowView;
    }
}


