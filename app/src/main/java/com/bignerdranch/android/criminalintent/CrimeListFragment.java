package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;


/**
 * Created by Admin on 2018/3/20.
 */

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecylerView;
    private RecyclerView mRequiresPoliceView;
 //   private RecyclerView mNoRequiresPoliceView;
    private CrimeAdapter mAdapter;
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private ImageView mSolvedImageView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecylerView = (RecyclerView) view.findViewById(R.id.crime_recyler_view);
        mCrimeRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));

         updateUI();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(mAdapter==null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecylerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
            //该方法刷新全部可见列表项，若只刷新一项则用：notifyItemChanged(int)
        }

    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime mCrime;
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView= (ImageView) itemView.findViewById(R.id.crime_solved);//
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }
        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            String date = (String) DateFormat.format("E, MMMM dd, yyyy h:mmaa" , mCrime.getDate());
            mDateTextView.setText(date);
            mSolvedImageView.setVisibility(crime.isSolved()?View.VISIBLE:View.GONE);       }


    }



    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;
        public  CrimeAdapter(List<Crime> crimes){
            mCrimes=crimes;}
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());

            return new CrimeHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime=mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
                                                                                                                                                                                                                                                                                                                                                                return mCrimes.size();
        }
    }
}
