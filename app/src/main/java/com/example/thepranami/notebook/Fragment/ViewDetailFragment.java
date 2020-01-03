package com.example.thepranami.notebook.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepranami.notebook.Activity.EditDetailActivity;
import com.example.thepranami.notebook.Model.ViewDataModel;
import com.example.thepranami.notebook.R;

//import com.example.thepranami.shadiregister.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDetailFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private TextView srno, name, amount, other, address, contact;
    private ViewDataModel viewDataModel;
    private ImageView phone, message, share, edit;
    private ImageButton phone1;
    View view;
    public static final String TAG = "MyDialogFragment";
    public ViewDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_detail, container, false);
        Bundle bundle = getArguments();
        viewDataModel = (ViewDataModel)bundle.getSerializable("viewdata");
        init();
        return view;
    }

    private void init(){
        srno=(TextView)view.findViewById(R.id.srno);
        name=(TextView)view.findViewById(R.id.name);
        amount=(TextView)view.findViewById(R.id.amt);
        other=(TextView)view.findViewById(R.id.other);
        address=(TextView)view.findViewById(R.id.address);
        contact=(TextView)view.findViewById(R.id.contact);
        phone=(ImageView)view.findViewById(R.id.phone);
        edit=(ImageView) view.findViewById(R.id.editDetail);
        message=(ImageView)view.findViewById(R.id.message);
        share=(ImageView)view.findViewById(R.id.share);
        phone.setOnClickListener(this);
        message.setOnClickListener(this);
        share.setOnClickListener(this);
        edit.setOnClickListener(this);

        //Intent intent = getIntent();
        //Bundle bundle = intent.getExtras();
        srno.setText(viewDataModel.getSrno());
        name.setText(viewDataModel.getName());
        amount.setText("₹ "+viewDataModel.getAmount());
        other.setText(viewDataModel.getOther());
        address.setText(viewDataModel.getAddress());
        contact.setText("+91- "+viewDataModel.getContact());
    }
    public void setData(){

    }

    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.phone:
//                AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F); // change values as you want
//                alpha.setDuration(0); // Make animation instant
//                alpha.setFillAfter(true); // Tell it to persist after the animation ends
//                phone.startAnimation(alpha);
               //phone.animate().alpha(45).setDuration(150).start();
                String phone = "+91"+viewDataModel.getContact().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" +phone));
                startActivity(intent);
                break;
            case R.id.message:
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
                String no = viewDataModel.getContact().toString();
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.setData(Uri.parse("sms:" + no));
                smsIntent.putExtra("sms_body", "Good Morning ! how r U ?");
                startActivity(smsIntent);
               // message.setImageAlpha(45);
                break;
            case R.id.share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String pName = viewDataModel.getName();
                // String temp = "https://play.google.com/store/apps/details?id=com.facebook.orca";
                String link = "https://play.google.com/store/apps/details?id=com.webrication.sgf";
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out \n"+pName+"\n at "+link);
                startActivity(shareIntent);
                break;
            case R.id.editDetail:
                Intent editIntent = new Intent(getContext(), EditDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("EditDetail", viewDataModel);
                editIntent.putExtras(bundle);
                startActivityForResult(editIntent, 111);
                //dismiss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==111){
            init();
        }
    }
}
