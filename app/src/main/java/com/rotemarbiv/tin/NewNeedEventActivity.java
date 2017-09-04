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
 * Created by laurescemama on 23/08/2017.
 */

public class NewNeedEventActivity extends AppCompatActivity {

    //TODO: split to 2 screens, one for when you can take out, and one for when you need

    public User selfUser; // the one utilizing the app
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
    public Button nextButton;

    final Context context = this;
    String needComments;
    private EventTime needTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_need_event);

        selfUser = (User)getIntent().getSerializableExtra("selfUser");

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

    /**
     * when next is pressed it means all details for need event are filled and need to pass on to
     * next activity which is newCanEventActicity.
     */
    public void nextPressed(View view){
        if (time != null && time.trim().length() > 0 &&
                (morningToggleButton.isChecked() || noonToggleButton.isChecked()
                        || eveningToggleButton.isChecked()))
        {
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

            Intent intent = new Intent(this, NewCanEventActivity.class);
            intent.putExtra("selfUser", this.selfUser);
            intent.putExtra("needTime", EventTime.createEventTime(date, time));
            intent.putExtra("needComments", comments);
            startActivity(intent);
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
