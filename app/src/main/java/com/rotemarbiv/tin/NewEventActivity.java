package com.rotemarbiv.tin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;

import static android.support.v7.appcompat.R.styleable.MenuItem;

/**
 * Created by laurescemama on 23/08/2017.
 */

public class NewEventActivity extends AppCompatActivity {

    //TODO: split to 2 screens, one for when you can take out, and one for when you need

    public User user; // the one utilizing the app
    public String time;
    public String date;
    public String comments;
    public boolean isItMe;

    public DatePicker datePickerView;
    public EditText commentsInput;
    public ToggleButton personToggleButton;
    public ToggleButton dogToggleButton;
    public ToggleButton morningToggleButton;
    public ToggleButton noonToggleButton;
    public ToggleButton eveningToggleButton;

    public Button cancelButton;
    public Button calculateButton;

    final Context context = this;

// bla info for testing
    public User laureUser = new User("Laure Scemama","Doggy", "Yafo 3", true);
    public User galUser = new User("Gal Nachmana", "Doggy2", "Yafo 4", true);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);

//        getActionBar().setDisplayHomeAsUpEnabled(true);

        datePickerView = (DatePicker) findViewById(R.id.datePickerEvent);

        commentsInput = (EditText) findViewById(R.id.commentsInput);

        personToggleButton = (ToggleButton)findViewById(R.id.userToggleButton);
        dogToggleButton = (ToggleButton)findViewById(R.id.dogToggleButton);

        morningToggleButton = (ToggleButton)findViewById(R.id.morningToggleButton);
        noonToggleButton = (ToggleButton)findViewById(R.id.noonToggleButton);
        eveningToggleButton = (ToggleButton)findViewById(R.id.eveningToggleButton);

        CompoundButton.OnCheckedChangeListener togglePressed = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (buttonView == personToggleButton) {
                        isItMe = true;
                        dogToggleButton.setChecked(false);
                    }
                    if (buttonView == dogToggleButton) {
                        isItMe = false;
                        personToggleButton.setChecked(false);
                    }
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

        personToggleButton.setOnCheckedChangeListener(togglePressed);
        dogToggleButton.setOnCheckedChangeListener(togglePressed);
        morningToggleButton.setOnCheckedChangeListener(togglePressed);
        noonToggleButton.setOnCheckedChangeListener(togglePressed);
        eveningToggleButton.setOnCheckedChangeListener(togglePressed);

//
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
        if (     time != null && time.trim().length() > 0 &&
                (personToggleButton.isChecked() || dogToggleButton.isChecked()) &&
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
            date = Integer.toString(day)+"."+Integer.toString(month)+"."+Integer.toString(year);

            comments = commentsInput.getText().toString();

            // bla info just for testing

            Event newEventServerResult = new Event(laureUser, galUser, "Tue, July 8th", "noon", true, 2);;

            if (personToggleButton.isChecked() && isItMe) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "You are available for a walk on "+date+" at "+time, Toast.LENGTH_LONG);
                toast.show();

                ///  here pass the server the request for creating a walk for user

                if (newEventServerResult != null){
                    Intent goodIntent = new Intent(this, ResultActivity.class);
                    goodIntent.putExtra("newEventFound", newEventServerResult);
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
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Doggy needs a walk pal on "+date+" at "+time, Toast.LENGTH_LONG);
                toast.show();

                ///  here pass the server the request for creating a walk for user's dog

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



//TODO: notice when dog or person buttoni s pressed