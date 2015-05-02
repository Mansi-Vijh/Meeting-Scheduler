package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.io.InputStream;

import sheduler.meeting.iiitd.meetingsheduler.R;


public class ProfilePage extends Fragment implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    private static final int RC_SIGN_IN = 0;
    // Logcat tag
    private static final String TAG = "MainActivity";

    // Profile pic image size in pixels
    private static final int PROFILE_PIC_SIZE = 150;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;


    SharedPreferences pref;
    SharedPreferences.Editor edit;

    /**
     * A flag indicating that a PendingIntent is in progress and prevents us
     * from starting further intents.
     */
    private boolean mIntentInProgress;

    public String email;
    private boolean mSignInClicked;

    private ConnectionResult mConnectionResult;


    private Button btnSignOut, btnEditDetails;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    private LinearLayout llProfileLayout;



    public ProfilePage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onStart() {
        super.onStart();




        btnSignOut = (Button) getView().findViewById(R.id.btn_sign_out);
        btnEditDetails= (Button) getView().findViewById(R.id.profile_edit_details);
        imgProfilePic = (ImageView) getView().findViewById(R.id.imgProfilePic);
        txtName = (TextView) getView().findViewById(R.id.txtName);
        txtEmail = (TextView) getView().findViewById(R.id.txtEmail);


        // Button click listeners
        btnEditDetails.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);


        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();

        mGoogleApiClient.connect();

        getProfileInformation();



    }

    private void updateUI(boolean isSignedIn) {
     // Nothing to do for now...
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_page, container, false);
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btn_sign_out:
                // Signout button clicked

                // Clear cache...


                pref = getActivity().getSharedPreferences("meeting_app", 0);
                edit = pref.edit();
                edit.putString("objectId", " ");
                edit.putString("UserPhotoUrl"," ");
                edit.putString("UserEmail", " ");
                edit.putString("UserName", " ");


                edit.putString("objectId", " ");
                edit.putString("type", " ");
                edit.putString("courses"," ");
                edit.putString("post", " ");
                edit.putString("stream", " ");
                edit.putString("year", " ");
                edit.putString("programme"," ");
                edit.commit();

                signOutFromGplus();
                break;
            case R.id.profile_edit_details:
                // Signout button clicked
                Intent intent = new Intent(getActivity(),  ProfileEdit.class);
             //   intent.putExtra("type", "Student");
                startActivity(intent);
                break;

        }

    }


    /**
     * Sign-out from google
     * */
    private void signOutFromGplus() {


        System.out.println("234 logout");

            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
            updateUI(false);

            System.out.println("234 logout in");
            Intent intent = new Intent(getActivity(),  GoogleLogin.class);
            startActivity(intent);


    }

    @Override
    public void onConnected(Bundle bundle) {
        mSignInClicked = false;

        // updateUI(true);

    }


    private void getProfileInformation() {
        try {

            pref = getActivity().getSharedPreferences("meeting_app", 0);
            edit = pref.edit();
            String personName =pref.getString("UserName", "");

            String personPhotoUrl=pref.getString("UserPhotoUrl",""); ;
            String personGooglePlusProfile="";
            String email=pref.getString("UserEmail", "");;


            System.out.println(" 234 email : "+email+" url "+personPhotoUrl +" name " + personName);
            txtName.setText(personName);
             txtEmail.setText(email);

            personPhotoUrl = personPhotoUrl.substring(0,
                    personPhotoUrl.length() - 2)
                    + PROFILE_PIC_SIZE;

            new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);





        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
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



    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
        updateUI(false);
    }

    /**
     * Method to resolve any signin errors
     * */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(getActivity(), RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), getActivity(),
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
