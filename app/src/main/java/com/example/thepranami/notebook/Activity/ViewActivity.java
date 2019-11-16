package com.example.thepranami.notebook.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.thepranami.notebook.Model.ViewDataModel;
import com.example.thepranami.notebook.R;

public class ViewActivity extends AppCompatActivity {
    private TextView srno, name, amount, other, address, contact;
    private ViewDataModel viewDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        init();
    }

    private void init(){
        srno=(TextView)findViewById(R.id.srno);
        name=(TextView)findViewById(R.id.name);
        amount=(TextView)findViewById(R.id.amt);
        other=(TextView)findViewById(R.id.other);
        address=(TextView)findViewById(R.id.address);
        contact=(TextView)findViewById(R.id.contact);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        viewDataModel = (ViewDataModel)bundle.getSerializable("viewdata");
        srno.setText(viewDataModel.getSrno());
        name.setText(viewDataModel.getName());
        amount.setText(viewDataModel.getAmount());
        other.setText(viewDataModel.getOther());
        address.setText(viewDataModel.getAddress());
        contact.setText(viewDataModel.getContact());
    }
}
