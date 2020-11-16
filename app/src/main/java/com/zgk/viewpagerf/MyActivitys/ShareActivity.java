package com.zgk.viewpagerf.MyActivitys;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hb.dialog.dialog.ConfirmDialog;
import com.hb.dialog.dialog.LoadingDialog;
import com.zgk.viewpagerf.R;
import com.zgk.viewpagerf.image.ImageUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShareActivity extends Activity {
    private static final int REQUEST_CHOOSE_IMAGE = 0x01;

    private static final int REQUEST_WRITE_EXTERNAL_PERMISSION_GRANT = 0xff;
    private LoadingDialog loadingDialog;
    private String path;
    private EditText file_ET,content_ET;
    private String url= "http://a30492951j.qicp.vip";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        findViewById(R.id.choosefile_BT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareToOpenAlbum();
            }
        });
        findViewById(R.id.upfileBT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (path==null){
                    ConfirmDialog confirmDialog = new ConfirmDialog(ShareActivity.this);
                    confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("请选择所要上传的文件！");
                    confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
                        @Override
                        public void ok() {

                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    confirmDialog.show();
                }

                else{
                upImage(path);
                 loadingDialog= new LoadingDialog(ShareActivity.this);
                 loadingDialog.setMessage("正在上传中");
                 loadingDialog.show();}
            }
        });
        file_ET=findViewById(R.id.file_ET);
        content_ET=findViewById(R.id.content_ET);
    }

    private void prepareToOpenAlbum() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_PERMISSION_GRANT);
        } else {
            openAlbum();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, REQUEST_CHOOSE_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_WRITE_EXTERNAL_PERMISSION_GRANT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                Toast.makeText(this, "You denied the write_external_storage permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            Uri uri =  data.getData();
            Log.d("Tianma", "Uri = " + uri);
            path = ImageUtils.getRealPathFromUri(this, uri);
            File file=new File(path);
            file_ET.setText(file.getName());
            Log.d("Tianma", "realPath = " + path);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    class NetworkTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return doPost(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if(!"error".equals(result)) {
                loadingDialog.dismiss();
                Toast.makeText(ShareActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                finish();
                Log.i("TAG", "图片地址 " + url+ result);
            }
        }
    }
    private String doPost(String imagePath) {
        OkHttpClient mOkHttpClient = new OkHttpClient();

        String result = "error";
        String FileName="",FileTitle="";
        try {
            FileName=URLEncoder.encode(file_ET.getText().toString(),"UTF-8");
            //FileName=URLEncoder.encode(FileName,"UTF-8");
            FileTitle=URLEncoder.encode(content_ET.getText().toString(),"UTF-8");
           // FileTitle=URLEncoder.encode(FileTitle,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.ALTERNATIVE);
        builder.addFormDataPart("image", imagePath,
                RequestBody.create(null, new File(imagePath)));
                builder.addFormDataPart("FileName",FileName)
        .addFormDataPart("FileTitle",FileTitle);
        RequestBody requestBody = builder.build();
        Request.Builder reqBuilder = new Request.Builder();
        Request request = reqBuilder
                .url(url+"/WebApplication1/FileServlet")
                .post(requestBody)
                .build();
        Log.d("TAG", "请求地址 " + url);
        try{
            Response response = mOkHttpClient.newCall(request).execute();
            Log.d("TAG", "响应码 " + response.code());
            if (response.isSuccessful()) {
                String resultValue = response.body().string();
                Log.d("TAG", "响应体 " + resultValue);
                return resultValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private void upImage(String imagePath) {
        new NetworkTask().execute(imagePath);
    }
}
