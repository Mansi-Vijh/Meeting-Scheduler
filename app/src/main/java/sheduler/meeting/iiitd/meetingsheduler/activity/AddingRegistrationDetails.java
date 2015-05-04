package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.google.android.gms.plus.Plus;
//import com.google.android.gms.plus.model.people.Person;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class AddingRegistrationDetails extends ActionBarActivity implements View.OnClickListener {

    TextView tvCourse;
    String objectId;
    public String name=" ", type=" ", addedCourses=" ";
    EditText post, yearOfPassing, etCourse, stream, program;


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
        System.out.println("237 4 "+ type);
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



        if(type.equals("Student")){

            post.setVisibility(View.GONE);

        }
        else{

            studentsRl.setVisibility(View.GONE);
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



/*

                ParseObject parseObject= null;
                try {
                    parseObject = query.get(objectId);

                    parseObject.put("UserType", type);
                    parseObject.put("Name", name);
                    parseObject.put("Courses", addedCourses);


                    if (type.equals("Student")) {
                        parseObject.put("Stream", stream);
                        parseObject.put("Programme", program);
                        parseObject.put("Year", yearOfPassing);
                    } else {
                        parseObject.put("Post", post);
                    }
                    while(!parseObject.saveInBackground().isCompleted()){

                    }


                } catch (com.parse.ParseException e) {
                    e.printStackTrace();
                }


*/

                AsyncCalling calling =new AsyncCalling();
                calling.execute();


                break;


        }


    }







    public class AsyncCalling extends AsyncTask<String, String , String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            putRow();

            return null;
        }

        private void putRow() {

            System.out.println("237 5 "+ type);


            ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
          //  final ParseObject[] parseObject_ = new ParseObject[1];
// Retrieve the object by id
          query.getInBackground(objectId, new GetCallback<ParseObject>() {


                @Override
                public void done(ParseObject parseObject, com.parse.ParseException e) {

                    parseObject.put("UserType", type);
                    parseObject.put("Name", name);
                    parseObject.put("Courses", addedCourses);


                    if (type.equals("Student")) {
                        parseObject.put("Stream", stream.getText().toString());
                        parseObject.put("Programme", program.getText().toString());
                        parseObject.put("Year",Integer.parseInt(yearOfPassing.getText().toString()));
                    } else {
                        parseObject.put("Post", post.getText().toString());
                    }

                while(!parseObject.saveInBackground().isCompleted()){

                }


                }


            });

/*

            ParseObject parseObject= null;
            try {
                parseObject = query.get(objectId);

                parseObject.put("Courses", addedCourses);
                parseObject.put("Name", name);
                parseObject.put("UserType", type);




                if (type.equals("Student")) {
                    parseObject.put("Stream", stream.getText().toString());
                    parseObject.put("Programme", program.getText().toString());
                    parseObject.put("Year", Integer.parseInt(yearOfPassing.getText().toString()));
                } else {
                    parseObject.put("Post", post);
                }
                while(!parseObject.saveInBackground().isCompleted()){

                }


            } catch (com.parse.ParseException e) {
                e.printStackTrace();
            }

          */
/*  while (!parseObject_[0].saveInBackground().isCompleted()) {

            }*/


        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Intent intent = new Intent(AddingRegistrationDetails.this, MainActivity.class);
            intent.putExtra("objectId",objectId);


            startActivity(intent);



        }
    }









}
