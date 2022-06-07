package com.example.project_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage_cl extends MainActivity {
    ImageButton back_button; //뒤로가기 버튼
    TextView search_Id, sign_Up, search_pass; //아이디 찾기, 회원가입, 비밀번호 찾기
    EditText id, pass; //아이디, 비밀번호 적는 칸
    Button login; //로그인 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        search_Id = findViewById(R.id.search_Id);
        sign_Up = findViewById(R.id.sign_Up);
        back_button = findViewById(R.id.back_button1);
        id = findViewById(R.id.loginId);
        pass = findViewById(R.id.loginPass);
        search_pass = findViewById(R.id.search_pass);
        login = findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ID = id.getText().toString();
                final String Pass = pass.getText().toString();

              Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success) {
                                Toast.makeText(LoginPage_cl.this,"로그인 성공",Toast.LENGTH_SHORT).show();
                                Intent go_main = new Intent(LoginPage_cl.this,home.class);
                                go_main.putExtra("ID",ID);
                                startActivity(go_main);
                                finish();
                            } else {
                                Toast.makeText(LoginPage_cl.this,"로그인 실패",Toast.LENGTH_SHORT).show();

                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(ID, Pass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginPage_cl.this);
                queue.add(loginRequest);
            }
        });
        search_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent find_Id = new Intent(LoginPage_cl.this, Find_Id_cl.class);
                startActivity(find_Id);
            }
            //아이디 찾기 텍스트를 누를 경우, 아이디 찾기 화면으로 넘어감
        });
        search_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent find_pass = new Intent(LoginPage_cl.this, Find_pass_cl.class);
                startActivity(find_pass);
            }
            //비밀번호 찾기 텍스트를 누를 경우, 비밀번호 찾기 화면으로 넘어감
        });
        sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SIGN_UP = new Intent(LoginPage_cl.this, Signup_cl.class);
                startActivity(SIGN_UP);
            }
            //회원가입 텍스트를 누를 경우, 회원가입 화면으로 넘어감
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
