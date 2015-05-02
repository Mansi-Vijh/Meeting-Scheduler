package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.InputStream;
import java.text.ParseException;

import sheduler.meeting.iiitd.meetingsheduler.R;

public class GoogleLogin extends ActionBarActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    SharedPreferences pref;
    SharedPreferences.Editor edit;
    boolean isRegistered = false;

    private static final int RC_SIGN_IN = 0;
    // Logcat tag
    private static final String TAG = "MainActivity";

    // Profile pic image size in pixels
    private static final int PROFILE_PIC_SIZE = 400;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    /**
     * A flag indicating that a PendingIntent is in progress and prevents us
     * from starting further intents.
     */
    private boolean mIntentInProgress;


    private boolean mSignInClicked;

    private ConnectionResult mConnectionResult;

    private SignInButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    private LinearLayout llProfileLayout;

    String name = "", courses= "", type ="", stream ="", year = "", programme = "",post = "", personPhotoUrl ;
    public static String email="";
    public static String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);


        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        btnSignOut = (Button) findViewById(R.id.btn_sign_out);
        btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);

        // Button click listeners
        btnSignIn.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnRevokeAccess.setOnClickListener(this);

        // Initializing google plus api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();


    }


    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_google_login, menu);
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
    public void onConnected(Bundle bundle) {

        mSignInClicked = false;

        btnSignIn.setVisibility(View.GONE);
        LinearLayout ll=(LinearLayout) findViewById(R.id.google_login_for_background);
        ll.setBackground(getResources().getDrawable(R.drawable.back));

        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

        /*AsyncCalling calling =new AsyncCalling();
        calling.execute();
        */
        try {
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi
                    .getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName();
            personPhotoUrl = currentPerson.getImage().getUrl();
            String personGooglePlusProfile = currentPerson.getUrl();
            email = Plus.AccountApi.getAccountName(mGoogleApiClient);
           // Parse.enableLocalDatastore(getBaseContext());
            Parse.initialize(getBaseContext(), "KaNybYEl3ipW0bdomrnWxcl98UGmFSxVrPEkFJE4", "bywJxTAclcQdSvQ0U7Vg1GU4ZpMlLIAcmPL0kMVs");

            ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
            query.whereEqualTo("Email", email);

            query.getFirstInBackground(new GetCallback<ParseObject>() {

                @Override
                public void done(ParseObject parseObject, com.parse.ParseException e) {
                    if (parseObject == null) {
                        ParseObject parseObj = new ParseObject("UserDetails");
                        System.out.println(" 123 1 new object created");
                        parseObj.put("Email", email);
                        while (!parseObj.saveInBackground().isCompleted()) {
                        }
                        isRegistered = false;
                        objectId = parseObj.getObjectId();
                    } else {
                        System.out.println("object exists. sent to Main");
                        isRegistered = true;
                        objectId = parseObject.getObjectId();
                        name = parseObject.getString("Name");
                        type = parseObject.getString("UserType");
                        courses = parseObject.getString("Courses");
                        post = parseObject.getString("Post");
                        stream = parseObject.getString("Stream");
                        year = parseObject.getString("Year");
                        programme = parseObject.getString("Programme");
                        System.out.println("230 " + objectId);
                        pref = getSharedPreferences("meeting_app", 0);
                        edit = pref.edit();

                        edit.putString("objectId", objectId);
                        edit.putString("UserPhotoUrl",personPhotoUrl);
                        edit.putString("UserEmail", email);
                        edit.putString("UserName", name);


                        edit.putString("objectId", objectId);
                        edit.putString("type", type);
                        edit.putString("courses",courses);
                        edit.putString("post", post);
                        edit.putString("stream", stream);
                        edit.putString("year", year);
                        edit.putString("programme",programme);

                        edit.commit();

                    }


                    Intent intent = null;

                    if (!isRegistered) {
                        btnSignIn.setVisibility(View.GONE);
                        LinearLayout ll=(LinearLayout) findViewById(R.id.google_login_for_background);
                        ll.setBackground(getResources().getDrawable(R.drawable.back));
                        intent = new Intent(getBaseContext(), RegistrationPage.class);

                    } else if (isRegistered) {
                        btnSignIn.setVisibility(View.GONE);
                        LinearLayout ll=(LinearLayout) findViewById(R.id.google_login_for_background);
                        ll.setBackground(getResources().getDrawable(R.drawable.back));
                        intent = new Intent(getBaseContext(), MainActivity.class);

                    }
                    intent.putExtra("objectId", objectId);
                    startActivity(intent);


                }
            });





                /*txtName.setText(personName);

                txtEmail.setText(email);
*/




        } else {
            Toast.makeText(getApplicationContext(),
                    "Person information is null", Toast.LENGTH_LONG).show();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }


        // Get user's information
        //getProfileInformation();

        // Update the UI after signin
        //updateUI(true);

    }


