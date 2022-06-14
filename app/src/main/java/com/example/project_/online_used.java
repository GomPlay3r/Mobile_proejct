package com.example.project_;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
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
import org.w3c.dom.Text;

import java.util.ArrayList;

public class online_used extends Activity implements View.OnClickListener {

    ImageButton back;
    Button LG, SS, Dyson, Sha;
    ImageView[] used_image = new ImageView[5];
    TextView[] used_com = new TextView[5];
    TextView[] used_name = new TextView[5];
    TextView[] used_price = new TextView[5];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_used);

        for (int i = 0; i < 5; i++) {
            used_image[i] = findViewById(R.id.used_image1 + i);
        }
        for (int i = 0; i < 5; i++) {
            used_com[i] = findViewById(R.id.used_com1 + i);
        }
        for (int i = 0; i < 5; i++) {
            used_name[i] = findViewById(R.id.used_name1 + i);
        }
        for (int i = 0; i < 5; i++) {
            used_price[i] = findViewById(R.id.used_price1 + i);
        }
        back = findViewById(R.id.back_used);
        LG = findViewById(R.id.used_LG);
        SS = findViewById(R.id.used_SS);
        Dyson = findViewById(R.id.used_Dyson);
        Sha = findViewById(R.id.used_Sha);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LG.setOnClickListener(this);
        SS.setOnClickListener(this);
        Dyson.setOnClickListener(this);
        Sha.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.used_LG:
                LG.setTextColor(Color.WHITE);
                LG.setBackgroundResource(R.drawable.online_used_btn);
                String CP = "LG";
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.d("test2", "test2" + jsonArray);
                                String image = jsonObject.getString("Image");
                                String CP = jsonObject.getString("CP");
                                String Name = jsonObject.getString("Name");
                                String UP = jsonObject.getString("UP");

                                Log.d("Name0", "Name0" + Name);

                                Glide.with(online_used.this).load(image).into(used_image[i]);
                                used_com[i].setText(CP);
                                used_name[i].setText(Name);
                                used_price[i].setText(UP);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                UsedLGRequest usedLGRequest = new UsedLGRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(usedLGRequest);
                break;

            case R.id.used_SS:
                SS.setTextColor(Color.WHITE);
                SS.setBackgroundResource(R.drawable.online_used_btn);
                LG.setEnabled(false);
                break;
            case R.id.used_Dyson:
                Dyson.setTextColor(Color.WHITE);
                Dyson.setBackgroundResource(R.drawable.online_used_btn);
                break;
            case R.id.used_Sha:
                Sha.setTextColor(Color.WHITE);
                Sha.setBackgroundResource(R.drawable.online_used_btn);
                break;
            default:
                break;
        }
    }
}
