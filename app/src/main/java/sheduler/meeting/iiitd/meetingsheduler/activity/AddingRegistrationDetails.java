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

import sheduler.meeting.iiitd.meetingsheduler.R;

public class AddingRegistrationDetails extends ActionBarActivity implements View.OnClickListener {

    TextView tvCourse;
    EditText post, yearOfPassing, etCourse, stream, program;
    String type="", addedCoursese="";
    Button addCourse, submit;
    RelativeLayout studentsRl;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_registration_details);

        Intent intent=getIntent();
        type=intent.getStringExtra("type");


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
                    addedCoursese=addedCoursese+ etCourse.getText() ;
                    flag=1;
                }
                else{
                    addedCoursese=addedCoursese+", "+ etCourse.getText() ;
                }

                tvCourse.setText(addedCoursese);
                etCourse.setText("");

                break;


            case R.id.adding_registration_details_submit:

                Intent intent=new Intent(AddingRegistrationDetails.this,MainActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);

                break;


        }


    }
}
