package com.rotemarbiv.tin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

/**
 * Created by dafnaarbiv on 25/07/2017.
 */

public class EventActivity extends AppCompatActivity {

    public TextView time;
    public TextView date;
    public TextView walkerName ;
    public TextView dogName;
    public MapView map;
    public EditText special;
    public Button walkDone;

    public User selfUser;
    public Event event;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.event);

        event = (Event) getIntent().getSerializableExtra("eventClicked");
        selfUser = (User) getIntent().getSerializableExtra("selfUser");

        if (event.isItMe){
            walkDone = (Button)findViewById(R.id.walkDoneButton);
            walkDone.setVisibility(View.VISIBLE);
            walkDone.setClickable(true);
        }

        time = (TextView) findViewById(R.id.timeTitleLabel);
        date = (TextView) findViewById(R.id.dateTitleLabel);
        walkerName = (TextView) findViewById(R.id.walkerName);
        dogName = (TextView) findViewById(R.id.dogName);
//        map = (MapView) findViewById(R.id.mapView);
        special = (EditText) findViewById(R.id.commentsInput);

        time.setText(event.getTimeStr());
        date.setText(event.getDateStr());
        walkerName.setText(event.walker.getFullName());
        dogName.setText((event.dog.dogName));
        special.setText(event.comments);

    }

        public void walkDoneClicked(View view){
            // send the server information that this event can be deleted
            createNotification(view);
            Intent intent = new Intent(EventActivity.this, HomeActivity.class);
            intent.putExtra("selfUser", selfUser);
            startActivity(intent);
        }

        public void createNotification(View view){
            // Prepare intent which is triggered if the
            // notification is selected
            Intent intent = new Intent(this, HomeActivity.class);

            PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

            // Build notification
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Walk Done!")
                    .setContentText(event.walker.getFullName().toString()+" just took "+
                            event.dog.dogName.toString()+" for a walk")
                    .setSmallIcon(R.mipmap.walk_dog_icon)
                    .setContentIntent(pIntent).build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // hide the notification after its selected
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);

        }
}
