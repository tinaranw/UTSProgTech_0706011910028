package com.uc.utsprogtech_0706011910028;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.utsprogtech_0706011910028.Model.SaveData;
import com.uc.utsprogtech_0706011910028.Model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity implements TextWatcher {

    TextInputLayout input_fname, input_age, input_address;
    Button btn_add;
    String fname, address, age;


    Toolbar toolbar;

    static ArrayList<User> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        input_fname = findViewById(R.id.input_fname);
        input_age = findViewById(R.id.input_age);
        input_address = findViewById(R.id.input_address);
        btn_add = findViewById(R.id.btn_savedata);
        toolbar = findViewById(R.id.toolbar_adduser);
        final LoadingDialog loadingDialog =  new LoadingDialog(AddUserActivity.this);

        input_fname.getEditText().addTextChangedListener(this);
        input_age.getEditText().addTextChangedListener(this);
        input_address.getEditText().addTextChangedListener(this);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User contact = new User(fname, address, age + " years old");
                final Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                intent.putExtra("dataUser",contact);
                SaveData.saveList.add(contact);
                loadingDialog.startLoadingDialog();
                Handler handler =new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        startActivity(intent);
                        finish();
                    }
                },5000);

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        fname = input_fname.getEditText().getText().toString().trim();
        age = input_age.getEditText().getText().toString().trim();
        address = input_address.getEditText().getText().toString().trim();

        if(!fname.isEmpty() && !address.isEmpty() && !age.isEmpty()){
            btn_add.setEnabled(true);
        } else {
            btn_add.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}
