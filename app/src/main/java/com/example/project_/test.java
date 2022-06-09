package com.example.project_;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

public class test extends Activity {

    //제품 정보 불러오는 테스트 클래스임
    Button btn;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    ImageView img;
    EditText edt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_);

        btn = findViewById(R.id.btn_test);
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);
        tv5 = findViewById(R.id.tv_5);
        tv6 = findViewById(R.id.tv_6);
        tv7 = findViewById(R.id.tv_7);
        img = findViewById(R.id.img_1);
        edt = findViewById(R.id.edt1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PN = edt.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("test","test : " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                String PN = jsonObject.optString("PN","no value");
                                String CP = jsonObject.getString("CP");
                                String Type = jsonObject.getString("Type");
                                String NP = jsonObject.getString("NP");
                                String UP = jsonObject.getString("UP");
                                String YOM = jsonObject.getString("YOM");
                                String Name = jsonObject.getString("Name");
                                String image = jsonObject.getString("Image");
                            Log.d("test", "test : " + PN);
                            if (success) {
                                tv1.setText(PN);
                                tv2.setText(CP);
                                tv3.setText(Type);
                                tv4.setText(NP);
                                tv5.setText(UP);
                                tv6.setText(YOM);
                                tv7.setText(Name);
                                Glide.with(test.this).load(image).into(img);
                            } else {
                                Toast.makeText(test.this, "실패", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                testRequest testrequest = new testRequest(PN, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(testrequest);
            }
        });
    }
}
