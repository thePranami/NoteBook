package com.example.thepranami.notebook.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.thepranami.notebook.NoteBookActivity;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        if (!NetworkUtility.isNetworkAvailable(context)){
//            Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show();
//        }else {
//            Intent intent1 = new Intent(context, NoteBookActivity.class);
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent1);
//
//        }
    }
}
