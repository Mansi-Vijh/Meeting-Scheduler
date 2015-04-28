package sheduler.meeting.iiitd.meetingsheduler.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sheduler.meeting.iiitd.meetingsheduler.PopulatingClass.ProfessorDetails;
import sheduler.meeting.iiitd.meetingsheduler.R;

/**
 * Created by Masood on 4/28/2015.
 */
public class ProfessorListViewAdaptor extends BaseAdapter {


    ArrayList<ProfessorDetails> professorDetails =new ArrayList<ProfessorDetails>();
   // Context context;
    LayoutInflater inflater;

    TextView name, course;

    public ProfessorListViewAdaptor(ArrayList<ProfessorDetails> professorDetails, Context context) {
        this.professorDetails = professorDetails;
    //    this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return professorDetails.size();
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

        convertView=inflater.inflate(R.layout.activity_professor_list_view_row, parent, false);

        name=(TextView) convertView.findViewById(R.id.professor_lv_row_name);
        course=(TextView) convertView.findViewById(R.id.professor_lv_row_course);

        name.setText(professorDetails.get(position).getProfName());
        course.setText(professorDetails.get(position).getCourse());


        return convertView;
    }
}
