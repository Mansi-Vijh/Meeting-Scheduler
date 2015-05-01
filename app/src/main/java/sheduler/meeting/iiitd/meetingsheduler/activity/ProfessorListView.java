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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    ArrayList<ParseObject> parseObjectList = new ArrayList<ParseObject>();

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

        Parse.enableLocalDatastore(getActivity());

        Parse.initialize(getActivity(), "KaNybYEl3ipW0bdomrnWxcl98UGmFSxVrPEkFJE4", "bywJxTAclcQdSvQ0U7Vg1GU4ZpMlLIAcmPL0kMVs");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
        query.whereEqualTo("UserType", "Professor");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                System.out.println("678 8 " + parseObjects.size());

                for (int i = 0; i < parseObjects.size(); i++)
                    parseObjectList.add(parseObjects.get(i));
                System.out.println("678 8 "+ parseObjectList.get(0).getString("Name"));
                prepData();
            }
        });


            professorListView = (ListView) getView().findViewById(R.id.professor_lv);

            professorListView.setOnItemClickListener(this);
            adapter = new ProfessorListViewAdaptor(professorDetails, getActivity());
            professorListView.setAdapter(adapter);
            prepData();

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


        System.out.println("678 "+ parseObjectList.size());
        for(int i = 0; i < parseObjectList.size();i++ )
        {
                professorDetails.add(new ProfessorDetails(parseObjectList.get(i).getString("Name"), parseObjectList.get(i).getString("Post"),parseObjectList.get(i).getString("Courses")));

                System.out.println("678 " + parseObjectList.get(i).getString("Name") + parseObjectList.get(i).getString("Post") + parseObjectList.get(i).getString("Courses"));

        }


        adapter.notifyDataSetChanged();
        /*professorDetails.add(new ProfessorDetails("vinayak naik", "mobile computing, computer networks, cool guy teaching cool subjects"));
        professorDetails.add(new ProfessorDetails("pushpendra singh","cloud computing"));
        professorDetails.add(new ProfessorDetails("rahul kohli","universal teacher"));
*/
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent =new Intent(getActivity() , ScheduleMeeting.class);
        intent.putExtra("proffessor_id",proffId);

        startActivity(intent);


    }
}
