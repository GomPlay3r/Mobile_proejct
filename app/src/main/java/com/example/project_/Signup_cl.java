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

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Signup_cl extends MainActivity {
    ImageButton back_button; //뒤로가기 버튼
    Button sign_button1; //회원가입 다음 버튼
    private EditText id, password, password2, email; //아이디, 비밀번호, 비밀번호 재입력, 이메일
    TextView overlap, overlap_text; //중복검사
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);
        sign_button1 = findViewById(R.id.sign_button1);
        back_button = findViewById(R.id.back_button3);
        id = findViewById(R.id.idEdit);
        password = findViewById(R.id.passEdit);
        password2 = findViewById(R.id.passEditAgain);
        email = findViewById(R.id.emailEdit);
        overlap = findViewById(R.id.id_overlap); //아이디 중복 검사 유효성 검사 이용하면 될 듯 Ex) 이미 등록된 아이디 입니다, 한글로 아이디 못만들게
        overlap_text = findViewById(R.id.tv_id_overlap);


        overlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = id.getText().toString();
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
                                if (id.getText().toString().equals("")) {
                                    overlap_text.setText("아이디를 입력해주세요");
                                } else {
                                    overlap_text.setTextColor(Color.parseColor("#002bff"));
                                    overlap_text.setText("사용가능 한 아이디 입니다.");
                                    id.setEnabled(false);
                                    validate = true;
                                    id.setBackgroundResource(R.drawable.overlap_success);
                                }
                            } else {
                                Log.d("edit", id.getText().toString());
                                overlap_text.setTextColor(Color.parseColor("#ff0000"));
                                overlap_text.setText("중복된 아이디 입니다.");
                                id.setBackgroundResource(R.drawable.overlap_fail);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(ID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Signup_cl.this);
                queue.add(validateRequest);
            }
        });


        sign_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate)
                    Toast.makeText(getApplicationContext(), "아이디 중복 체크를 해주세요!", Toast.LENGTH_SHORT).show();
                String ID = id.getText().toString();
                String Pass = password.getText().toString();
                String Email = email.getText().toString();

                if (ID.equals("") || Pass.equals("") || Email.equals("")) {
                    Toast.makeText(getApplicationContext(), "모든 정보가 입력되었는지 다시 한번 확인해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Signup_cl.this, Signup_cl2.class);
                    intent.putExtra("ID", ID);
                    intent.putExtra("Pass", Pass);
                    intent.putExtra("Email", Email);
                    startActivity(intent);
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
