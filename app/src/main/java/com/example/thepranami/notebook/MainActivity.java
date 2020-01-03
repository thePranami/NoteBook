package com.example.thepranami.notebook;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.thepranami.notebook.Activity.RegistrationActivity;

public class MainActivity extends AppCompatActivity {
ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Data Register");

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                    Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}