package com.example.thepranami.notebook;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RelativeActivity extends AppCompatActivity {
    ActionBar actionBar;
    EditText e0_id, e1_amt, e2_name, e3_address, e4_other, e5_mobile;
    Button b1_submit, b2_get, b3_update, b4_delete;
    RelativeDatabase relativeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Data Register");
        setContentView(R.layout.activity_relative);
        e0_id = (EditText)findViewById(R.id.et_id);
        e1_amt = (EditText) findViewById(R.id.et_amount);
        e2_name = (EditText) findViewById(R.id.et_name);
        e3_address = (EditText) findViewById(R.id.et_address);
        e4_other = (EditText) findViewById(R.id.et_other);
        b1_submit = (Button) findViewById(R.id.submit_button);
        b2_get = (Button) findViewById(R.id.get_button);
        b3_update = (Button)findViewById(R.id.update_button);
        b4_delete = (Button)findViewById(R.id.delete_button);
        e5_mobile=(EditText)findViewById(R.id.et_mobile);


        relativeDatabase = new RelativeDatabase(this, RelativeDatabase.DATABASE_NAME, null, 1);
        //called AddData() method
        AddData();
        getData();
        UpdateData();
        DeleteData();
        //lastInsertId();
    }

    public void AddData() {
        b1_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AMOUNT = e1_amt.getText().toString();
                String NAME = e2_name.getText().toString();
                String ADDRESS = e3_address.getText().toString();
                String OTHER = e4_other.getText().toString();
                String MOBILE = e5_mobile.getText().toString();
                if (e1_amt.getText().toString().length() == 0 || e2_name.getText().toString().length() == 0 || e3_address.getText().toString().length() == 0) {
                    Toast.makeText(RelativeActivity.this, "Please fill required field....", Toast.LENGTH_SHORT).show();
                    e0_id.setHint("Please Fill Required Field");

                } else {
                    boolean isinserted = relativeDatabase.insertData(Integer.parseInt(e1_amt.getText().toString()), e2_name.getText().toString(), e3_address.getText().toString(), e4_other.getText().toString());

                    if (isinserted = true) {
                       /* Cursor cursor = relativeDatabase.AutoId();
                        int aid = cursor.getColumnIndex("ID");
                            int auid = cursor.getInt(aid);
                            e0_id.setText(auid);  */

                        Toast.makeText(RelativeActivity.this, "Thanks, You are Welcome!!!!", Toast.LENGTH_SHORT).show();
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(MOBILE, null, "ID :" +"\n" + "AMOUNT: " + AMOUNT+"Rs.\n" + "NAME: "+ NAME+"\n" + "OTHER: "+ OTHER+"\n" +"ADDRESS: "+ ADDRESS+"\n", null, null);
                        e0_id.setHint("Thanks You are Welcome!!");
                        e1_amt.setText(null);
                        e2_name.getText().clear();
                        e3_address.setText("");
                        e4_other.setText("");
                        e5_mobile.setText("");
                      //  e0_id.setText(auid);
                    }
                    else {
                        Toast.makeText(RelativeActivity.this, "Sorry, Data is not Inserted", Toast.LENGTH_SHORT).show();
                        e0_id.setHint("Sorry, Data is not Inserted");
                    }
                }
            }
        });
    }

    public void getData() {
        b2_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = relativeDatabase.getAllData();
                if (res.getCount() == 0) {
                    msg("Error", "No data found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID  : " + res.getString(0) + "\n");
                    buffer.append("AMOUNT  : " + res.getString(1) + "\n");
                    buffer.append("NAME  : " + res.getString(2) + "\n");
                    buffer.append("ADDRESS  : " + res.getString(3) + "\n");
                    buffer.append("OTHERS  : " + res.getString(4) + "\n\n");
                }

                msg("Data" ,buffer.toString());
            }
        });
    }

    public void msg(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    //  Update method
    public void UpdateData(){
        b3_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e0_id.getText().toString().isEmpty())
                {
                    Toast.makeText(RelativeActivity.this, "Please Fill ID and Details", Toast.LENGTH_SHORT).show();
                    e0_id.setHint("Please Fill ID and Details");
                }
                else {
                    boolean isUpdate = relativeDatabase.updateData(e0_id.getText().toString(), Integer.parseInt(e1_amt.getText().toString()), e2_name.getText().toString(), e3_address.getText().toString(), e4_other.getText().toString());
                    if (isUpdate == true) {
                        Toast.makeText(RelativeActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();

                        e1_amt.setText(null);
                        e2_name.getText().clear();
                        e3_address.setText("");
                        e4_other.setText("");
                        e0_id.setText("");
                        e0_id.setHint("Data Updated Successfully");

                    } else {
                        Toast.makeText(RelativeActivity.this, "Data did not Update", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void DeleteData(){
        b4_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (e0_id.getText().toString().length() == 0)
                {
                    Toast.makeText(RelativeActivity.this, "Please fill your ID", Toast.LENGTH_SHORT).show();
                    e0_id.setHint("Please enter your ID here");

                }

                else if (e0_id.getText().toString().length() > 0)
                {
                        //  DialogBox code
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(RelativeActivity.this);
                        a_builder.setMessage("Do you want to delete it ?").setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // finish();
                                        Integer deleterow = relativeDatabase.deleteData(e0_id.getText().toString());
                                        if (deleterow  > 0)
                                        {
                                            Toast.makeText(RelativeActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                                            e0_id.setText("");
                                            e0_id.setHint("Data Deleted Successfully");
                                        }
                                        else
                                        {
                                            Toast.makeText(RelativeActivity.this, "Data did not Delete", Toast.LENGTH_SHORT).show();
                                            e0_id.setText("");
                                            e0_id.setHint("Data did not Delete");
                                        }

                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = a_builder.create();
                        alertDialog.setTitle("Alert!!!!");
                        alertDialog.show();  // DialogBox code end here

                  /*  Integer deleterow = relativeDatabase.deleteData(e0_id.getText().toString());
                    if (deleterow  > 0)
                    {
                        Toast.makeText(RelativeActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        e0_id.setText("");
                        e0_id.setHint("Data Deleted Successfully");
                    }
                    else
                    {
                        Toast.makeText(RelativeActivity.this, "Data did not Delete", Toast.LENGTH_SHORT).show();
                        e0_id.setText("");
                        e0_id.setHint("Data did not Delete");
                    } */
                }
            }

        });
    }
}
