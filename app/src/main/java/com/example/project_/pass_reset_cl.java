package com.example.project_;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class pass_reset_cl extends Find_pass_cl {
    ImageButton back; //뒤로가기 버튼
    EditText pass1, pass2; //비밀번호 입력, 비밀번호 재입력
    Button Reset; //비밀번호 재설정 버튼
    TextView tv_reset1, tv_reset2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_reset);

        back = findViewById(R.id.back_button6);
        pass1 = findViewById(R.id.pass_reset1);
        pass2 = findViewById(R.id.pass_reset2);
        Reset = findViewById(R.id.reset_button);
        tv_reset1 = findViewById(R.id.tv_reset1);
        tv_reset2 = findViewById(R.id.tv_reset2);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pass1 = pass1.getText().toString();
                String Pass2 = pass2.getText().toString();
                if(Pass1.equals("") || Pass2.equals("")) {
                    Toast.makeText(pass_reset_cl.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,}.$",Pass1)) {
                    tv_reset1.setVisibility(View.VISIBLE);
                }
                else if (!Pass1.equals(Pass2)) {
                    tv_reset2.setVisibility(View.VISIBLE);
                }
                else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Intent go_to_check = new Intent(pass_reset_cl.this,pass_reset_cl.class);
                                startActivity(go_to_check);
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    //ID Intent로 줘야함. 메인에서.
                    Reset_Pass_Request reset_pass_request = new Reset_Pass_Request(ID,Pass1,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(pass_reset_cl.this);
                    queue.add(reset_pass_request);
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
