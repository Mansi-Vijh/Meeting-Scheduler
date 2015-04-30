package sheduler.meeting.iiitd.meetingsheduler.Adapters;



import android.content.Context;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;



import java.util.ArrayList;

import sheduler.meeting.iiitd.meetingsheduler.R;
import sheduler.meeting.iiitd.meetingsheduler.PopulatingClass.ProfessorDetails;

/**
 * Created by dell on 4/19/2015.
 */




public class ProfListViewRowAdapter extends BaseAdapter {


    TextView date, time, month, name, about,status;

    ArrayList<ProfessorDetails> profArrayList = new ArrayList();
    LayoutInflater inflater;
    Context context;

    public ProfListViewRowAdapter(ArrayList<ProfessorDetails> profArrayList, Context context) {
        this.profArrayList = profArrayList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }


    @Override
    public int getCount() {
        return profArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.meeting_list_row, parent, false);

        date = (TextView) convertView.findViewById(R.id.meeting_row_date);
        time = (TextView) convertView.findViewById(R.id.meeting_row_time);
        month = (TextView) convertView.findViewById(R.id.meeting_row_month);
        name = (TextView) convertView.findViewById(R.id.meeting_row_name);
        about = (TextView) convertView.findViewById(R.id.meeting_row_about);
        status = (TextView) convertView.findViewById(R.id.meeting_row_status);

        date.setText(profArrayList.get(position).getDate());
        time.setText(profArrayList.get(position).getTime());
        month.setText(profArrayList.get(position).getMonth());
        name.setText(profArrayList.get(position).getProfName());
        about.setText(profArrayList.get(position).getAbout());

        String getStatus;
        getStatus = profArrayList.get(position).getStatus();
        if(getStatus=="Approved"){

            status.setBackgroundColor(Color.GREEN);
        }
        if(getStatus=="Pending"){

            status.setBackgroundColor(Color.RED);
        }


        return convertView;

    }
}