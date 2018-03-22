package com.bignerdranch.android.criminalintent;
/*
* 3-20 0:20
* Joe  第七章
* */

import android.support.v4.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeFragment();
    }

}
