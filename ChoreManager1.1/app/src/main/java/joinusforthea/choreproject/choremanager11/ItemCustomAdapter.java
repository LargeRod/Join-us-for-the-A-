package joinusforthea.choreproject.choremanager11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by LargeRod on 2017-11-23.
 *
 */
//Note this code is just a placeholder for now

public class ItemCustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] myChores;

    public ItemCustomAdapter(Context context, String[] choreList) {
        super(context, R.layout.custom_chore_layout, choreList);
        this.context = context;
        this.myChores = choreList;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_item_layout, parent, false);
        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.itemNameTextView);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);
        TextView choreFootnote = (TextView) rowView.findViewById(R.id.itemFootnoteTextView);

        choreNameTextField.setText(myChores[position]);
        choreNameTextField.setText(myChores[position]);
        choreFootnote.setText("Amount: 1");


        return rowView;
    }
}
