package com.example.project_;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

public class Signup_cl2 extends Signup_cl {
    ImageButton back; // 뒤로가기 버튼
    private EditText name, nn; //이름, 닉네임
    Button Complete; //회원가입 완료 버튼
    TextView overlap, tv_nn; //중복검사
    private boolean validate = false;
//중복 체크 안하면 안넘어가게 해야됨 ㅇㅇ 그거 추가해야됨
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page2);
        back = findViewById(R.id.back_button4);
        name = findViewById(R.id.nameEdit);
        nn = findViewById(R.id.nnEdit);
        Complete = findViewById(R.id.sign_button2);
        overlap = findViewById(R.id.nn_overlap); // 닉네임 중복 유효성 검사 하기. Ex) 이미 등록된 닉네임 입니다. 이런거. 아니면 한글 모음만 쓰는거 안된다거나
        tv_nn = findViewById(R.id.tv_nn);

        Intent intent_info = getIntent();
        String ID = intent_info.getStringExtra("ID");
        String Pass = intent_info.getStringExtra("Pass");
        String Email = intent_info.getStringExtra("Email");

        overlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NN = nn.getText().toString();
                if (validate) {
                    return;
                }
                Response.Listener responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                if (nn.getText().toString().equals("")) {
                                    tv_nn.setText("닉네임을 입력해주세요.");
                                } else {
                                    tv_nn.setText("사용가능한 닉네임 입니다.");
                                    tv_nn.setTextColor(Color.parseColor("#002bff"));
                                    nn.setEnabled(false);
                                    validate = true;
                                }
                            } else {
                                tv_nn.setText("중복된 닉네임 입니다.");
                                tv_nn.setTextColor(Color.parseColor("#ff0000"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest_nn validateRequest_nn = new ValidateRequest_nn(NN, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Signup_cl2.this);
                queue.add(validateRequest_nn);
            }
        });


        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(!validate) {
                   Toast.makeText(getApplicationContext(),"닉네임 중복 체크를 해주세영!",Toast.LENGTH_SHORT).show();
               }
                String Name = name.getText().toString();
                String NN = nn.getText().toString();
                Response.Listener responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent go_login = new Intent(Signup_cl2.this, LoginPage_cl.class);
                                startActivity(go_login);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(ID, Pass, Email, Name, NN, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Signup_cl2.this);
                queue.add(registerRequest);
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
