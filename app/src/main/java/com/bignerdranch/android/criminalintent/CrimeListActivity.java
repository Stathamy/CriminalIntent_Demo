package com.bignerdranch.android.criminalintent;
import android.support.v4.app.Fragment;
/**
 * Created by Admin on 2018/3/20.
 */

public class CrimeListActivity extends SingleFragmentActivity{
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
