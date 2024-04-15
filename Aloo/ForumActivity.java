import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForumActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TimePicker timePicker;
    private EditText messageEditText;
    private Button postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.timePicker);
        messageEditText = findViewById(R.id.messageEditText);
        postButton = findViewById(R.id.postButton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = getSelectedDate();
                String selectedTime = getSelectedTime();
                String message = messageEditText.getText().toString();

                // Hier kannst du die Nachricht und die ausgewählte Zeit in deiner Datenbank oder Forum speichern.
                // Beispielhaft wird hier nur eine Toast-Nachricht angezeigt.
                Toast.makeText(ForumActivity.this, "Nachricht gepostet:\nDatum: " + selectedDate + "\nZeit: " + selectedTime + "\nNachricht: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getSelectedDate() {
        // Hier implementierst du die Logik, um das ausgewählte Datum zu erhalten.
        // Beispielhaft wird hier der Millisekunden-Wert vom CalendarView genommen.
        long selectedDateInMillis = calendarView.getDate();
        // Du solltest die Logik für die Umwandlung des Datums anpassen.
        return String.valueOf(selectedDateInMillis);
    }

    private String getSelectedTime() {
        // Hier implementierst du die Logik, um die ausgewählte Uhrzeit zu erhalten.
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        // Du solltest die Logik für die Formatierung der Uhrzeit anpassen.
        return hour + ":" + minute;
    }
}

//
////    2. In deiner ForumActivity.java kannst du den DialogFragment aufrufen:   java
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.DialogFragment;
//
//public class ForumActivity extends AppCompatActivity {
//
//    private Button pickTimeButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forum);
//
//        pickTimeButton = findViewById(R.id.pickTimeButton);
//
//        pickTimeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showTimePickerDialog();
//            }
//        });
//    }
//
//    private void showTimePickerDialog() {
//        DialogFragment newFragment = new TimePickerFragment();
//        newFragment.show(getSupportFragmentManager(), "timePicker");
//    }
//}
//
