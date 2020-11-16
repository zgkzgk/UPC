package com.zgk.viewpagerf.MyFragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hb.dialog.dialog.ConfirmDialog;
import com.hb.dialog.myDialog.ActionSheetDialog;
import com.zgk.viewpagerf.ItemView;
import com.zgk.viewpagerf.MyActivitys.ClassRoomActivity;
import com.zgk.viewpagerf.MyActivitys.EnquiryActivity;
import com.zgk.viewpagerf.MyActivitys.MainActivity;
import com.zgk.viewpagerf.MyActivitys.ScoreActivity;
import com.zgk.viewpagerf.MyActivitys.TestActivity;
import com.zgk.viewpagerf.MyApplication;
import com.zgk.viewpagerf.R;
import com.zgk.viewpagerf.vohttp.ClassRoomQuest;
import com.zgk.viewpagerf.vohttp.MyInfoQuest;
import com.zgk.viewpagerf.vohttp.TestQuest;

import static android.content.Context.MODE_PRIVATE;

public class ThreeFragment extends Fragment{
    private Button button,Test_button;
    private ItemView RealName_IV,XYMC_TV,MyInfo_IV,TYPE_IV;
    private String Token;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,  Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_three, null);
//        button=view.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(view.getContext(),CourseActivity.class);
//                startActivity(intent);
//            }
//        });
//        Test_button=view.findViewById(R.id.test_button);
//        Test_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(view.getContext(), TestActivity.class);
//                startActivity(intent);
//            }
//        });
        RealName_IV=view.findViewById(R.id.nickName);
        XYMC_TV=view.findViewById(R.id.xymc);
        MyInfo_IV=view.findViewById(R.id.pass);
        TYPE_IV=view.findViewById(R.id.signAge);
        button=view.findViewById(R.id.logout);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("sp",MODE_PRIVATE);
        String nickname=sharedPreferences.getString("RealName","");
        String XYMC=sharedPreferences.getString("XYMC","");
        final String type=sharedPreferences.getString("TYPE","学生");
        RealName_IV.setRightDesc(nickname);
        XYMC_TV.setRightDesc(XYMC);
        TYPE_IV.setRightDesc(type);
        final MyApplication myApplication=(MyApplication) getActivity().getApplication();
        Token=myApplication.getToken();
        MyInfo_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyInfoQuest testQuest=new MyInfoQuest();
                testQuest.getDataAsync(Token,view.getContext());
                if (type.equals("学生")){
                ActionSheetDialog dialog = new ActionSheetDialog(view.getContext()).builder().setTitle("请选择")
                        .addSheetItem("课表查询", null, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                               Intent intent=new Intent(view.getContext(), EnquiryActivity.class);
                               startActivity(intent);
//                                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("sp",MODE_PRIVATE);
//                                String username=sharedPreferences.getString("username","");
//                                String ZC=sharedPreferences.getString("ZC","");
//                                TestQuest testQuest=new TestQuest();
//                                testQuest.getDataAsync(Token,username,ZC,view.getContext());
                            }
                        }).addSheetItem("成绩查询", null, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent intent1=new Intent(view.getContext(), ScoreActivity.class);
                                startActivity(intent1);
                            }
                        }).addSheetItem("空闲教室", null, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent intent1=new Intent(view.getContext(), ClassRoomActivity.class);
                                startActivity(intent1);
                            }
                        });
                dialog.show();
            }
            else{
                    ActionSheetDialog dialog = new ActionSheetDialog(view.getContext()).builder().setTitle("请选择")
                            .addSheetItem("课表查询", null, new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    Intent intent=new Intent(view.getContext(), EnquiryActivity.class);
                                    startActivity(intent);
//                                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("sp",MODE_PRIVATE);
//                                String username=sharedPreferences.getString("username","");
//                                String ZC=sharedPreferences.getString("ZC","");
//                                TestQuest testQuest=new TestQuest();
//                                testQuest.getDataAsync(Token,username,ZC,view.getContext());
                                }
                            }).addSheetItem("空闲教室", null, new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    Intent intent1=new Intent(view.getContext(), ClassRoomActivity.class);
                                    startActivity(intent1);
                                }
                            });
                    dialog.show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog confirmDialog = new ConfirmDialog(getContext());
                confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("确定要退出吗？");
                confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
                    @Override
                    public void ok() {
                        Intent intent=new Intent(getContext(), TestActivity.class);
                        getActivity().finish();
                        startActivity(intent);
                    }

                    @Override
                    public void cancel() {

                    }
                });
                confirmDialog.show();
            }
        });
        return view;
    }

}