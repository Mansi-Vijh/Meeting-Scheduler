package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class MeetingForm extends ActionBarActivity implements View.OnClickListener {

    EditText title, description, details, attachment;
    Button sendRequest;
    TextView slot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_form);

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



        Intent intent =new Intent(MeetingForm.this, MainActivity.class );
        startActivity(intent);
    }
}
