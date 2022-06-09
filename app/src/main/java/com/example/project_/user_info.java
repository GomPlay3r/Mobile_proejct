package com.example.project_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class user_info extends Activity {

    Button change_name, change_nn, change_email, change_pass;
    ImageView profile_img;
    ImageButton back;
    TextView Name, NN_ID, NN, Email;


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
        Name = findViewById(R.id.user_info_name);
        NN_ID = findViewById(R.id.user_info_nn_id);
        NN = findViewById(R.id.user_info_nn);
        Email = findViewById(R.id.user_info_email);

        //여기에 로그인해서 메인에 모든 정보 보내고 그 모든 정보를 여기에 Intent로 줘야함
        //이름, 아이디, 닉네임, 이메일만 주면됨.

        //임시 변수
        String test = "test";

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
                //intent.putExtra("test",test);
                //원래는 아이디 메인에서 받은거 줘야함
                startActivity(intent);
            }
        });
        change_nn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_info.this, com.example.project_.change_nn.class);
                //itent.putExtra("test",test);
                //원래는 아이디 메인에서 받은거 줘야함
                startActivity(intent);
            }
        });
        change_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_info.this, com.example.project_.change_email.class);
                //위와 같음
                startActivity(intent);
            }
        });
        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_info.this, com.example.project_.change_pass.class);
                //위와 같음
                startActivity(intent);
            }
        });

    }
}
