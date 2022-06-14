package com.example.project_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class user_info extends Activity {

    Button change_name, change_nn, change_email, change_pass;
    ImageView profile_img;
    ImageButton back, f5;
    TextView Name_tv, NN_ID, NN_tv, Email_tv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        change_name = findViewById(R.id.change_name);
        change_nn = findViewById(R.id.change_nn);
        change_email = findViewById(R.id.change_email);
        change_pass = findViewById(R.id.change_pass);
        profile_img = findViewById(R.id.profile_img);
        back = findViewById(R.id.back_user_info);
        Name_tv = findViewById(R.id.user_info_name);
        NN_ID = findViewById(R.id.user_info_nn_id);
        NN_tv = findViewById(R.id.user_info_nn);
        Email_tv = findViewById(R.id.user_info_email);
        f5 = findViewById(R.id.f5);

        //여기에 로그인해서 메인에 모든 정보 보내고 그 모든 정보를 여기에 Intent로 줘야함
        //이름, 아이디, 닉네임, 이메일만 주면됨.

        Intent get_info = getIntent();
        String Name = get_info.getStringExtra("Name");
        String ID = get_info.getStringExtra("ID");
        String NN = get_info.getStringExtra("NN");
        String Email = get_info.getStringExtra("Email");
        String Pass = get_info.getStringExtra("Pass");


        Name_tv.setText(Name);
        NN_ID.setText(NN + "#" + ID);
        NN_tv.setText(NN);
        Email_tv.setText(Email);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_info.this, com.example.project_.change_name.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });

        change_nn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_info.this, com.example.project_.change_nn.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });

        change_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_info.this, com.example.project_.change_email.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_info.this, com.example.project_.change_pass.class);
                intent.putExtra("ID", ID);
                intent.putExtra("Pass", Pass);
                startActivity(intent);
            }
        });
    }
}
