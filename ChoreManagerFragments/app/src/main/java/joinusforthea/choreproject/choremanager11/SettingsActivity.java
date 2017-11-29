package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String[] choreList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out", "Eat the garbage"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
