package joinusforthea.choreproject.choremanager11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
//EV: inspired by Mitch Tabian
/**
 * Created by LargeRod on 2017-11-23.
 *
 */
//Note this code is just a placeholder for now

public class BroomClosetCustomAdapter extends ArrayAdapter<BroomClosetItems> {
    private final Context context;
    List<BroomClosetItems> items;

    public BroomClosetCustomAdapter(Context context, List<BroomClosetItems> items) {
        super(context, R.layout.custom_chore_layout, items);
        this.context = context;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_item_layout, parent, false);

        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.itemNameTextView);

        BroomClosetItems item = items.get(position);
        choreNameTextField.setText(item.getItemName());
        return rowView;
    }


}


