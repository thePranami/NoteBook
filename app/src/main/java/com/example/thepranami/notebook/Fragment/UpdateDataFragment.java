package com.example.thepranami.notebook.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thepranami.notebook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateDataFragment extends Fragment {
    private View view;

    public UpdateDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_data, container, false);
        return view;
    }

}
