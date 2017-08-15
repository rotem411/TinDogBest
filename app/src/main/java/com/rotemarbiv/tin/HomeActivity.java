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

public class HomeActivity extends AppCompatActivity {
    public static Event a = new Event(3, "shenkin", "yotam", true, "momo");
    public static Event b =  new Event(1, "Alenby", "maya ", false, "roki" );

    static Event[] events = new Event[]{
            a,b
    };

    // NOTE - instead of this list, there going to be a request to the server:
    // give me the list of events for the current user .

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ListView eventsList = (ListView)findViewById(R.id.eventsList);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, events);
        eventsList.setAdapter(adapter);

        eventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent , View view, int position, long id){

                int curEventID = view.getId();
                Toast.makeText(getApplicationContext(), "cur Event id "+ curEventID,Toast.LENGTH_LONG ).show();

                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });
    }
}
