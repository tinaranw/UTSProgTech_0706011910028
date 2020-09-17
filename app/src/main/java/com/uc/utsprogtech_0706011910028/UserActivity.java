package com.uc.utsprogtech_0706011910028;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.uc.utsprogtech_0706011910028.Model.SaveData;
import com.uc.utsprogtech_0706011910028.Model.User;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    TextView nameContact, ageContact, addressContact;

    ArrayList<User> mContacts = SaveData.saveList;
    String name, address;
    String age;
    User contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        int position = intent.getIntExtra("contact_info",0);
        Log.d("position", String.valueOf(position));
        name = mContacts.get(position).getName();
        age = mContacts.get(position).getAge();
        address = mContacts.get(position).getAddress();

        Log.d("name",name);
        Log.d("age",age);
        Log.d("address",address);

        nameContact = findViewById(R.id.name_title);
        ageContact = findViewById(R.id.age_title);
        addressContact = findViewById(R.id.address_title);

        nameContact.setText(name);
        ageContact.setText(age);
        addressContact.setText(address);

    }
}