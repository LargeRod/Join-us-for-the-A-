package joinusforthea.choreproject.choremanager11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 07/11/2017.
 */

public class ChoreCustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] myChores;

    public ChoreCustomAdapter(TasksFragment context, String[] choreList) {
        super(context, R.layout.custom_chore_layout, choreList);
        this.context = context;
        this.myChores = choreList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_chore_layout, parent, false);
        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.choreNameTextView);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);
        TextView choreFootnote = (TextView) rowView.findViewById(R.id.choreFootnoteTextView);

        choreNameTextField.setText(myChores[position]);
        choreFootnote.setText(myChores[position]+ " is a chore");


        return rowView;
    }
}


