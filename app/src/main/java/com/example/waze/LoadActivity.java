package com.example.waze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import java.io.Serializable;
import java.util.ArrayList;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        getSupportActionBar().hide();
//        Intent inGet=getIntent();
//        Bundle args=inGet.getBundleExtra("areainfo");
        Intent inGet=getIntent();
        ArrayList<areaInfo> path=(ArrayList<areaInfo>) inGet.getSerializableExtra("areaInfo");
        int minCost=inGet.getIntExtra("minCost",-1);
//        ArrayList<String> path=inGet.getStringArrayListExtra("path");
        VideoView videoview = (VideoView) findViewById(R.id.videoView2);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.waze_loading);
        videoview.setVideoURI(uri);
        videoview.start();

//        Intent intent=new Intent(this, FinalActivity.class);
//        Bundle args2=new Bundle();
//        args2.putSerializable("arraylist",(Serializable)path);
//        intent.putExtra("areainfo",args2);
//        intent.putExtra("cost",cost);
        Intent intent = new Intent(LoadActivity.this, FinalActivity.class);
        intent.putExtra("areaInfo",path);
        intent.putExtra("minCost",minCost);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(intent);
//                mediaPlayer.stop();
                finish();
            }
        },3000);
    }
}