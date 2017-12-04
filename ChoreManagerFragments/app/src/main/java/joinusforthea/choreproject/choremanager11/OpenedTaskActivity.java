package joinusforthea.choreproject.choremanager11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by m-elbaz on 2017-11-28.
 */

public class OpenedTaskActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String taskName = getIntent().getStringExtra("Task Name");
        setTitle(taskName);
        setContentView(R.layout.activity_opened_task);
    }
}
