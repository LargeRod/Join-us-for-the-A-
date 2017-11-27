package joinusforthea.choreproject.choremanager11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
//EV: inspired by Mitch Tabian and the labs
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
        View view = inflater.inflate(R.layout.custom_people_layout, parent, false);
        TextView personName = (TextView) view.findViewById(R.id.personNameTextView);
        TextView numTasks = (TextView) view.findViewById(R.id.numTasksTextView);
        TextView nextTask = (TextView) view.findViewById(R.id.nextTaskTextView);
        ImageView avatar = (ImageView) view.findViewById(R.id.avaterImage);
        //only needed when changing the first one to dots
        ImageButton button = (ImageButton) view.findViewById(R.id.chatBubbleImage);

        if (position==0){
            //current user is displayed at top with dots instead of chat bubble
            button.setImageResource(R.drawable.dots);
        }
        else {

            personName.setText(peopleNames[position]);
            numTasks.setText("Number of tasks : "+(position*2-position%3));
            nextTask.setText("Next Task: you know what you have to do");
        }


        return view;
    }
}


