package com.rotemarbiv.tin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by laurescemama on 21/08/2017.
 */

public class EventAdapter extends ArrayAdapter<Event> {

    Context context;
    int layoutResourceId;
    Event events[] = null;

    public EventAdapter(Context context, int layoutResourceId, Event[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.events = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EventHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EventHolder();
            holder.imageIcon = (ImageView)row.findViewById(R.id.imageIconList);
            holder.nameTitle = (TextView)row.findViewById(R.id.nameTitleList);
            holder.timeTitle = (TextView)row.findViewById(R.id.timeTitleList);
            holder.dateTitle = (TextView)row.findViewById(R.id.dateTitleList);

            row.setTag(holder);
        }
        else
        {
            holder = (EventHolder)row.getTag();
        }

        Event event = events[position];

        System.out.println(event.getEventTitle() + " " + event.isItMe + " " + position);

        holder.imageIcon.setImageResource(event.getIcon());
        holder.nameTitle.setText(event.getEventTitle());
        holder.timeTitle.setText(event.getTimeStr());
        holder.dateTitle.setText(event.getDateStr());


        return row;
    }

    static class EventHolder
    {
        ImageView imageIcon;
        TextView nameTitle;
        TextView timeTitle;
        TextView dateTitle;

    }
}