/*

    public class AsyncCalling extends AsyncTask<String, String , String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            getProfileInformation();

            return null;
        }

        private void getProfileInformation() {
            try {
                if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                    Person currentPerson = Plus.PeopleApi
                            .getCurrentPerson(mGoogleApiClient);
                    String personName = currentPerson.getDisplayName();
                    String personPhotoUrl = currentPerson.getImage().getUrl();
                    String personGooglePlusProfile = currentPerson.getUrl();
                      email = Plus.AccountApi.getAccountName(mGoogleApiClient);
                    Parse.enableLocalDatastore(getBaseContext());
                    Parse.initialize(getBaseContext(), "KaNybYEl3ipW0bdomrnWxcl98UGmFSxVrPEkFJE4", "bywJxTAclcQdSvQ0U7Vg1GU4ZpMlLIAcmPL0kMVs");

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
                    query.whereEqualTo("Email", email);

                    query.getFirstInBackground(new GetCallback<ParseObject>() {

                        @Override
                        public void done(ParseObject parseObject, com.parse.ParseException e) {
                            if (parseObject == null) {

                                System.out.println(" 123 1 new object created");
                                parseObject.put("Email", email);
                                while (!parseObject.saveInBackground().isCompleted()) {
                                }
                                isRegistered = false;
                                objectId = parseObject.getObjectId();
                            } else {
                                System.out.println("object exists. sent to Main");
                                isRegistered = true;
                                objectId = parseObject.getObjectId();





                                System.out.println("230 " + objectId);



                            }


                        }
                    });

                    pref = getSharedPreferences("meeting_app", 0);
                    edit = pref.edit();
                    edit.putString("objectId", objectId);
                    edit.putString("UserPhotoUrl", personPhotoUrl);
                    edit.putString("UserEmail", email);
                    edit.putString("UserName", personName);
                    edit.commit();



                */
/*txtName.setText(personName);

                txtEmail.setText(email);
*//*





                } else {
                    Toast.makeText(getApplicationContext(),
                            "Person information is null", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("987" + objectId);

            Intent intent = null;

            if(!isRegistered) {
                intent = new Intent(getBaseContext(), RegistrationPage.class);

            }
            else if(isRegistered) {
                intent = new Intent(getBaseContext(), MainActivity.class);

            }
            intent.putExtra("objectId", objectId);
            startActivity(intent);
        }
    }

*/



    /**
     * Fetching user's information name, email, profile pic
     * */

    /**
     * Background Async task to load user profile picture from url
     * */
  /*  private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadProfileImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
*/

    @Override
    public void onConnectionSuspended(int i) {

        mGoogleApiClient.connect();
        updateUI(false);

    }



    /**
     * Sign-in into google
     * */
    private void signInWithGplus() {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }
    }

    /**
     * Method to resolve any signin errors
     * */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }




    /**
     * Updating the UI, showing/hiding buttons and profile layout
     * */
    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_sign_in:
                btnSignIn.setVisibility(View.GONE);
                // Signin button clicked
                LinearLayout ll=(LinearLayout) findViewById(R.id.google_login_for_background);
                ll.setBackground(getResources().getDrawable(R.drawable.back));


                signInWithGplus();
                break;
            case R.id.btn_sign_out:
                // Signout button clicked
                signOutFromGplus();
                break;
            case R.id.btn_revoke_access:
                // Revoke access button clicked
                revokeGplusAccess();
                break;
        }


    }


    /**
     * Sign-out from google
     * */
    private void signOutFromGplus() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
            updateUI(false);
        }
    }



    /**
     * Revoking access from google
     * */
    private void revokeGplusAccess() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status arg0) {
                            Log.e(TAG, "User access revoked!");
                            mGoogleApiClient.connect();
                            updateUI(false);
                        }

                    });
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }



    @Override
    public void onConnectionFailed(ConnectionResult result) {

        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;

            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }


    }
}
