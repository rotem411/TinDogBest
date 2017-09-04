package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rotemarbiv.tin.backend.BackendSimulator;

import java.util.ArrayList;

/**
 * Created by dafnaarbiv on 22/07/2017.
 */

public class HomeActivity extends AppCompatActivity {

//    public Event a = new Event(laureUser, rotemUser, "Tue, July 7th", "noon", true, 1);
//    public Event b = new Event(laureUser, galUser, "Tue, July 7th", "morning", true, 2);
//    public Event c =  new Event(galUser, laureUser, "Wed, July 8th", "noon", false, 1);
//    public Event d =  new Event(rotemUser, laureUser, "Thu, July 8th", "evening",  false, 2);

    private User selfUser;
    private static BackendSimulator backend = BackendSimulator.getInstance();

//    private ArrayList<Event> myEvents = new ArrayList<Event>(); //todo: convert from backend.getTasks()
//    private ArrayList<Event> dogsEvents = new ArrayList<Event>();
//    private ArrayList<Event> myPendingEvents = new ArrayList<Event>();

    private ArrayList<Event> myEvents;
    private ArrayList<Event> dogsEvents;
    private ArrayList<Event> myPendingEvents;

    private FloatingActionButton newEventButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
//        myEvents.add(a);
//        myEvents.add(b);
//        dogsEvents.add(c);
//        dogsEvents.add(d);
//        myPendingEvents.add(d);
        selfUser = (User) getIntent().getSerializableExtra("selfUser");
        com.rotemarbiv.tin.backend.User backendUser = backend.getUser(selfUser.getEmail());

        myEvents = Event.convertBackendEventListToEventList(backendUser.getDashboard().getUserEvents().getEvents());
        dogsEvents = Event.convertBackendEventListToEventList(backendUser.getDashboard().getDogEvents().getEvents());
        myPendingEvents = Event.convertBackendEventListToEventList(backendUser.getDashboard().getPendingEvents().getEvents());

//        Event myToDelete = (Event) getIntent().getSerializableExtra("myEventToDelete");
//        Event dogToDelete = (Event) getIntent().getSerializableExtra("dogEventToDelete");
//        Event toAdd = (Event) getIntent().getSerializableExtra("newEventFound");
//
//        if(myToDelete != null){
//
//            myEvents.remove(myToDelete.index);
//        }
//        if(dogToDelete != null){
//            dogsEvents.remove(dogToDelete.index);
//        }
//
//        if(toAdd != null){
//            if (toAdd.isItMe){
//                myEvents.add(toAdd);
//            }
//            else{
//                dogsEvents.add(toAdd);
//            }
//        }

        ListView myEventsListView = (ListView)findViewById(R.id.myEventsList);
        ListView dogsEventsListView = (ListView)findViewById(R.id.dogsEventsList);
        ListView myPendingEventsListView = (ListView)findViewById(R.id.myPendingEventsList);

        EventAdapter myEventsAdapter = new EventAdapter(this, R.layout.event_item_list, myEvents);
        myEventsListView.setAdapter(myEventsAdapter);
        EventAdapter dogsEventsAdapter = new EventAdapter(this, R.layout.event_item_list, dogsEvents);
        dogsEventsListView.setAdapter(dogsEventsAdapter);


        myEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent , View view, int position, long id){

                int curEventID = view.getId();
                Toast.makeText(getApplicationContext(), "cur Event id "+ curEventID,Toast.LENGTH_LONG ).show();

                System.out.println(myEvents.get(position).getEventTitle()+"   "+myEvents.get(position).isItMe);
                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
                intent.putExtra("eventClicked", myEvents.get(position));
                startActivity(intent);

            }
        });

        dogsEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent , View view, int position, long id){

                int curEventID = view.getId();
                Toast.makeText(getApplicationContext(), "cur Event id "+ curEventID,Toast.LENGTH_LONG ).show();

                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
                intent.putExtra("eventClicked", dogsEvents.get(position));
                startActivity(intent);

            }
        });

        ArrayAdapter myPendingAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, myPendingEvents);
        myPendingEventsListView.setAdapter(myPendingAdapter);

        myPendingEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent , View view, int position, long id){

                int curEventID = view.getId();
                Toast.makeText(getApplicationContext(), "cur Event id "+ curEventID,Toast.LENGTH_LONG ).show();

                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
//                intent.putExtra("spesificEvent", curEventID);
                startActivity(intent);

            }
        });

        newEventButton = (FloatingActionButton) findViewById(R.id.addEventButton);
        newEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewNeedEventActivity.class);
                intent.putExtra("selfUser", selfUser);
                startActivity(intent);
            }
        });

    }

    public void profileClicked(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("profileUser", selfUser);
        startActivity(intent);
    }
}

// time of walk: morning (8-12, noon12-18, evening18-24)
// date- no option for past event
//
