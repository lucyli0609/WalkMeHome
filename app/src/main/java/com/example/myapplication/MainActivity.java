package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void walkRequest(View view){
        Intent intent = new Intent(this, signUp.class);
        startActivity(intent);
    }

    public void autoCall(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+"083446666"));
        if (intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }
}
