package com.uc.utsprogtech_0706011910028;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uc.utsprogtech_0706011910028.Model.SaveData;
import com.uc.utsprogtech_0706011910028.Model.User;
import com.uc.utsprogtech_0706011910028.LoadingDialog;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    TextView nameContact, ageContact, addressContact;

    ArrayList<User> mContacts = SaveData.saveList;
    String name, address;
    String age;
    Button editBtn, deleteBtn;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final Intent intent = getIntent();
        final int position = intent.getIntExtra("contact_info",0);
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
        toolbar = findViewById(R.id.userdetail_toolbar);
        final LoadingDialog loadingDialog =  new LoadingDialog(UserActivity.this);

        nameContact.setText(name);
        ageContact.setText(age);
        addressContact.setText(address);

        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,AddUserActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
                finish();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(UserActivity.this,MainActivity.class);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                mContacts.remove(position);
                                loadingDialog.startLoadingDialog();
                                Handler handler =new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadingDialog.dismissDialog();
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(UserActivity.this, "Delete success!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                },5000);
//                                Toast.makeText(UserActivity.this, "Delete Success",
//                                        Toast.LENGTH_LONG).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setMessage("Are you sure you want to delete " + name + " ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });

    }
}