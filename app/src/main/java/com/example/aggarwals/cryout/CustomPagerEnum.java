package com.example.aggarwals.cryout;

/**
 * Created by AGGARWAL'S on 4/10/2016.
 */
public enum CustomPagerEnum {

    One(1, R.layout.splash_screen_1),
    Two(2, R.layout.splash_screen_2),
    Three(3, R.layout.splash_screen_3),
    Four(4, R.layout.splash_screen_4),
    Five(5,R.layout.splash_screen_5);

    private int mTitleResId;
    private int mLayoutResId;

    CustomPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}