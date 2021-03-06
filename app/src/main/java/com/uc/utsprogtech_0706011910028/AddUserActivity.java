package com.uc.utsprogtech_0706011910028;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uc.utsprogtech_0706011910028.Model.SaveData;
import com.uc.utsprogtech_0706011910028.Model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity implements TextWatcher {

    TextInputLayout input_fname, input_age, input_address;
    TextInputEditText nameTxt, ageTxt, addressTxt;
    Button btn_add;
    String fname, address, age;
    String getname, getage, getaddress;
    int pos;
    ArrayList<User> mContacts = SaveData.saveList;

    Toolbar toolbar;

    static ArrayList<User> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        input_fname = findViewById(R.id.input_fname);
        input_age = findViewById(R.id.input_age);
        input_address = findViewById(R.id.input_address);
        nameTxt = findViewById(R.id.nameEditText);
        ageTxt = findViewById(R.id.ageEditText);
        addressTxt = findViewById(R.id.addressEditText);
        btn_add = findViewById(R.id.btn_savedata);
        toolbar = findViewById(R.id.toolbar_adduser);
        final LoadingDialog loadingDialog =  new LoadingDialog(AddUserActivity.this);

        input_fname.getEditText().addTextChangedListener(this);
        input_age.getEditText().addTextChangedListener(this);
        input_address.getEditText().addTextChangedListener(this);

        Intent intentget = getIntent();
        pos = intentget.getIntExtra("position",0);
        Log.d("intentcallme", String.valueOf(pos));

        if(pos>=0){
            btn_add.setText("Update Data");
            toolbar.setTitle("Edit User");
            getname = mContacts.get(pos).getName();
            getage = mContacts.get(pos).getAge();
            getaddress = mContacts.get(pos).getAddress();

            Log.d("namekontak",getname);
            Log.d("agekontak",getage);
            Log.d("addresskontak",getaddress);
            nameTxt.setText(getname);
            getage = getage.replaceAll("\\D+","");
            ageTxt.setText(getage);
            addressTxt.setText(getaddress);

        }



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(AddUserActivity.this, MainActivity.class);

                if(pos>=0){
                    mContacts.get(pos).setName(fname);
                    mContacts.get(pos).setAge(age + " years old");
                    mContacts.get(pos).setAddress(address);

                } else {
                    User contact = new User(fname, address, age + " years old");
                    intent.putExtra("dataUser",contact);
                    SaveData.saveList.add(contact);
                }

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
