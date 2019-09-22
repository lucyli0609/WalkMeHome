package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class walkRequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public String pick_up;
    public String drop_off;
    public String time_select;
    public String name = "Dummy";
    public String phone = "DummyPhone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_walk_request);
        Spinner dropoff = (Spinner) findViewById(R.id.dropoff_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropoff_spinner,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropoff.setAdapter(adapter);
        dropoff.setOnItemSelectedListener(this);

        Spinner pickup = (Spinner) findViewById(R.id.pickup_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.pickup_spinner,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pickup.setAdapter(adapter2);
        pickup.setOnItemSelectedListener(this);

        Spinner time = (Spinner) findViewById(R.id.time_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.time_spinner,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter3);
        time.setOnItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            phone = extras.getString("phone");
        }

    }

    public void sendMessage(View view) throws IOException {
        //passing JSON string to backend
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        String host = "http://157.245.235.93/walkRequest?";
        String nameReq = "name="+name+"&";
        String phoneReq = "phone="+phone+"&";
        String pickupReq = "pickup="+pick_up+"&";
        String dropoffReq = "dropoff="+drop_off+"&";
        String dateReq = "date="+dtf.format(localDate)+"&";
        String timeReq = "time="+time_select;


        String urlParams = (host+nameReq+phoneReq+pickupReq+dropoffReq+dateReq+timeReq).replaceAll(" ", "_");
        Log.d("url: ", urlParams);

        URL url = new URL(urlParams);
        URLConnection conn = url.openConnection();
        Scanner in = new Scanner(new InputStreamReader(conn.getInputStream()));
        String inputLine=null;
        while(in.hasNext()) {
            inputLine = in.next().replaceAll("[\\[\\]\"\",]","");
            Log.d("Line: ", inputLine);
        }
        in.close();

        String[] students = inputLine.split(";");

        String name1 = "dummy1";
        String name2 = "dummy2";
        String phone1 = "dummy phone";
        String phone2 = "dummy phone";

        if (students.length>=2) {
            String student1 = students[0];
            String student2 = students[1];

            name1 = student1.split("\\*")[0];
            name2 = student2.split("\\*")[0];
            phone1 = student1.split("\\*")[1];
            phone2 = student2.split("\\*")[1];
        }

        //passing input data to result page
        Intent intent = new Intent(this, result.class);
        intent.putExtra("pick_up", pick_up);
        intent.putExtra("time_select", time_select);
        intent.putExtra("name1", name1);
        intent.putExtra("phone1", phone1);
        intent.putExtra("name2", name2);
        intent.putExtra("phone2", phone2);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.pickup_spinner){
            pick_up = parent.getItemAtPosition(position).toString();
            Log.d("item", pick_up);
        }else if(parent.getId()==R.id.dropoff_spinner) {
            drop_off = parent.getItemAtPosition(position).toString();
            Log.d("item", drop_off);
        }else if(parent.getId()==R.id.time_spinner){
            time_select = parent.getItemAtPosition(position).toString();
            Log.d("item",time_select);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d("item","click nothing");
    }

}
