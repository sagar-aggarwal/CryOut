package com.example.aggarwals.cryout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by AGGARWAL'S on 4/10/2016.
 */
public class WantToHelpFragment extends Fragment {

    private TextView SingupActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.want_to_help_layout,container,false);
        SingupActivity = (TextView)rootview.findViewById(R.id.signup_activity_text);
        SingupActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),SidnUp.class);
                startActivity(i);
            }
        });
        return rootview;
    }
}
