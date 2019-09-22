package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class signUp extends AppCompatActivity {

    private EditText name_box;
    private EditText phone_box;
    private EditText email_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name_box = (EditText) findViewById(R.id.name_box);
        phone_box = (EditText) findViewById(R.id.phone_box);
        email_box = (EditText) findViewById(R.id.email_box);
    }

    public void gotoWalkRequest(View view){
        String name = name_box.getText().toString();
        String email = email_box.getText().toString();
        String phone = phone_box.getText().toString();
        Intent intent = new Intent(this, walkRequestActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
