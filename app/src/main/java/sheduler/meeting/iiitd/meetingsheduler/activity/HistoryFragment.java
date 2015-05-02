package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import sheduler.meeting.iiitd.meetingsheduler.R;
import sheduler.meeting.iiitd.meetingsheduler.Adapters.ProfListViewRowAdapter;
import sheduler.meeting.iiitd.meetingsheduler.PopulatingClass.ProfessorDetails;

public class HistoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    SharedPreferences pref;
    ArrayList<ProfessorDetails> profPopulate = new ArrayList<ProfessorDetails>();

    ProfListViewRowAdapter adapter;

    ListView profListView;

    int flag=0;

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
        pref = getActivity().getSharedPreferences("meeting_app",0);
        String userId = pref.getString("objectId","");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MeetingDetails");

        query.whereEqualTo("fromID", userId);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                for(int i = 0 ; i < parseObjects.size(); i++)
                {

                }
               // profPopulate.add(new ProfessorDetails(name, parseObjects.get(i)., "2:00 pm", "25", "April","Approved"));

            }
        });

        query.whereEqualTo("toID", userId);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                String name;
                String title;
                String other;

                for(int i = 0 ; i < parseObjects.size(); i++)
                {
              //      profPopulate.add()
                }
                // profPopulate.add(new ProfessorDetails(name, parseObjects.get(i)., "2:00 pm", "25", "April","Approved"));

            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();

        if(flag==0) {
            profListView = (ListView) getView().findViewById(R.id.prof_list_view_lv);
            populateData();
            adapter = new ProfListViewRowAdapter(profPopulate, getActivity());
            profListView.setAdapter(adapter);
            profListView.setOnItemClickListener(this);
            flag = 1;
        }
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


        String name = "YAYYY!";
        String meetingId=profPopulate.get(position).getMeetingId();
        String status=profPopulate.get(position).getStatus();
        Intent intent=new Intent(getActivity(), MeetingDetailsActivity.class);
        intent.putExtra("meeting_id",meetingId);
        intent.putExtra("status",status);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
