package com.example.thepranami.notebook.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thepranami.notebook.Database.NotebookDatabse;
import com.example.thepranami.notebook.Fragment.ViewDataFragment;
import com.example.thepranami.notebook.Fragment.ViewDetailFragment;
import com.example.thepranami.notebook.Model.ViewDataModel;
import com.example.thepranami.notebook.NoteBookActivity;
import com.example.thepranami.notebook.R;

public class EditDetailActivity extends AppCompatActivity {
    private EditText editAmount, editName, editAddress, editMobile, editOther;
    private Button saveButton;
    private ViewDataModel viewDataModel;
    private NotebookDatabse notebookDatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);
        init();
        Intent intent = getIntent();
        viewDataModel=(ViewDataModel)intent.getSerializableExtra("EditDetail");
        editAmount.setText(viewDataModel.getAmount());
        editName.setText(viewDataModel.getName());
        editAddress.setText(viewDataModel.getAddress());
        editMobile.setText(viewDataModel.getContact());
        editOther.setText(viewDataModel.getOther());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editId = viewDataModel.getSrno().toString();
                String newAmt = editAmount.getText().toString();
                String newName = editName.getText().toString();
                String newAdr = editAddress.getText().toString();
                String newMob = editMobile.getText().toString();
                String newOth = editOther.getText().toString();
                boolean isUpdate = notebookDatabse.updateData(editId, newAmt, newName, newAdr, newMob, newOth);
                if (isUpdate==true){

                    //fragment = getSupportFragmentManager().findFragmentByTag(ViewDetailFragment.TAG);
                    //vdf = (ViewDetailFragment)fragment;
                    //vdf = (ViewDetailFragment)getSupportFragmentManager().findFragmentByTag(ViewDetailFragment.TAG);
//                    ViewDetailFragment viewDetailFragment  = new ViewDetailFragment();
//                    viewDetailFragment.dismiss();
                   // new ViewDataFragment().notifyAll();
                    finish();

                    Toast.makeText(EditDetailActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditDetailActivity.this, "Data did not update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init(){
        editAmount=(EditText)findViewById(R.id.editAmount);
        editName=(EditText)findViewById(R.id.editName);
        editAddress=(EditText)findViewById(R.id.editAddress);
        editMobile=(EditText)findViewById(R.id.editMobile);
        editOther=(EditText)findViewById(R.id.editOther);
        saveButton=(Button)findViewById(R.id.saveButton);
        notebookDatabse = new NotebookDatabse(EditDetailActivity.this,
                NotebookDatabse.DATABASE_NAME, null, 1);
    }
}
