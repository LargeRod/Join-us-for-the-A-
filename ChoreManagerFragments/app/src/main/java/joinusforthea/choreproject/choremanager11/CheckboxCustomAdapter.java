package joinusforthea.choreproject.choremanager11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
//EV: inspired by Mitch Tabian and the labs
/**
 * Created by admin on 07/11/2017.
 */

public class CheckboxCustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] itemList;
    private ImageButton avatarButton;
    public CheckboxCustomAdapter(Context context, String[] items) {
        super(context, R.layout.custom_checkbox_layout, items);
        this.context = context;
        this.itemList = items;
    }



    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_checkbox_layout, parent, false);
        TextView item = (TextView) rowView.findViewById(R.id.itemNameTextView);
        item.setText(itemList[position]);

        return rowView;
    }

}


