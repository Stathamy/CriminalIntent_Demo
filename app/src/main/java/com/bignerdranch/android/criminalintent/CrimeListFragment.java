package com.bignerdranch.android.criminalintent;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


/**
 * Created by Admin on 2018/3/20.
 */

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecylerView;
    private  CrimeAdapter mAdapter;
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private Crime mCrime;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
            Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecylerView=(RecyclerView)view.findViewById(R.id.crime_recyler_view);
        mCrimeRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return  view;
    }
    private void updateUI(){
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimes=crimeLab.getCrimes();
        mAdapter=new CrimeAdapter(crimes);
        mCrimeRecylerView.setAdapter(mAdapter);
    }
    private class CrimeHolder extends RecyclerView.ViewHolder{
        public CrimeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            mTitleTextView=(TextView)itemView.findViewById(R.id.crime_title);
            mDateTextView=(TextView)itemView.findViewById(R.id.crime_date);
        }

        public void bind(Crime crime) {
            mCrime=crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }
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
