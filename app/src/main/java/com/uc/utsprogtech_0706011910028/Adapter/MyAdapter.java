package com.uc.utsprogtech_0706011910028.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.utsprogtech_0706011910028.Model.SaveData;
import com.uc.utsprogtech_0706011910028.Model.User;
import com.uc.utsprogtech_0706011910028.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


    ArrayList<User> mContacts = SaveData.saveList;
    private OnContactListener mOnContactListener;

    public MyAdapter(ArrayList<User> mContacts, OnContactListener onContactListener) {
        this.mContacts = mContacts;
        this.mOnContactListener = onContactListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_rows,parent,false);
        return new MyViewHolder(view, mOnContactListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mFullName.setText(mContacts.get(position).getName());
        holder.mAge.setText(mContacts.get(position).getAge());
        holder.mAddress.setText(mContacts.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mFullName, mAge, mAddress;
        OnContactListener onContactListener;

        public MyViewHolder(@NonNull View itemView, OnContactListener onContactListener) {
            super(itemView);

            mFullName = itemView.findViewById(R.id.view_fname);
            mAge = itemView.findViewById(R.id.view_age);
            mAddress = itemView.findViewById(R.id.view_address);
            this.onContactListener = onContactListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactListener{
        void onContactClick(int position);
    }
}