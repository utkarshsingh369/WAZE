package com.example.waze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> cities;
    ImageView imageView;
    AutoCompleteTextView tvSource,tvDest;
    Toast toast;
    TextView tvToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        Toast.makeText(this, "Enter cities of maharashtra :)", Toast.LENGTH_SHORT).show();
//        ToastCustom tc=new ToastCustom("Enter cities of maharashtra",getApplicationContext(),Toast.LENGTH_LONG,this);
//        tc.show();
        toast=Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG);
        View view=getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.custom_toast_layout));
        tvToast=view.findViewById(R.id.tv_toast_msg);
        tvToast.setText("Enter cities of Maharashtra only!");
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM,0,30);
        toast.show();
        imageView=findViewById(R.id.roughImg);
        tvSource=findViewById(R.id.autoCompleteTextView);
        tvDest=findViewById(R.id.autoCompleteTextView2);

        cities=new ArrayList<>();
        cities.add("Gondia");
        cities.add("Bhandara");
        cities.add("Nagpur");
        cities.add("Wardha");
        cities.add("Amravati");
        cities.add("Yavatmal");
        cities.add("Chandrapur");
        cities.add("Akola");
        cities.add("Bhusawal");
        cities.add("Jalgaon");
        cities.add("Dhule");
        cities.add("Malegaon");
        cities.add("Aurangabad");
        cities.add("Jalna");
        cities.add("Parbhani");
        cities.add("Nanded");
        cities.add("Nashik");
        cities.add("Ahmednagar");
        cities.add("Beed");
        cities.add("Latur");
        cities.add("Mumbai");
        cities.add("Pune");
        cities.add("Solapur");
        cities.add("Satara");
        cities.add("Ratnagiri");
        cities.add("Kolhapur");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_actv,R.id.tvCustom,cities);
        tvSource.setAdapter(adapter);
        tvDest.setAdapter(adapter);
    }
    public void onDone(View view){
        Dijkstra dj=new Dijkstra();
        String source=tvSource.getText().toString();
        String destination=tvDest.getText().toString();
        if(!cities.contains(source) || !cities.contains(destination)){
//            Toast.makeText(this, "Please Enter the cities from suggestion only!", Toast.LENGTH_SHORT).show();
            tvToast.setText("Please choose the cities from suggestions only!");
            toast.setGravity(Gravity.CENTER,0,20);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Result output = dj.dijkstraShotestPathAlgo(source, destination);
            Intent intent = new Intent(MainActivity.this, LoadActivity.class);
            intent.putExtra("areaInfo", output.res);
            intent.putExtra("minCost", output.minCost);
            startActivity(intent);
        }
//            Intent in = new Intent(MainActivity.this, LoadActivity.class);
//            Bundle args=new Bundle();
//            args.putSerializable("arraylist",(Serializable)output.res);
//            in.putExtra("areainfo",args);
//            in.putExtra("cost",output.minCost);
////        try {
//            startActivity(in);
//        }catch (Exception ex){
//            Toast.makeText(this, "Please enter the source and destination correctly!!!", Toast.LENGTH_SHORT).show();
//        }
    }
}