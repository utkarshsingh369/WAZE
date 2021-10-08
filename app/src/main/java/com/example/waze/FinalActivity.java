package com.example.waze;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FinalActivity extends AppCompatActivity {
    Toast toast;
    int minCost;
    ArrayList<areaInfo> path;
    TextView textView,textViewOrigin,tvToast;
    ListView listView;
    ScrollView wholeScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        getSupportActionBar().hide();
//        Intent in = getIntent();
//        Bundle args=in.getBundleExtra("areainfo");
//        ArrayList<areaInfo> path=(ArrayList<areaInfo>) args.getSerializable("arraylist");
//        minCost=in.getIntExtra("cost",-1);
//        Toast.makeText(this, "Click on Go to Map :)", Toast.LENGTH_LONG).show();

        toast=Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);
        View view=getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.custom_toast_layout));
        tvToast=view.findViewById(R.id.tv_toast_msg);
        tvToast.setText("Click Here on 'Go To Map' to see the map");
        toast.setGravity(Gravity.TOP,0,20);
        toast.setView(view);
        toast.show();

        Intent inGet=getIntent();
        path=(ArrayList<areaInfo>) inGet.getSerializableExtra("areaInfo");
        int minCost=inGet.getIntExtra("minCost",-1);

        textView = findViewById(R.id.tvMinCost);
        listView = findViewById(R.id.listView);
        textViewOrigin = findViewById(R.id.tvOrigin);
        textView.setText(String.valueOf(minCost) + "KM");
        ArrayList<String> pathCity=new ArrayList<String>();
        for(int i=0;i<path.size();i++){
            pathCity.add(path.get(i).area_name);
        }
        textViewOrigin.setText(pathCity.get(0));
        pathCity.remove(0);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_listview, R.id.tvLV, pathCity);
        listView.setAdapter(adapter);

        ViewGroup.LayoutParams params=listView.getLayoutParams();
        params.height=300*path.size();
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    public void openMap(View v){
        Intent in2 = new Intent(FinalActivity.this, FinalMapActivity.class);
        in2.putExtra("areaInfo",path);
        startActivity(in2);
    }
}