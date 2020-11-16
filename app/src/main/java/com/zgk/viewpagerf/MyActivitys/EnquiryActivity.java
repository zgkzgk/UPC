package com.zgk.viewpagerf.MyActivitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.zgk.viewpagerf.MyAdapters.MyAdapter.CourseAdapter;
import com.zgk.viewpagerf.MyApplication;
import com.zgk.viewpagerf.R;
import com.zgk.viewpagerf.vohttp.ClassRoomQuest;
import com.zgk.viewpagerf.vohttp.TestQuest;

import static android.content.Context.MODE_PRIVATE;

public class EnquiryActivity extends Activity {
public String Token,username,ZC;
private Button Course_BT;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        SharedPreferences sharedPreferences=getSharedPreferences("sp",MODE_PRIVATE);
        spinner=findViewById(R.id.course_medel);
        ZC=sharedPreferences.getString("ZC","");
        spinner.setSelection(Integer.parseInt(ZC)-1);	//根据该选项的位置设置该选项为spinner默认值
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ZC=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        username=sharedPreferences.getString("username","");
        Course_BT=findViewById(R.id.course_button);
        MyApplication myApplication=(MyApplication) this.getApplication();
        Token=myApplication.getToken();
        Course_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestQuest testQuest=new TestQuest();
                testQuest.getDataAsync(Token,username,ZC,getApplicationContext());
            }
        });
    }
}
