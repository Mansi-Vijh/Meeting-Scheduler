package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class MeetingForm extends ActionBarActivity implements View.OnClickListener {

    EditText title, description, details, attachment;
    Button sendRequest;
    TextView slot;
    SharedPreferences pref;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_form);

        Parse.initialize(this, "KaNybYEl3ipW0bdomrnWxcl98UGmFSxVrPEkFJE4", "bywJxTAclcQdSvQ0U7Vg1GU4ZpMlLIAcmPL0kMVs");
        Intent intent;


        title=(EditText )findViewById(R.id.meeting_form_title);
        description=(EditText )findViewById(R.id.meeting_form_description);
        details=(EditText )findViewById(R.id.meeting_form_other_details);
        attachment=(EditText )findViewById(R.id.meeting_form_attachment);
        sendRequest=(Button ) findViewById(R.id.meeting_form_send_request);
        slot=(TextView) findViewById(R.id.meeting_form_slot);


        sendRequest.setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meeting_form, menu);
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

        Intent intent = getIntent();
        String proffessorId=intent.getStringExtra("proffessor_id");


      pref = getSharedPreferences("meeting_app",0);
      userId = pref.getString("objectId","");
      String titleText = title.getText().toString();
      String descriptionText = description.getText().toString();
      String detailsText = details.getText().toString();
      String attachmentLinkText = attachment.getText().toString();

       ParseObject meetingdetails = new ParseObject("MeetingDetails");
        meetingdetails.put("FromID", userId);
        meetingdetails.put("ToID", proffessorId);
        meetingdetails.put("Title", titleText);
        meetingdetails.put("Description", descriptionText);
        meetingdetails.put("Details", detailsText);
        meetingdetails.put("AttachmentLink",attachmentLinkText);

       while(!meetingdetails.saveInBackground().isCompleted()){

       }


         intent =new Intent(MeetingForm.this, MainActivity.class );
        startActivity(intent);
    }
}
