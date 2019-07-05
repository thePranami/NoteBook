package com.example.thepranami.notebook.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thepranami.notebook.Model.ViewDataModel;
import com.example.thepranami.notebook.R;

import java.util.ArrayList;

public class ViewDataAdapter extends RecyclerView.Adapter<ViewDataAdapter.DataHolder> {
    private Context context;
    private ArrayList<ViewDataModel> arrayList;

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
        ViewDataModel viewDataModel = arrayList.get(position);
        holder.srno.setText(viewDataModel.getSrno());
        holder.amount.setText("₹ "+viewDataModel.getAmount());
        holder.name.setText(viewDataModel.getName());
        holder.address.setText(viewDataModel.getAddress());
        holder.mobile.setText(viewDataModel.getContact());
        holder.other.setText(viewDataModel.getOther());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        TextView srno, name, amount, address, mobile, other;
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
