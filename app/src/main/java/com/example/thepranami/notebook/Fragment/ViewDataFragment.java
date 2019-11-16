package com.example.thepranami.notebook.Fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thepranami.notebook.Adapter.ViewDataAdapter;
import com.example.thepranami.notebook.Database.NotebookDatabse;
import com.example.thepranami.notebook.Model.ViewDataModel;
import com.example.thepranami.notebook.R;
import com.example.thepranami.notebook.Utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDataFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ArrayList<ViewDataModel> arrayList;
    private ViewDataAdapter viewDataAdapter;
    private NotebookDatabse notebookDatabse;

    public ViewDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_view_data, container, false);
         arrayList=new ArrayList<>();
         recyclerView=(RecyclerView)view.findViewById(R.id.viewRecycler);
        notebookDatabse = new NotebookDatabse(getActivity(), NotebookDatabse.DATABASE_NAME, null, 1);

        setData();
         return view;
    }
    public void setData(){
        Cursor cursor = notebookDatabse.getAllData();
        if (cursor.getCount()==0){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }
        while (cursor.moveToNext()){
            arrayList.add(new ViewDataModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)));
        }
////            arrayList.add(new ViewDataModel(1+i, "Ajay Bahadur ", "2500"+i, "Amaraudha",
////                    "9989897676", "Bike"));

        viewDataAdapter = new ViewDataAdapter(getContext(), arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(5));
        recyclerView.setAdapter(viewDataAdapter);
    }
}
