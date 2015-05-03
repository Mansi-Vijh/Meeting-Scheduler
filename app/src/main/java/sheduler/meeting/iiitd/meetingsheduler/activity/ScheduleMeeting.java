package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class ScheduleMeeting extends ActionBarActivity implements View.OnClickListener {

    TextView profName, basicDetails;
    Button selectDate, proceed;

    Spinner spinner;
    int mYear, mMonth, mDay;
    String date, profId;

    ArrayList<Integer> Overall_slots = new ArrayList<Integer>();
    ArrayAdapter<String> adapter;
    ArrayList<String> string_slots= new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_a_meeting);

        Parse.initialize(getBaseContext(), "KaNybYEl3ipW0bdomrnWxcl98UGmFSxVrPEkFJE4", "bywJxTAclcQdSvQ0U7Vg1GU4ZpMlLIAcmPL0kMVs");
        Intent intent = getIntent();
        profId=intent.getStringExtra("professor_id");
        profName=(TextView) findViewById(R.id.schedule_a_meeting_name);
        basicDetails=(TextView) findViewById(R.id.schedule_a_meeting_details);
        selectDate=(Button) findViewById(R.id.schedule_a_meeting_date_picker);
        proceed=(Button) findViewById(R.id.schedule_a_meeting_proceed);
        spinner=(Spinner) findViewById(R.id.schedule_a_meeting_slot);
        selectDate.setOnClickListener(this);
        proceed.setOnClickListener(this);

        adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, string_slots);
    //    spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



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
                intent.putExtra("professor_id",profId);
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


                                //ArrayAdapter&lt;String&gt; dataAdapter = new ArrayAdapter&lt;String&gt;(this, categories);



                            }
                        }, mYear, mMonth, mDay);
                dpd.show();


                ParseQuery<ParseObject> query = ParseQuery.getQuery("MeetingDetails");

                query.whereEqualTo("Date", date);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        if (e == null) {
                            ArrayList<Integer> slots_notAvailable = new ArrayList<Integer>();

                            for (int i = 0; i < parseObjects.size(); i++)
                                slots_notAvailable.add(parseObjects.get(i).getDate("Date").getHours());
                            Overall_slots.add(8);
                            Overall_slots.add(9);
                            Overall_slots.add(10);
                            Overall_slots.add(11);
                            Overall_slots.add(12);
                            Overall_slots.add(1);
                            Overall_slots.add(2);
                            Overall_slots.add(3);
                            Overall_slots.add(4);
                            Overall_slots.add(5);
                            for (int j = 0; j < slots_notAvailable.size(); j++) {
                                Overall_slots.contains(slots_notAvailable.get(j));
                                Overall_slots.remove(Overall_slots.indexOf(slots_notAvailable.get(j)));
                            }


                            for (int i = 0; i < Overall_slots.size(); i++) {
                                string_slots.add(Overall_slots.toString());
                            }
adapter.notifyDataSetChanged();
                            spinner.getSelectedItem();
                            // populate spinner using the values in Overall_slots
                            //get the selected value and save it in the variable : new_slot
                            int new_slot = 8;
                            ParseObject parseObject = new ParseObject("MeetingDetails");
                            Date date_new;
                            date_new = parseObject.getDate("Date");
                            System.out.println("Date Checking : " + date_new.toString());
                            //this is the selected slot
                            date_new.setHours(new_slot);
                            parseObject.put("Title", "my new title");
                            parseObject.put("Date", date_new);
                            while (!parseObject.saveInBackground().isCompleted()) {

                            }




                            Log.d("Slots", "Retrieved " + parseObjects.size() + " objects");
                        } else {
                        }
                    }

                    @Override
                    public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {

                    }
                });

                break;

        }



        }



    public class CustomOnItemSelectedListener implements OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }


}
