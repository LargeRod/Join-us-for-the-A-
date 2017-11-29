package joinusforthea.choreproject.choremanager11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jarro on 2017-11-28.
 */

public class MessageCustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] messages;

    public MessageCustomAdapter(Context context, String[] messageList) {
        super(context, R.layout.custom_message_layout, messageList);
        this.context = context;
        this.messages = messageList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_message_layout, parent, false);
        TextView messageTextField = (TextView) rowView.findViewById(R.id.message);
        TextView timeSent = (TextView) rowView.findViewById(R.id.timeSent);
        TextView name = (TextView) rowView.findViewById(R.id.name);

        messageTextField.setText(messages[position]);
        name.setText(messages[position]);
        timeSent.setText(messages[position]);


        return rowView;
    }
}