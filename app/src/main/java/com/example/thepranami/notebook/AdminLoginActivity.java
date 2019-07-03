package com.example.thepranami.notebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {
    ActionBar actionBar;
    EditText e1, e2;
    Button b1_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Data Register");
        setContentView(R.layout.activity_admin_login);
        e1 = (EditText)findViewById(R.id.et_uname);
        e2 = (EditText)findViewById(R.id.et_pass);
        b1_log = (Button)findViewById(R.id.login_button);

        b1_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if (s1.isEmpty() || s2.isEmpty())
                {
                    Toast.makeText(AdminLoginActivity.this, "Please enter valid Username & Password", Toast.LENGTH_SHORT).show();
                }

                else if (s1.equals("skp") && s2.equals("skp"))
                {
                    Intent intent = new Intent(AdminLoginActivity.this, NoteBookActivity.class);
                   // userintent.putExtra("name", e1.getText().toString());
                    startActivity(intent);
                    e1.setText(null);
                    e2.setText(null);
                }
                else
                {
                    Toast.makeText(AdminLoginActivity.this, "Sorry You have entered wrong credentials.....", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
