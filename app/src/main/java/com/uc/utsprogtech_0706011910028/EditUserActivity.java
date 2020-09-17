package com.uc.utsprogtech_0706011910028;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uc.utsprogtech_0706011910028.Model.SaveData;
import com.uc.utsprogtech_0706011910028.Model.User;

import java.util.ArrayList;

public class EditUserActivity extends AppCompatActivity implements TextWatcher {

    TextInputEditText nameTxt, ageTxt, addressTxt;
    String name, age, address;
    ArrayList<User> mContacts = SaveData.saveList;
    Button editBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        nameTxt = findViewById(R.id.edit_nametext);
        ageTxt = findViewById(R.id.edit_agetext);
        addressTxt = findViewById(R.id.edit_addresstext);
        editBtn = findViewById(R.id.btn_editdata);

        Intent intent = getIntent();
        final int position = intent.getIntExtra("position",0);
        name = mContacts.get(position).getName();
        age = mContacts.get(position).getAge();
        address = mContacts.get(position).getAddress();

        Log.d("name",name);
        Log.d("age",age);
        Log.d("address",address);

        nameTxt.setText(name);
        ageTxt.setText(age);
        addressTxt.setText(address);

        nameTxt.addTextChangedListener(this);
        ageTxt.addTextChangedListener(this);
        addressTxt.addTextChangedListener(this);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mContacts.get(position).setName(name);
                mContacts.get(position).setAge(age);
                mContacts.get(position).setAddress(address);
                Intent intent = new Intent(EditUserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        name = nameTxt.getText().toString().trim();
        age = ageTxt.getText().toString().trim();
        address = addressTxt.getText().toString().trim();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}