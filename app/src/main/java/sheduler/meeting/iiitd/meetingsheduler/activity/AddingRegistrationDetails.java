package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class AddingRegistrationDetails extends ActionBarActivity implements View.OnClickListener {

    TextView tvCourse;
    String objectId;
    String name;
    EditText post, yearOfPassing, etCourse, stream, program;
    String type="", addedCourses="";

    Button addCourse, submit;
    RelativeLayout studentsRl;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_registration_details);

        Parse.initialize(this, "KaNybYEl3ipW0bdomrnWxcl98UGmFSxVrPEkFJE4", "bywJxTAclcQdSvQ0U7Vg1GU4ZpMlLIAcmPL0kMVs");


        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        objectId = intent.getStringExtra("objectId");
        name = intent.getStringExtra("name");

        addCourse=(Button) findViewById(R.id.adding_registration_details_courses_button);
        submit=(Button) findViewById(R.id.adding_registration_details_submit);

        studentsRl=(RelativeLayout) findViewById(R.id.adding_registration_details_rl);

        post=(EditText) findViewById(R.id.adding_registration_details_tv_post);
        yearOfPassing=(EditText) findViewById(R.id.adding_registration_details_tv_year);
        etCourse=(EditText) findViewById(R.id.adding_registration_details_courses);
        stream=(EditText) findViewById(R.id.adding_registration_details_tv_stream);
        program=(EditText) findViewById(R.id.adding_registration_details_tv_program);

        tvCourse=(TextView) findViewById(R.id.adding_registration_details_tv_courses);

        addCourse.setOnClickListener(this);
        submit.setOnClickListener(this);



        if(type.equals("Faculty")){

            studentsRl.setVisibility(View.GONE);

        }
        else{
            post.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adding_registration_details, menu);
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

            case R.id.adding_registration_details_courses_button:

                if(flag==0){
                    addedCourses=addedCourses+ etCourse.getText() ;
                    flag=1;
                }
                else{
                    addedCourses=addedCourses+", "+ etCourse.getText() ;
                }

                tvCourse.setText(addedCourses);
                etCourse.setText("");

                break;


            case R.id.adding_registration_details_submit:


                ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");

// Retrieve the object by id
                query.getInBackground(objectId, new GetCallback<ParseObject>() {

                    @Override
                    public void done(ParseObject parseObject, com.parse.ParseException e) {
                        if(e == null)
                        {
                            parseObject.put("UserType",type);
                            parseObject.put("Name",name);
                            parseObject.put("Courses",addedCourses);

                            if(type.equals("Professor"))
                            {
                                parseObject.put("Post",post);
                            }
                            else if(type.equals("Student"))
                            {
                                parseObject.put("Stream",stream);
                                parseObject.put("Programme",program);
                                parseObject.put("Year",yearOfPassing);
                            }

                        }
                    }
                });


                Intent intent=new Intent(AddingRegistrationDetails.this,MainActivity.class);
                intent.putExtra("objectId", objectId);
                startActivity(intent);

                break;


        }


    }
}
