package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Calendar;
import java.util.Date;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class ScheduleMeeting extends ActionBarActivity implements View.OnClickListener {

    TextView profName, basicDetails;
    Button selectDate, proceed;

    int mYear, mMonth, mDay;
    String date, proffId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_a_meeting);



        Parse.initialize(getBaseContext(), "KaNybYEl3ipW0bdomrnWxcl98UGmFSxVrPEkFJE4", "bywJxTAclcQdSvQ0U7Vg1GU4ZpMlLIAcmPL0kMVs");

        Intent intent = getIntent();
        proffId=intent.getStringExtra("proffessor_id");


        profName=(TextView) findViewById(R.id.schedule_a_meeting_name);
        basicDetails=(TextView) findViewById(R.id.schedule_a_meeting_details);

        selectDate=(Button) findViewById(R.id.schedule_a_meeting_date_picker);
        proceed=(Button) findViewById(R.id.schedule_a_meeting_proceed);
        selectDate.setOnClickListener(this);
        proceed.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shedule_meeting, menu);
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
            case R.id.schedule_a_meeting_proceed:

                Intent intent =new Intent( ScheduleMeeting.this,MeetingForm.class );
                intent.putExtra("proffessor_id",proffId);
                startActivity(intent);

                break;
            case R.id.schedule_a_meeting_date_picker:


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                System.out.println("1234 : " + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                                if (monthOfYear < 9) {
                                    date = year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth;
                                } else {
                                    date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                }
                                System.out.println(date);


                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
                System.out.println("*****" + date);
                ParseQuery<ParseObject> query = ParseQuery.getQuery("MeetingDetails");

                query.getInBackground("GehHfr9xGC", new GetCallback<ParseObject>() {


                    @Override
                    public void done(ParseObject parseObject, com.parse.ParseException e) {

                        Date date_new = parseObject.getDate("Date");
                        System.out.println("12345 Date" + date_new.getHours() + date_new.getMinutes() + date_new.getDate() );

                    } });
                break;

        }



        }


    }


