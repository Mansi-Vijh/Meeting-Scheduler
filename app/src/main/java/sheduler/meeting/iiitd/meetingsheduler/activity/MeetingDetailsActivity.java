package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class MeetingDetailsActivity extends ActionBarActivity implements View.OnClickListener{

    TextView name,time,attachmentLink, description, details,title;
    Button button1, button2;


    enum status{pending, approved, canceled, rejected};

    status currentStatus=status.pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

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

        switch(v.getId()){
            case R.id.meeting_details_button1:


            break;

            case R.id.meeting_details_button2:

                break;



        }


    }
}
