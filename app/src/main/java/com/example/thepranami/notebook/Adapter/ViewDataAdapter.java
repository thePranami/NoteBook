package com.example.thepranami.notebook.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepranami.notebook.Activity.ViewActivity;
import com.example.thepranami.notebook.Fragment.ViewDetailFragment;
import com.example.thepranami.notebook.Model.ViewDataModel;
import com.example.thepranami.notebook.R;

import java.util.ArrayList;

public class ViewDataAdapter extends RecyclerView.Adapter<ViewDataAdapter.DataHolder> {
    private Context context;
    private ArrayList<ViewDataModel> arrayList;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public ViewDataAdapter(Context context, ArrayList<ViewDataModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_data, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        final ViewDataModel viewDataModel = arrayList.get(position);
        holder.srno.setText(viewDataModel.getSrno());
        holder.amount.setText("₹ "+viewDataModel.getAmount());
        holder.name.setText(viewDataModel.getName());
        holder.address.setText(viewDataModel.getAddress());
        holder.mobile.setText("+91- "+viewDataModel.getContact());
        holder.other.setText(viewDataModel.getOther());
        if (holder.other.getText().toString().isEmpty()){
            holder.other.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(context, ViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("viewdata", viewDataModel);
                //intent.putExtras(bundle);
                //context.startActivity(intent);
                //fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                ViewDetailFragment viewDetailFragment = new ViewDetailFragment();
                viewDetailFragment.setArguments(bundle);
                viewDetailFragment.show(((FragmentActivity)context).getSupportFragmentManager(), ViewDetailFragment.TAG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        private TextView srno, name, amount, address, mobile, other;
        public DataHolder(View itemView) {
            super(itemView);
            srno=(TextView)itemView.findViewById(R.id.srno);
            name=(TextView)itemView.findViewById(R.id.viewName);
            amount=(TextView)itemView.findViewById(R.id.viewAmount);
            address=(TextView)itemView.findViewById(R.id.viewAddress);
            mobile=(TextView)itemView.findViewById(R.id.viewContact);
            other=(TextView)itemView.findViewById(R.id.viewOther);
        }
    }
}
