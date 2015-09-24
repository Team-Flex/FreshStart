package com.assignments.koorong.firsteverfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by koorong on 9/21/2015.
 */
public class MainFragment extends Fragment {
    public MainFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        //now we have a view...

        TextView txtMessage = (TextView) view.findViewById(R.id.listTxt);
        txtMessage.setText("Hello from fragment!");

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
