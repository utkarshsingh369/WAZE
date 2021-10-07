package com.example.waze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        VideoView videoview = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.waze_splash);
        videoview.setVideoURI(uri);
        videoview.start();

////        mediaPlayer.start();
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                mediaPlayer.start();
//            }
//        },600);

        Intent intent=new Intent(this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
//                mediaPlayer.stop();
                finish();
            }
        },6000);
    }
}