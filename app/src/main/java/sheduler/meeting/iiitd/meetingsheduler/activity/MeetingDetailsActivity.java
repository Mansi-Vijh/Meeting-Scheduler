package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.List;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class MeetingDetailsActivity extends ActionBarActivity implements View.OnClickListener{

    TextView name,time,attachmentLink, description, details,title;
    Button button1, button2;
    String meetingId;
    String title_var;
    String description_var;
    String details_var;
    String attachmentLink_var;
    String time_var;
    String name_var;
    String userId;
    String status_var;
    int clicked_button;
    SharedPreferences pref;



   //retrieve this from the previous page


    enum status{pending, approved, cancelled, rejected};

    status currentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        /*Intent intent = getIntent();
        intent.getStringExtra("meetingId");
        */

        meetingId = "ZAuXuhaAiN";

        title=(TextView) findViewById(R.id.meeting_details_meeting_title);
        name=(TextView) findViewById(R.id.meeting_details_to_or_from);
        time=(TextView) findViewById(R.id.meeting_details_time);
        attachmentLink=(TextView) findViewById(R.id.meeting_details_link);
        description=(TextView) findViewById(R.id.meeting_details_description);
        details=(TextView) findViewById(R.id.meeting_details_details);
        button1=(Button) findViewById(R.id.meeting_details_button1);
        button2=(Button) findViewById(R.id.meeting_details_button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);/*
        status testVariable= status.pending;
        if(testVariable== status.pending){

            System.out.println("pending checked. ");
        }*/


        title.setText("");
        name.setText("");
        time.setText("");
        attachmentLink.setText("");
        description.setText("");
        details.setText("");
        button1.setText("");
        button2.setText("");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("MeetingDetails");

        query.getInBackground(meetingId, new GetCallback<ParseObject>() {


            @Override
            public void done(ParseObject parseObject, com.parse.ParseException e) {

               String fromId = parseObject.getString("FromID");
               String toId = parseObject.getString("ToID");

                pref = getSharedPreferences("meeting_app",0);
                userId =  pref.getString("objectId"," ");

                if (userId.equals(fromId))
                {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
                    query.getInBackground(toId, new GetCallback<ParseObject>() {


                        @Override
                        public void done(ParseObject parseObject, com.parse.ParseException e) {
                                name_var = parseObject.getString("Name");
                        }
                    });
                }

                else if(userId.equals(toId))
                {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
                    query.getInBackground(fromId, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, com.parse.ParseException e) {
                            name_var = parseObject.getString("Name");
                        }
                    });
                }

                name.setText(name_var);
                title_var = parseObject.getString("Title");
                title.setText(title_var);
                description_var = parseObject.getString("Description");
                description.setText(description_var);
                attachmentLink_var = parseObject.getString("AttachmentLink");
                attachmentLink.setText(attachmentLink_var);
                time_var = parseObject.getString("Time");
                time.setText(time_var);
                details_var = parseObject.getString("Details");
                details.setText(details_var);
                status_var = parseObject.getString("Status");
                if(status_var.equals("Pending"))
                {
                    currentStatus = status.pending;
                    button1.setText("Approve");
                    button2.setText("Reject");
                }
                else if(status_var.equals("Approved"))
                {
                    currentStatus = status.approved;
                    button1.setText("Cancel");
                }
                else if(status_var.equals("Cancelled"))
                {
                    currentStatus = status.cancelled;
                }
                else if(status_var.equals("Rejected"))
                {
                    currentStatus = status.rejected;

                }



               }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meeting_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }








    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.meeting_details_button1:
                clicked_button = 1;

                break;

            case R.id.meeting_details_button2:
                clicked_button = 2;
                break;
        }
        AsyncCalling asyncObject = new AsyncCalling();
        asyncObject.execute();




    }



    public class AsyncCalling extends AsyncTask<String, String , String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            updateStatus();

            return null;
        }

        private void updateStatus() {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("MeetingDetails");


              query.getInBackground(meetingId, new GetCallback<ParseObject>() {
                  @Override
                  public void done(ParseObject parseObject, com.parse.ParseException e) {
                      if (clicked_button == 1) {
                          if (currentStatus.equals(status.approved)) {
                              parseObject.put("Status", "Cancelled");

                          }
                          else if (currentStatus.equals(status.pending)) {
                              parseObject.put("Status", "Approved");

                          }
                      } else if (clicked_button == 2) {
                          if (currentStatus.equals(status.pending)) {
                              parseObject.put("Status", "Approved");
                          }
                      }
                  }
              });




        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (clicked_button == 1) {
                if (currentStatus.equals(status.approved)) {
                     button1.setVisibility(View.GONE);
                     currentStatus = status.cancelled;
                }
                else if (currentStatus.equals(status.pending)) {
                    button1.setText("Cancel");
                    button2.setVisibility(View.GONE);
                    currentStatus = status.approved;
                }

            } else if (clicked_button == 2) {
                if (currentStatus.equals(status.pending)) {
                    button1.setVisibility(View.GONE);
                    button2.setVisibility(View.GONE);
                }
            }

        }
    }




}
