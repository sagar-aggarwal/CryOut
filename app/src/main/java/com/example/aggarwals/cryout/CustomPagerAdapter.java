package com.example.aggarwals.cryout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by AGGARWAL'S on 4/10/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    private Activity activity;
    private Button gotit;

    public CustomPagerAdapter(Context context,Activity activity) {
        mContext = context;
        this.activity = activity;

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        gotit = null;
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);
        if (customPagerEnum.getLayoutResId() == R.layout.splash_screen_5) {
            gotit = (Button) layout.findViewById(R.id.button_gotit);
            gotit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext,MainActivity.class);
                    activity.startActivityForResult(i,SlidingActivity.requestcode);
                }
            });
        }

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return CustomPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }

}