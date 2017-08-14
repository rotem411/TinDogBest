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
    public Event a = new Event(3, "shenkin", "yotam", true);
    public Event b =  new Event(1, "Alenby", "maya ", false );

    Event[] events = new Event[]{
            a,b
    };
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

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
//                intent.putExtra("spesificEvent", curEventID);
                startActivity(intent);

            }
        });
    }
}
