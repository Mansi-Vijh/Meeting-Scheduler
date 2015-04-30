package sheduler.meeting.iiitd.meetingscheduler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import sheduler.meeting.iiitd.meetingscheduler.PopulatingClass.ProfessorDetails;
import sheduler.meeting.iiitd.meetingsheduler.R;
import sheduler.meeting.iiitd.meetingscheduler.Adapters.ProfListViewRowAdapter;

public class HistoryFragment extends Fragment implements AdapterView.OnItemClickListener {




    ArrayList<ProfessorDetails> profPopulate = new ArrayList<ProfessorDetails>();

    ProfListViewRowAdapter adapter;

    ListView profListView;


    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    public void populateData() {

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
    public void onStart() {
        super.onStart();

        profListView=(ListView) getView().findViewById(R.id.prof_list_view_lv);
        populateData();
        adapter=new ProfListViewRowAdapter(profPopulate, getActivity());
        profListView.setAdapter(adapter);
        profListView.setOnItemClickListener(this);

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

    }
}
