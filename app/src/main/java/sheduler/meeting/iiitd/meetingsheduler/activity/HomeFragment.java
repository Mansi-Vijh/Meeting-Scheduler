package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sheduler.meeting.iiitd.meetingsheduler.PopulatingClass.MeetingDetails;
import sheduler.meeting.iiitd.meetingsheduler.R;
import sheduler.meeting.iiitd.meetingsheduler.Adapters.ProfListViewRowAdapter;
import sheduler.meeting.iiitd.meetingsheduler.PopulatingClass.ProfessorDetails;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    SharedPreferences pref;
    ArrayList<MeetingDetails> meetingPopulate = new ArrayList<MeetingDetails>();
    String name;
    String title;
   // String other;
    String userId;
    ProfListViewRowAdapter adapter;

    ListView profListView;

    int flag=0;

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

    public void populateData() {
        pref = getActivity().getSharedPreferences("meeting_app", 0);
         userId = pref.getString("objectId", "");


        AsyncCalling calling=new AsyncCalling();
        calling.execute();





    }


    @Override
    public void onStart() {
        super.onStart();

        if(flag==0) {
            profListView = (ListView) getView().findViewById(R.id.prof_list_view_lv);

            adapter = new ProfListViewRowAdapter(meetingPopulate, getActivity());
            profListView.setAdapter(adapter);
            profListView.setOnItemClickListener(this);
            populateData();
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


        String name = meetingPopulate.get(position).getName();
        String meetingId=meetingPopulate.get(position).getMeetingId();
        String status=meetingPopulate.get(position).getStatus();
        Intent intent=new Intent(getActivity(), MeetingDetailsActivity.class);
        intent.putExtra("meeting_id",meetingId);
        intent.putExtra("status",status);
        intent.putExtra("name",name);
        startActivity(intent);
    }





    public class AsyncCalling extends AsyncTask<String, String , String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                updateStatus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        private void updateStatus() throws InterruptedException {

            ParseQuery<ParseObject> query = ParseQuery.getQuery("MeetingDetails");

            query.whereEqualTo("fromID", userId);
            query.wait(5000);

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {


                    for (int i = 0; i < parseObjects.size(); i++) {
                        title = parseObjects.get(i).getString("Title");
                        Date date = parseObjects.get(i).getDate("Date");

                        ParseQuery<ParseObject> queryInner = ParseQuery.getQuery("UserDetails");
                        queryInner.getInBackground(parseObjects.get(i).getString("toID"), new GetCallback<ParseObject>() {


                            @Override
                            public void done(ParseObject parseObject, com.parse.ParseException e) {
                                name = parseObject.getString("Name");

                            }
                        });
                        Calendar c = Calendar.getInstance();
                        int now = Calendar.DATE;

                        meetingPopulate.add(new MeetingDetails(name, title, date.getHours(), date.getDate(), date.getMonth(), parseObjects.get(i).getObjectId(), parseObjects.get(i).getString("Status")));
                        System.out.println("598" + name + title + date.getHours() + date.getDate() + date.getMonth() + parseObjects.get(i).getObjectId() + parseObjects.get(i).getString("Status"));
                    }


                }
            });


            ParseQuery<ParseObject> query1 = ParseQuery.getQuery("MeetingDetails");

            query1.whereEqualTo("toID", userId);

            query1.findInBackground(new FindCallback<ParseObject>() {

                @Override
                public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {


                    for(int i = 0 ; i < parseObjects.size(); i++)
                    {
                        title = parseObjects.get(i).getString("Title");
                        Date date = parseObjects.get(i).getDate("Date");

                        ParseQuery<ParseObject> queryInner = ParseQuery.getQuery("UserDetails");
                        queryInner.getInBackground(parseObjects.get(i).getString("fromID"), new GetCallback<ParseObject>() {


                            @Override
                            public void done(ParseObject parseObject, com.parse.ParseException e) {
                                name = parseObject.getString("Name");
                                System.out.println("1234 Name"+name);
                            }
                        });
                        Calendar c = Calendar.getInstance();
                        int now = Calendar.DATE;

                        meetingPopulate.add(new MeetingDetails(name, title, date.getHours(), date.getDate(), date.getMonth(), parseObjects.get(i).getObjectId(), parseObjects.get(i).getString("Status")));
                        System.out.println("598" + name + title + date.getHours() + date.getDate() + date.getMonth() + parseObjects.get(i).getObjectId() + parseObjects.get(i).getString("Status"));
                    }


                    adapter.notifyDataSetChanged();
                }
            });
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            adapter.notifyDataSetChanged();

        }
    }




}
