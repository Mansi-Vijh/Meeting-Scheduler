package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import sheduler.meeting.iiitd.meetingsheduler.R;
import sheduler.meeting.iiitd.meetingsheduler.Adapters.ProfListViewRowAdapter;
import sheduler.meeting.iiitd.meetingsheduler.PopulatingClass.ProfessorDetails;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener{


    ArrayList<ProfessorDetails> profPopulate = new ArrayList<ProfessorDetails>();

    ProfListViewRowAdapter adapter;

    ListView profListView;
    public String name;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        profListView=(ListView) getView().findViewById(R.id.prof_list_view_lv);
        populateData();
        adapter=new ProfListViewRowAdapter(profPopulate, getActivity());
        profListView.setAdapter(adapter);
        profListView.setOnItemClickListener(this);

    }

    public void populateData() {

        name = "YAYYY!";
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "25", "April","Approved"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "26", "April","Pending"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "27", "April","Pending"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "28", "April","Approved"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "29", "April","Pending"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "30", "April","Approved"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "2", "May","Pending"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "5", "May","Approved"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "6", "May","Approved"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "10", "May","Pending"));
        profPopulate.add(new ProfessorDetails("Rahul", "about project descussion", "2:00 pm", "25", "May","Approved"));

    }

        @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String meetingId=profPopulate.get(position).getMeetingId();
        String status=profPopulate.get(position).getStatus();
        Intent intent=new Intent(getActivity(), MeetingDetailsActivity.class);
        intent.putExtra("meeting_id",meetingId);
        intent.putExtra("status",status);
        intent.putExtra("name",name);
        startActivity(intent);

    }
}
