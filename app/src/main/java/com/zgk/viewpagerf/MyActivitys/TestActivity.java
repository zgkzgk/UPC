package com.zgk.viewpagerf.MyActivitys;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zgk.viewpagerf.R;
import com.zgk.viewpagerf.vohttp.LoginTestQuest;

public class TestActivity extends Activity {
private EditText userName,Password;
private Button LoginBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TestActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0xff);
        }
        setContentView(R.layout.activity_login);
        userName=findViewById(R.id.username_view);
        Password=findViewById(R.id.password_view);
        SharedPreferences sharedPreferences=getSharedPreferences("sp",MODE_PRIVATE);
        userName.setText(sharedPreferences.getString("username",""));
        Password.setText(sharedPreferences.getString("password",""));
        LoginBT=findViewById(R.id.login_btn);
        LoginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(TestActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0xff);
                }else{
                LoginTestQuest loginTestQuest=new LoginTestQuest();
                loginTestQuest.LoginRequest(userName.getText().toString(),Password.getText().toString(),getApplicationContext(),TestActivity.this);
            }
            }
        });
    }
}
