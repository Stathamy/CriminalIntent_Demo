package com.bignerdranch.android.criminalintent;
/*
* 3-20 0:20
* Joe  第七章
* */

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
/*
    用Fragment argument而不直接在CrimeFragment创建实例变量是因为实例变量不可靠，在OS重建用Fragment
 时，OS会按需回收RAM，该应用可能会被OS杀掉。
*/
    @Override
    protected Fragment createFragment(){
        UUID crimeId= (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return  CrimeFragment.newInatance(crimeId);
    }

    private static final  String EXTRA_CRIME_ID=
            "com.bignerdranch.android.criminalintent.crime_id";
    public static Intent newIntent(Context packagecontext, UUID crimeId){
        Intent intent=new Intent(packagecontext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }
}
