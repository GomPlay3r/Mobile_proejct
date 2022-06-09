package com.example.project_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.regex.Pattern;


public class change_pass extends user_info {

    ImageButton back;
    Button cancel, save;
    EditText old_pass, new_pass1, new_pass2;
    TextView check1, check2, check3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pass);

        back = findViewById(R.id.back_change_pass);
        cancel = findViewById(R.id.change_pass_cancel);
        save = findViewById(R.id.change_pass_save);
        old_pass = findViewById(R.id.edt_old_pass);
        new_pass1 = findViewById(R.id.edt_new_pass1);
        new_pass2 = findViewById(R.id.edt_new_pass2);
        check1 = findViewById(R.id.old_pass_check);
        check2 = findViewById(R.id.new_pass_check);
        check3 = findViewById(R.id.new_pass_check2);
        //테스트용 임시 변수
        String test = "Pet33044@";
        String ID = "test";

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
        // 유효성 검사 많이 많이 수정해야함
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //기존 비밀번호 Intent로 가져와야 비교 가능함.
                if (old_pass.getText().toString().equals("") || new_pass1.getText().toString().equals("") || new_pass2.getText().toString().equals("")) {
                    Toast.makeText(change_pass.this, "비밀번호를 모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
                if (!old_pass.getText().toString().equals(test)) {
                    check1.setVisibility(View.VISIBLE);
                    //테두리 빨간색으로 바뀌게 하기.
                } else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,}.$", new_pass1.getText().toString())) {
                    check2.setVisibility(View.VISIBLE);
                    check1.setVisibility(View.INVISIBLE);
                    //테투리 빨간색으로 바뀌게 하기.
                } else if (!new_pass1.getText().toString().equals(new_pass2.getText().toString())) {
                    check2.setVisibility(View.VISIBLE);
                    //얘도 빨간색으로 테두리 바뀌게 하기.
                } else {
                    String Pass = new_pass2.getText().toString();
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                //비밀번호 변경 됐다는 다이얼로그 뜨게 하기
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    ChangePassRequest changePassRequest = new ChangePassRequest(ID, Pass, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(change_pass.this);
                    queue.add(changePassRequest);
                }

            }
        });

    }
}
