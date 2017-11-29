package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by jarro on 2017-11-28.
 */

public class MessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String[] messageList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out", "Eat the garbage"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        ListView listView = (ListView) findViewById(R.id.messages);
        MessageCustomAdapter adapter = new MessageCustomAdapter(this, messageList);
        listView.setAdapter(adapter);

    }
}
