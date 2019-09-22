package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

public class result extends AppCompatActivity  {
    HashMap<String, String> mapExits = new HashMap<>();
    HashMap<String, String> mapCoor = new HashMap<>();
    String pick_up = null;
    String details = null;
    String coordinates = null;
    TextView phone_one;
    TextView phone_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mapExits.put("Arts West", "Arts West - Exit on Elizabeth street");
        mapExits.put("Baillieu", "Baillieu library - Exit on southern lawn");
        mapExits.put("Kwong Lee Dow", "Kowng Lee Dow - Exit on Queensberry street");
        mapExits.put("Law Building", "Law building - Exit on Pelham street");
        mapExits.put("Mechanical Engineering", "Engineering block - Exit on Grattan street");
        mapExits.put("stop 1", "Stop 1 - Exit on Swanston street");
        mapExits.put("The spot", "The spot - Exit on Elizabeth street");
        mapExits.put("Union House", "Union house - Exit on Professor road");

        mapCoor.put("Arts West", "-37.797446,144.959419");
        mapCoor.put("Baillieu", "-37.798155,144.959346");
        mapCoor.put("Kwong Lee Dow", "-37.803695,144.960819");
        mapCoor.put("Law Building", "-37.802103,144.960093");
        mapCoor.put("Mechanical Engineering", "-37.799764,144.962839");
        mapCoor.put("stop 1", "-37.799763,144.964053");
        mapCoor.put("The spot", "-37.801433,144.958957");
        mapCoor.put("Union House", "-37.793284,144.966507");

        String time_select=null;
        String name1=null;
        String name2=null;
        String phone1=null;
        String phone2=null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pick_up = extras.getString("pick_up");
            time_select = extras.getString("time_select");
            name1 = extras.getString("name1");
            phone1 = extras.getString("phone1");
            name2 = extras.getString("name2");
            phone2 = extras.getString("phone2");
        }

        TextView name_one= (TextView)findViewById(R.id.name1);
        if(name1!=null) {name_one.setText(name1);}

        TextView name_two= (TextView)findViewById(R.id.name2);
        if(name2!=null) {name_two.setText(name2);}

        TextView timeDetails= (TextView)findViewById(R.id.timeDetails);
        if(time_select!=null) {timeDetails.setText(time_select);}

        phone_one = (TextView)findViewById(R.id.phone1);
        if(phone1!=null) {phone_one.setText(phone1);}

        phone_two = (TextView)findViewById(R.id.phone2);
        if(phone2!=null) {phone_two.setText(phone2);}

        TextView locationDetail = (TextView)findViewById(R.id.locationDetail);
        if(pick_up!=null) {locationDetail.setText(mapExits.get(pick_up));}
        coordinates = mapCoor.get(pick_up);
        details = mapExits.get(pick_up);

    }

    public void openMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("coor", coordinates);
        intent.putExtra("pick_up", details);
        startActivity(intent);
    }

    public void callPhone1 (View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String phone_num = phone_one.getText().toString();
        intent.setData(Uri.parse("tel:"+phone_num));
        if (intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

    public void callPhone2 (View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String phone_num = phone_two.getText().toString();
        intent.setData(Uri.parse("tel:"+phone_num));
        if (intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

}
