package com.example.thepranami.notebook.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepranami.notebook.NoteBookActivity;
import com.example.thepranami.notebook.R;

import java.util.Random;

public class RegistrationActivity extends AppCompatActivity {
    private EditText bride, brideGroom, regardsPerson, mobile, email, pass, conPass, otp;
    private Button registerButton, nextButton;
    private TextView alreadyLogin;
    SharedPreferences sharedPreferences;
    SmsManager smsManager;
    TelephonyManager telephonyManager;
    Random random;
    private LinearLayout regLayout;
    private RelativeLayout otpLayout;
    private String brideName, groomName, regardName, mobileNo, password, conPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();

        final String generatedOTP[] = {null};

        sharedPreferences = getSharedPreferences("NoteBook", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("REG_STATUS", false);
        if (check){
            Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brideName = bride.getText().toString();
                groomName = brideGroom.getText().toString();
                regardName = regardsPerson.getText().toString();
                mobileNo = mobile.getText().toString();
                //String emailId = email.getText().toString();
                password = pass.getText().toString();
                conPassword = conPass.getText().toString();
                if (brideName.isEmpty() || groomName.isEmpty() ||
                        regardName.isEmpty() || mobileNo.isEmpty()
                        || password.isEmpty() || conPassword.isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please fill all field values.", Toast.LENGTH_SHORT).show();
                } else if (password.equals(conPassword)){
                    Random random = new Random();
                    generatedOTP[0] = String.format("%04d", random.nextInt(10000));
                    Log.d(">>>>>>>>>>MyApp>>>>>", "Generated Password====>> : " + generatedOTP[0]);

                    telephonyManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                    smsManager = SmsManager.getDefault();

                    smsManager.sendTextMessage(mobileNo, null, "Your OTP is: " + generatedOTP[0]+"\n Please do not share this anyone else.", null, null);
                    //textView.setText(generatedOTP[0]);
                    regLayout.setVisibility(View.GONE);
                    otpLayout.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "Password is not matched", Toast.LENGTH_SHORT).show();
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enterOtp = otp.getText().toString();
                if (enterOtp.isEmpty()){
                   otp.setError("enter 4 digit otp");
                }else if (enterOtp.equals(generatedOTP[0])){
                    final SharedPreferences.Editor editor = sharedPreferences.edit();
                    brideName = bride.getText().toString();
                    groomName = brideGroom.getText().toString();
                    regardName = regardsPerson.getText().toString();
                    mobileNo = mobile.getText().toString();
                    //String emailId = email.getText().toString();
                    password = pass.getText().toString();
                    conPassword = conPass.getText().toString();
                    editor.putString("BRIDE", brideName);
                    editor.putString("GROOM", groomName);
                    editor.putString("REGARD", regardName);
                    editor.putString("MOBILE", mobileNo);
                    //editor.putString("EMAIL", emailId);
                    editor.putString("PASS", password);
                    editor.putString("CONPASS", conPassword);
                    editor.putBoolean("REG_STATUS",true);
                    editor.commit();
                    Toast.makeText(RegistrationActivity.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                    Intent noteIntent = new Intent(RegistrationActivity.this, NoteBookActivity.class);
                    startActivity(noteIntent);
                    finish();
                }
                else {
                    otp.setError("enter correct OTP");
                }
            }
        });
        alreadyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    private void init(){
        bride=(EditText)findViewById(R.id.bride);
        brideGroom=(EditText)findViewById(R.id.bridegroom);
        regardsPerson=(EditText)findViewById(R.id.regards);
        mobile=(EditText)findViewById(R.id.mobile);
        //email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.password);
        conPass=(EditText)findViewById(R.id.con_password);
        registerButton=(Button)findViewById(R.id.registerButton);
        alreadyLogin=(TextView)findViewById(R.id.alreadyloginTextView);
        regLayout=(LinearLayout)findViewById(R.id.regislayout);
        otpLayout=(RelativeLayout)findViewById(R.id.otplayout);
        nextButton=(Button)findViewById(R.id.nextButton);
        otp=(EditText)findViewById(R.id.otp);
    }
}
