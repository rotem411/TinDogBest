package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by dafnaarbiv on 22/07/2017.
 */
// todo: pending request, new event goes there. approved events go from there to upcoming

public class HomeActivity extends AppCompatActivity {

    public User laureUser = new User("Laure Scemama", "laure", "Doggy", "Yafo 3", true);
    public User galUser = new User("Gal Nachmana", "gal", "Doggy2", "Yafo 4", true);
    public User rotemUser = new User("Rotem Arbiv", "rotem", "Doggy3", "Yafo 5", true);

    public Event a = new Event(laureUser, rotemUser, "Tue, July 7th", "13:00", true);
    public Event b = new Event(laureUser, galUser, "Tue, July 7th", "14:00", true);
    public Event c =  new Event(galUser, laureUser, "Wed, July 8th", "15:00", false);
    public Event d =  new Event(rotemUser, laureUser, "Thu, July 8th", "22:00",  false);

    Event[] myEvents = new Event[]{
            a,b
    };

    Event[] dogsEvents = new Event[]{
            c,d
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ListView myEventsListView = (ListView)findViewById(R.id.myEventsList);
        ListView dogsEventsListView = (ListView)findViewById(R.id.dogsEventsList);

        EventAdapter myEventsAdapter = new EventAdapter(this, R.layout.event_item_list, myEvents);
        myEventsListView.setAdapter(myEventsAdapter);
        EventAdapter dogsEventsAdapter = new EventAdapter(this, R.layout.event_item_list, dogsEvents);
        dogsEventsListView.setAdapter(dogsEventsAdapter);

        myEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent , View view, int position, long id){

                int curEventID = view.getId();
                Toast.makeText(getApplicationContext(), "cur Event id "+ curEventID,Toast.LENGTH_LONG ).show();


                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
//                intent.putExtra("spesificEvent", curEventID);
                startActivity(intent);

            }
        });

        dogsEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent , View view, int position, long id){

                int curEventID = view.getId();
                Toast.makeText(getApplicationContext(), "cur Event id "+ curEventID,Toast.LENGTH_LONG ).show();


                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
//                intent.putExtra("spesificEvent", curEventID);
                startActivity(intent);

            }
        });



//        ArrayAdapter myAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, myEvents);
//        myEventsList.setAdapter(myAdapter);
//
//        myEventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> parent , View view, int position, long id){
//
//                int curEventID = view.getId();
//                Toast.makeText(getApplicationContext(), "cur Event id "+ curEventID,Toast.LENGTH_LONG ).show();
//
//
//                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
////                intent.putExtra("spesificEvent", curEventID);
//                startActivity(intent);
//
//            }
//        });
//
    }
}
