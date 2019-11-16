package com.example.thepranami.notebook.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thepranami.notebook.NoteBookActivity;
import com.example.thepranami.notebook.R;

public class LoginActivity extends AppCompatActivity {
    private EditText mobile, password;
    private Button loginButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile=(EditText)findViewById(R.id.loginMobile);
        password=(EditText)findViewById(R.id.loginPassword);
        loginButton=(Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("NoteBook", MODE_PRIVATE);
                String log_mobile = mobile.getText().toString();
                String log_pass = password.getText().toString();
                String save_mobile = sharedPreferences.getString("MOBILE", "");
                String save_pass = sharedPreferences.getString("PASS", "");

                if (log_mobile.isEmpty()){
                    mobile.setError("Enter correct mobile!");
                }else if (log_pass.isEmpty()){
                    password.setError("Enter correct password!");
                }else if (log_mobile.equals(save_mobile) && log_pass.equals(save_pass)){
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent noteIntent = new Intent(LoginActivity.this, NoteBookActivity.class);
                    startActivity(noteIntent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Enter correct email or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
