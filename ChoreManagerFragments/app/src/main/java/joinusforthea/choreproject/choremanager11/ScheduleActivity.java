package joinusforthea.choreproject.choremanager11;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        CalendarView calendarView = (CalendarView) findViewById(R.id.view_calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth+"/"+month+"/"+year, Toast.LENGTH_LONG);
            }
        });

        final EditText taskEditText = (EditText) findViewById(R.id.newTaskText);
        final Button addTaskButton = (Button) findViewById(R.id.newTaskButton);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskEditText.getText().toString().equals("")) {
                    String taskName = taskEditText.getText().toString();
                    Intent intent = new Intent(ScheduleActivity.this, TaskAddActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ScheduleActivity.this, "Please enter a new task name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
