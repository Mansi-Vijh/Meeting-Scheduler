package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sheduler.meeting.iiitd.meetingsheduler.Adapters.ProfListViewRowAdapter;
import sheduler.meeting.iiitd.meetingsheduler.Adapters.ProfessorListViewAdaptor;
import sheduler.meeting.iiitd.meetingsheduler.PopulatingClass.ProfessorDetails;
import sheduler.meeting.iiitd.meetingsheduler.R;

public class ProfessorListView extends Fragment implements AdapterView.OnItemClickListener {

    ListView professorListView;
    ProfessorListViewAdaptor adapter;
    ArrayList<ProfessorDetails>  professorDetails =new ArrayList<ProfessorDetails>();
    String proffId="";
    static int flag=0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_professor_list_view, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();



        if(flag==0) {
            professorListView = (ListView) getView().findViewById(R.id.professor_lv);

            professorListView.setOnItemClickListener(this);
            prepData();
            adapter = new ProfessorListViewAdaptor(professorDetails, getActivity());
            professorListView.setAdapter(adapter);
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

    void prepData(){


        professorDetails.add(new ProfessorDetails("vinayak naik", "mobile computing, computer networks, cool guy teaching cool subjects"));
        professorDetails.add(new ProfessorDetails("pushpendra singh","cloud computing"));
        professorDetails.add(new ProfessorDetails("rahul kohli","universal teacher"));

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent =new Intent(getActivity() , ScheduleMeeting.class);
        intent.putExtra("proffessor_id",proffId);

        startActivity(intent);


    }
}
