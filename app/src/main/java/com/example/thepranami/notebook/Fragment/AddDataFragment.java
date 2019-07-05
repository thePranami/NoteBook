package com.example.thepranami.notebook.Fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepranami.notebook.Database.NotebookDatabse;
import com.example.thepranami.notebook.R;

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

                    if (isDataInsert = true) {
//                        Cursor cursor = notebookDatabse.getAllData();
//                        String s = cursor.getString(0);
//                        Log.e("aaaaaa", s);
                        Toast.makeText(getContext(), "Thanks, You are Welcome!!!!", Toast.LENGTH_SHORT).show();
//                        SmsManager smsManager = SmsManager.getDefault();
//                        smsManager.sendTextMessage(MOBILE, null, "ID :" +"\n" + "AMOUNT: " + AMOUNT+"Rs.\n" + "NAME: "+ NAME+"\n" + "OTHER: "+ OTHER+"\n" +"ADDRESS: "+ ADDRESS+"\n", null, null);
//                        e0_id.setHint("Thanks You are Welcome!!");
//                        e1_amt.setText(null);
//                        e2_name.getText().clear();
//                        e3_address.setText("");
//                        e4_other.setText("");
//                        e5_mobile.setText("");
//                        //  e0_id.setText(auid);
                    }
                    else {
                        Toast.makeText(getContext(), "Sorry, Data is not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
