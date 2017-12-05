package joinusforthea.choreproject.choremanager11;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by m-elbaz on 2017-11-28.
 */

public class OpenedTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String taskName = getIntent().getStringExtra("Task Name");
        setTitle(taskName);
        setContentView(R.layout.activity_opened_task);
    }

    //calling the DatePickerFragment to display a date picker dialog
    public void showDatePickerDialog(View v) {

        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");

        //MUST OBTAIN DATE FROM DATEPICKERFRAGMENT AND CALL SETDATE WITH IT TO SET
        //IT IN THE VIEW
    }

    public void setDate(String date) {
        TextView dateText = (TextView) findViewById(R.id.dateText);
        dateText.setText(date);
    }

}
