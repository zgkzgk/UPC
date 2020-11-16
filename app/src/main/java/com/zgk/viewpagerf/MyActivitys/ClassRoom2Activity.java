package com.zgk.viewpagerf.MyActivitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zgk.viewpagerf.R;

public class ClassRoom2Activity extends Activity {
    private TextView jsh,jxl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room2);
        jxl=findViewById(R.id.jxl_TV);
        jsh=findViewById(R.id.classroom_TV);
        Intent intent=getIntent();
        jxl.setText(intent.getStringExtra("jxl"));
        jsh.setText(intent.getStringExtra("jsh"));
    }
}
