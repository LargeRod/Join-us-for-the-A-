package joinusforthea.choreproject.choremanager11;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    static String date;
    String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        long millis = Long.valueOf(0);

        // Create a new instance of DatePickerDialog
        DatePickerDialog dpdialog = new DatePickerDialog(getActivity(), this, year, month, day);

        //convert date to long
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
        String stringDate = day+"-"+months[month]+"-"+year;
        try {
            Date d = f.parse(stringDate);
            millis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //dont allow earlier dates than it currently is
        dpdialog.getDatePicker().setMinDate(millis);

        //returns the dialog to the activity
        return dpdialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        String chosenMonth = months[month-1];
        date = chosenMonth+" "+day+", "+year;
        Toast.makeText(getActivity(), "Date from datePickerFragment: " + this.toString(), Toast.LENGTH_LONG).show();

    }

    public String toString() {
        return date;
    }
}
