package joinusforthea.choreproject.choremanager11;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
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

    }

    public void openTaskActivity (View v) {
        Intent intent = new Intent(ScheduleActivity.this, OpenedTaskActivity.class);
        startActivity(intent);
    }
}
