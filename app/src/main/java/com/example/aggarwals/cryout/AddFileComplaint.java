package com.example.aggarwals.cryout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by AGGARWAL'S on 4/9/2016.
 */
public class AddFileComplaint extends Fragment {

    private ImageView uploadPicture;
    private static final int GALLERY_INTENT = 1;
    private RadioGroup radioGroup;
    private String complaintype,medical,police,couns,eventdetails,fullname,email;
    private CheckBox medicalbx,policebx,counsbx;
    private EditText detail,name,emailid;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mContext = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.file_complaint_layout,container,false);
        uploadPicture = (ImageView)rootview.findViewById(R.id.upload_image);
        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryintent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (galleryintent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(galleryintent, GALLERY_INTENT);
                }
            }
        });

        radioGroup = (RadioGroup)rootview.findViewById(R.id.type_radio_group);


        medicalbx = (CheckBox)rootview.findViewById(R.id.medical);


        policebx = (CheckBox)rootview.findViewById(R.id.police);


        counsbx = (CheckBox)rootview.findViewById(R.id.counselling);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
               complaintype = (String) rb.getText();
            }
        });

        detail = (EditText)rootview.findViewById(R.id.event_details);
        name = (EditText)rootview.findViewById(R.id.full_name);
        emailid = (EditText)rootview.findViewById(R.id.email);

        medical = police = couns = "0";

        medicalbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    medical = "1";
                else
                    medical = "0";
            }
        });
        policebx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    police = "1";
                else
                    police = "0";
            }
        });
        counsbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    couns = "1";
                else
                    couns = "0";
            }
        });


        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case GALLERY_INTENT:if (resultCode == getActivity().RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String PhotoPathGallery = cursor.getString(columnIndex);
                Toast.makeText(getActivity(),PhotoPathGallery,Toast.LENGTH_LONG).show();
                cursor.close();
                Picasso.with(getActivity()).load(new File(PhotoPathGallery)).resize(450,350).into(uploadPicture);
            }else{
                Toast.makeText(getActivity(),"Not",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_file_complaint_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_file_complaint:

                eventdetails = detail.getText().toString();
                fullname = name.getText().toString();
                email = emailid.getText().toString();
              new postingThread().execute();
                Toast.makeText(getActivity().getApplicationContext(), "You have CriedOut for help. We will reach out to you soon!",Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
                            break;

        }
        return super.onOptionsItemSelected(item);
    }

    public class postingThread extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            boolean result= ServerUtilities.register(mContext,complaintype,medical,police,couns,eventdetails,fullname,email);
            if (result)
                Log.d("Successfully ", "On Server");
            else {
                Log.d("Unsuccesfull Registered"," :Fail");
            }
            return null;
        }
    }
}
