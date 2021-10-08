package com.example.waze;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ToastCustom extends AppCompatActivity {
    String text;
    Context ctx;
    int duration;
    Activity activity;
    TextView tv;
    ImageView imageView;

    public ToastCustom(String text, Context ctx, int duration, Activity activity){
        this.text=text;
        this.ctx=ctx;
        this.duration=duration;
        this.activity=activity;
    }
    public void show(){
        Toast toast=new Toast(ctx);
        toast.makeText(ctx,text,duration);
        LayoutInflater li=LayoutInflater.from(ctx);
        View view=li.inflate(R.layout.custom_toast,this.activity.findViewById(R.id.iv_custom_toast));
        tv=view.findViewById(R.id.tv_toast_msg);
        tv.setText(text);
        toast.setView(view);
        toast.show();
    }
}
