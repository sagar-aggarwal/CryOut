package com.example.aggarwals.cryout;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by AGGARWAL'S on 4/9/2016.
 */
public class HomeFragment extends Fragment {

    private ImageView askforhelp,filecomplaint,wanttohelp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.main_home_fragment,container,false);
        askforhelp = (ImageView)rootview.findViewById(R.id.ask_for_help_image_view);
        askforhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new askForHelpFragment()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Ask For Help");
            }
        });
        filecomplaint = (ImageView)rootview.findViewById(R.id.file_complaint_image_view);
        filecomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new AddFileComplaint()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("File Complaint");
            }
        });
        wanttohelp = (ImageView)rootview.findViewById(R.id.want_to_help_image_view);
        wanttohelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
            }
        });
        return rootview;
    }
}
