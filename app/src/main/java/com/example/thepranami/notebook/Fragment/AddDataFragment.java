package com.example.thepranami.notebook.Fragment;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepranami.notebook.Activity.LoginActivity;
import com.example.thepranami.notebook.Activity.RegistrationActivity;
import com.example.thepranami.notebook.Database.NotebookDatabse;
import com.example.thepranami.notebook.R;
import com.example.thepranami.notebook.Service.ServiceOnSuccess;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDataFragment extends Fragment {
    private View view;
    private NotebookDatabse notebookDatabse;
    private EditText donor_id, amount, name, address, mobile, other;
    private Button submitButton;
    public AddDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_data, container, false);
        notebookDatabse = new NotebookDatabse(getActivity(), NotebookDatabse.DATABASE_NAME, null, 1);
        init();
        addData();
        return view;
    }
    public void init(){
        //donor_id=(EditText)view.findViewById(R.id.donor_id);
        amount=view.findViewById(R.id.addAmount);
        name=view.findViewById(R.id.addName);
        address=view.findViewById(R.id.addAddress);
        mobile=view.findViewById(R.id.addMobile);
        other=view.findViewById(R.id.addOther);
        submitButton=view.findViewById(R.id.submitButton);
    }
    //add data
    public void addData() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                String DONOR_ID = String.format("%04d", random.nextInt(10000));
                String AMOUNT = amount.getText().toString();
                String NAME = name.getText().toString();
                String ADDRESS = address.getText().toString();
                String MOBILE = mobile.getText().toString();
                String OTHER = other.getText().toString();
                if (AMOUNT.isEmpty() || NAME.isEmpty() || ADDRESS.isEmpty()) {
                    Toast.makeText(getContext(), "Please fill required field....", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("aaaaaaaa", DONOR_ID+"/"+DONOR_ID+"/"+AMOUNT+"/"+NAME+"/"+ADDRESS+"/"+MOBILE+"/"+OTHER);
                    boolean isDataInsert = notebookDatabse.insertData(Integer.parseInt(AMOUNT),
                            NAME, ADDRESS, MOBILE, OTHER);

                    if (isDataInsert == true) {
//                        Cursor cursor = notebookDatabse.getAllData();
//                        String s = cursor.getString(0);
//                        Log.e("aaaaaa", s);
                        NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(getActivity())
                                .setSmallIcon(R.drawable.ic_add_data)
                                .setContentTitle("MyData")
                                .setContentText("One new data inserted")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        Intent notiIntent = new Intent(getActivity(), LoginActivity.class);
                        notiIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0,
                                notiIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        notiBuilder.setContentIntent(pendingIntent);
                        notificationManager.notify(0, notiBuilder.build());

                        Cursor cursor = notebookDatabse.getMaxId();
                        Toast.makeText(getContext(), "Thanks "+cursor.getString(0), Toast.LENGTH_SHORT).show();
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(MOBILE, null, "Hi "+NAME+" you donate Rs. "+AMOUNT+" your donation ID is "+cursor.getString(0)+"\n\nThank you",
                                null, null);

//                        Intent serviceIntent = new Intent(getActivity(), ServiceOnSuccess.class);
//                        getActivity().startService(serviceIntent);
                    }
                    else {
                        Toast.makeText(getContext(), "Sorry, Data is not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
