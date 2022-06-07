package com.example.project_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Find_pass_cl extends LoginPage_cl {
    EditText name, id; //이름, 아이디 적는 칸
    ImageView back; //뒤로가기 버튼
    Button Complete; //비밀번호 찾기 버튼
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_pass);

        name = findViewById(R.id.find_name2);
        id = findViewById(R.id.find_id);
        back = findViewById(R.id.back_button5);
        Complete = findViewById(R.id.find_button2);
        tv_result = findViewById(R.id.tv_check2);

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String ID = id.getText().toString();
                if (Name.equals("") || ID.equals("")) {
                    Toast.makeText(getApplicationContext(), "이름과 아이디를 적어주세요!", Toast.LENGTH_SHORT).show();
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                String findPass = jsonObject.getString("Pass");
                                //String findEmail = jsonObject.getString("Email");
                                //비번을 가입된 정보에 있는 이메일을 이용하여 보내는거임
//                                Intent email = new Intent(Intent.ACTION_SEND);
//                                email.setType("plain/text");
//                                String[] address = {"quf2009@naver.com"};
//                                email.putExtra(Intent.EXTRA_EMAIL, address);
//                                email.putExtra(Intent.EXTRA_SUBJECT, "테스트로 보내는 이메일~!");
//                                email.putExtra(Intent.EXTRA_TEXT, "회원님의 비밀번호는 : " + findPass + " 입니다.");
//                                startActivity(email);
                            } else {
                                tv_result.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                FindPassRequest findPassRequest = new FindPassRequest(Name, ID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Find_pass_cl.this);
                queue.add(findPassRequest);
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