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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class RegistrationPage extends ActionBarActivity implements View.OnClickListener{

    EditText name;
    String objectId;
    String type="Student";
    RadioGroup radioGroup;
    RadioButton radioButtonFaculty,  radioButtonStudent ;
    Button next;
    SharedPreferences pref;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);


        Intent intent =  getIntent();
         objectId = intent.getStringExtra("objectId");
        name=(EditText) findViewById(R.id.registration_name);
        radioGroup=(RadioGroup) findViewById(R.id.registration_page_radio_button);

        System.out.println("654" + objectId);
        radioButtonFaculty = (RadioButton) findViewById(R.id.radio_button_faculty);
        radioButtonStudent = (RadioButton) findViewById(R.id.radio_button_student);
        next=(Button) findViewById(R.id.registration_page_next);
        next.setOnClickListener(this);
        radioButtonFaculty.setOnClickListener(this);
        radioButtonStudent.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration_page, menu);
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
            case R.id.registration_page_radio_button:
                int selectedId = radioGroup.getCheckedRadioButtonId();


          //      type=  radioButton.getText().toString();


                System.out.println("237 "+ type);

                break;

            case R.id.radio_button_faculty:
                type=  radioButtonFaculty.getText().toString();
                System.out.println("237 "+ type);
                break;


            case R.id.radio_button_student:
                type=  radioButtonStudent.getText().toString();
                System.out.println("237 "+ type);
                break;

            case R.id.registration_page_next:

                System.out.println("237 8 "+ type);

                Intent intent=new Intent(RegistrationPage.this, AddingRegistrationDetails.class);
                intent.putExtra("type", type);
                startActivity(intent);

                break;



        }

    }
}
