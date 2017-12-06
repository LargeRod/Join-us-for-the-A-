package joinusforthea.choreproject.choremanager11;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
//EV: inspired by Mitch Tabian and the labs
/**
 * Created by Digo9 on 2017-11-24.
 */

public class CupboardFridgeAdapter extends ArrayAdapter<CupboardFridgeItems> {
    private Context context;
    List<CupboardFridgeItems> items;

    public CupboardFridgeAdapter(Context context, List<CupboardFridgeItems> items) {
        super(context, R.layout.custom_fridge_layout, items);
        this.context = context;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(R.layout.custom_fridge_layout, parent, false);


        TextView choreNameTextField = (TextView) listViewItem.findViewById(R.id.foodNameTextView);

        CupboardFridgeItems item = items.get(position);
        choreNameTextField.setText(item.getfoodName());

        return listViewItem;
    }
}


