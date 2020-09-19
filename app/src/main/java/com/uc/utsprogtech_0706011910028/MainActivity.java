package com.uc.utsprogtech_0706011910028;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.utsprogtech_0706011910028.Adapter.MyAdapter;
import com.uc.utsprogtech_0706011910028.Model.SaveData;
import com.uc.utsprogtech_0706011910028.Model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MyAdapter.OnContactListener {

    FloatingActionButton btn_add;
    TextView nodata;
    ArrayList<User> mContacts = SaveData.saveList;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nodata = findViewById(R.id.nodataTxt);

        if(mContacts.isEmpty()){
            nodata.setVisibility(View.VISIBLE);
        } else {
            nodata.setVisibility(View.INVISIBLE);
        }

        btn_add = findViewById(R.id.button_add_main);
//        nodata = findViewById(R.id.noData);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, AddUserActivity.class);
                intent.putExtra("position",-1);
                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(mContacts, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onContactClick(int position) {
        Log.d("clickwhy","onContactClick: clicked " + position);
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("contact_info",position);
        startActivity(intent);

    }
}
