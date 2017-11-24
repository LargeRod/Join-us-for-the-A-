package joinusforthea.choreproject.choremanager11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Digo9 on 2017-11-24.
 */

public class CupboardFridgeAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] myChores;

    public CupboardFridgeAdapter(Context context, String[] choreList) {
        super(context, R.layout.custom_fridge_layout, choreList);
        this.context = context;
        this.myChores = choreList;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_fridge_layout, parent, false);
        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.foodNameTextView);
        TextView choreFootnote = (TextView) rowView.findViewById(R.id.foodFootnoteTextView);

        choreNameTextField.setText(myChores[position]);
        choreNameTextField.setText(myChores[position]);
        choreFootnote.setText("Amount: 1");


        return rowView;
    }
}