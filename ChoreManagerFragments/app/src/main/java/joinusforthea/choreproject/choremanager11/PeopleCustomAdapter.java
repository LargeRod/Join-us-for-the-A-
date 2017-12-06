package joinusforthea.choreproject.choremanager11;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
//EV: inspired by Mitch Tabian and the labs
/**
 * Created by admin on 07/11/2017.
 */

public class PeopleCustomAdapter extends ArrayAdapter<User> {
    private final Context context;
    private final List<User> user;

    public PeopleCustomAdapter(Activity context, List<User> names) {
        super(context, R.layout.custom_people_layout, names);
        this.context = context;
        this.user = names;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_people_layout, null, false);
        TextView personName = (TextView) view.findViewById(R.id.personNameTextView);
        TextView numTasks = (TextView) view.findViewById(R.id.numTasksTextView);
        TextView nextTask = (TextView) view.findViewById(R.id.nextTaskTextView);
        ImageView avatar = (ImageView) view.findViewById(R.id.avatarImage);
        //only needed when changing the first one to dots
        ImageButton button = (ImageButton) view.findViewById(R.id.chatBubbleImage);


        //EV: if else for when a current user can be selected and switched
//        if (position==0){
//            //current user is displayed at top with dots instead of chat bubble
//            button.setImageResource(R.drawable.dots);
//        }
//        else {
            String name = user.get(position).getAvatar();
            //this makes an int out of a string resource file
            String a = user.get(position).getAvatar();
            int resID = getContext().getResources().getIdentifier(""+a, "drawable", getContext().getPackageName());
            personName.setText(user.get(position).toString());
            view.findViewById(R.id.avatarImage).setBackgroundResource(resID);
            numTasks.setText("Number of tasks : "+(position*2-position%3));
            nextTask.setText("Next Task: you know what you have to do");
//        }


        return view;
    }
}


