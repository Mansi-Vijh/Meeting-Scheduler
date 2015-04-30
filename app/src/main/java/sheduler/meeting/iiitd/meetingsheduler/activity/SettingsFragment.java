package sheduler.meeting.iiitd.meetingsheduler.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import sheduler.meeting.iiitd.meetingsheduler.Adapters.ProfListViewRowAdapter;
import sheduler.meeting.iiitd.meetingsheduler.R;

public class SettingsFragment extends Fragment {
    ListView listView;
    public static String Status[] = {"Off","On"};
    public static String Sounds[] = {"Off","On"};
     int notif_status;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.settings_fragment, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();


        listView = (ListView) getView().findViewById(R.id.listViewSettings);
        String[] values = new String[] { "About Us",
                "Notification Settings",
                "Calendar Settings",
                "Sounds",
                "Feedback",
                "Rate the App"
        };


        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create(); //Read Update

                if(position == 0) {
                    alertDialog.setTitle("About Us");
                    alertDialog.setMessage("This is a meeting scheduler. It is used for scheduling meetings. Yay!  ");

                }
                if(position == 1) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Notification Settings");
                    builder.setSingleChoiceItems(Status, 1, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {

                            notif_status=item;
                        }
                    });

                }
                if(position == 2)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Sound Settings");
                    builder.setSingleChoiceItems(Sounds, 1, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {

                            notif_status=item;
                        }
                    });
                }

                    alertDialog.setButton(1,"Ok", new DialogInterface.OnClickListener(){


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog.setCancelable(true);
                alertDialog.show();  //<-- See This!
            }
               // Toast.makeText(getActivity(),
                //        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                 //       .show();



        });


    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
