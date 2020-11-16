package com.zgk.viewpagerf.MyActivitys;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.zgk.viewpagerf.MyApplication;
import com.zgk.viewpagerf.R;
import com.zgk.viewpagerf.vohttp.ClassRoomQuest;

public class ClassRoomActivity extends Activity {
    private Spinner JXL,SJD;
    private Button searchBT;
    private String jxl,sjd,Token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room);
        searchBT=findViewById(R.id.FreeClass_button);
        JXL=findViewById(R.id.JXL_Spinner);
        SJD=findViewById(R.id.SJD_Spinner);
        MyApplication myApplication=(MyApplication) this.getApplication();
        Token=myApplication.getToken();
        JXL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (JXL.getSelectedItem().toString()){
                    case "东廊":
                        jxl="00006";
                        break;
                    case "东环":
                        jxl="00008";
                        break;
                    case "南堂":
                        jxl="00013";
                        break;
                    case "西廊":
                        jxl="00007";
                        break;
                    case "南教":
                        jxl="00017";
                        break;
                    case "西环":
                        jxl="00009";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SJD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (SJD.getSelectedItem().toString()){
                    case "上午":
                        sjd="am";
                        break;
                    case "下午":
                        sjd="pm";
                        break;
                    case "晚上":
                        sjd="night";
                        break;
                    case "0102":
                        sjd="0102";
                        break;
                    case "0304":
                        sjd="0304";
                        break;
                    case "0506":
                        sjd="0506";
                        break;
                    case "0708":
                        sjd="0708";
                        break;
                    case "0910":
                        sjd="0910";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        searchBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassRoomQuest testQuest=new ClassRoomQuest();
                testQuest.getDataAsync(Token,"getKxJscx",sjd,jxl,"00002",getApplicationContext());
            }
        });
    }
}
