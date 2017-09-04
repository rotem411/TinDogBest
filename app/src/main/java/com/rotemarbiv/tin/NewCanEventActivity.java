package com.rotemarbiv.tin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.rotemarbiv.tin.backend.BackendSimulator;
import com.rotemarbiv.tin.backend.EventTime;
import com.rotemarbiv.tin.backend.Match;

import java.util.Calendar;

/**
 * Created by laurescemama on 03/09/2017.
 */

public class NewCanEventActivity extends AppCompatActivity {

    public User user; // the one utilizing the app
    public String time;
    public String date;
    public String comments;

    private static BackendSimulator backend = BackendSimulator.getInstance();

    public DatePicker datePickerView;
    public EditText commentsInput;
    public ToggleButton morningToggleButton;
    public ToggleButton noonToggleButton;
    public ToggleButton eveningToggleButton;

    public Button cancelButton;
    public Button calculateButton;

    final Context context = this;
    String needComments;
    private EventTime needTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_can_event);

        needTime = (EventTime)getIntent().getSerializableExtra("needTime");
        needComments = getIntent().getStringExtra("needComments");
        user = (User)getIntent().getSerializableExtra("selfUser");

        //        getActionBar().setDisplayHomeAsUpEnabled(true);

        datePickerView = (DatePicker) findViewById(R.id.datePickerEvent);
        commentsInput = (EditText) findViewById(R.id.commentsInput);

        morningToggleButton = (ToggleButton)findViewById(R.id.morningToggleButton);
        noonToggleButton = (ToggleButton)findViewById(R.id.noonToggleButton);
        eveningToggleButton = (ToggleButton)findViewById(R.id.eveningToggleButton);

        CompoundButton.OnCheckedChangeListener togglePressed = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (buttonView == morningToggleButton) {
                        time = "morning";
                        noonToggleButton.setChecked(false);
                        eveningToggleButton.setChecked(false);
                    }
                    if (buttonView == noonToggleButton) {
                        time = "noon";
                        morningToggleButton.setChecked(false);
                        eveningToggleButton.setChecked(false);
                    }
                    if (buttonView == eveningToggleButton) {
                        time = "evening";
                        morningToggleButton.setChecked(false);
                        noonToggleButton.setChecked(false);
                    }
                }
            }
        };

        morningToggleButton.setOnCheckedChangeListener(togglePressed);
        noonToggleButton.setOnCheckedChangeListener(togglePressed);
        eveningToggleButton.setOnCheckedChangeListener(togglePressed);

//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            super.onOptionsItemSelected(item);
//            switch(item.getItemId()) {
//                case android.R.id.home:
//                    NavUtils.navigateUpFromSameTask(this);
//                    break;
//            }
//            return true;
//        }

//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent cancelIntent = new Intent(this, HomeActivity.class);
//                startActivity(cancelIntent);
//            }
//        });

    }

    public void calculatePressed(View view){
        if (time != null && time.trim().length() > 0 &&
                (morningToggleButton.isChecked() || noonToggleButton.isChecked()
                        || eveningToggleButton.isChecked())){

            int day = datePickerView.getDayOfMonth();
            int month = datePickerView.getMonth();
            int year = datePickerView.getYear();

            Calendar calendar= Calendar.getInstance();

            if (calendar.YEAR <= year && calendar.MONTH <= month && calendar.DAY_OF_MONTH <= day){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "You cannot choose a date that has passed", Toast.LENGTH_LONG);
                toast.show();
            }
            date = Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);

            comments = commentsInput.getText().toString();
            EventTime canTime = EventTime.createEventTime(date, time);
            Match matchFound = backend.findMatch(needTime, canTime,
                    User.convertUserToBackendUser(this.user));

            if (matchFound != null){
                //update the events comments
                (matchFound.getUserEvent()).setComments(comments);
                matchFound.getDogEvent().setComments(needComments);

                Event userEvent = Event.convertBackendEventToEvent(matchFound.getUserEvent());
                Event dogEvent = Event.convertBackendEventToEvent(matchFound.getDogEvent());

                Intent goodIntent = new Intent(this, ResultActivity.class);
                goodIntent.putExtra("userEvent", userEvent);
                goodIntent.putExtra("dogEvent", dogEvent);
                goodIntent.putExtra("selfUser", this.user);
                startActivity(goodIntent);
            }
            else{
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.bad_result_dialog);

                Button dialogButton = (Button) dialog.findViewById(R.id.okButton);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Fill all inputs in order to continue.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void cancelPressed(View view){
        startActivity(new Intent(this, HomeActivity.class));
    }
}

