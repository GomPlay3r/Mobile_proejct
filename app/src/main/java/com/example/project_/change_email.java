package com.example.project_;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class change_email extends user_info {

    ImageButton back;
    EditText email;
    Button cancel, save;
    Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_email);

        back = findViewById(R.id.back_change_email);
        email = findViewById(R.id.edt_email);
        cancel = findViewById(R.id.change_email_cancel);
        save = findViewById(R.id.change_email_save);

        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        dialog = new Dialog(change_email.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.change_email_success);

        Intent get_ID = getIntent();
        String ID = get_ID.getStringExtra("ID");

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
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                if (email.equals("")) {
                    Toast.makeText(change_email.this, "성과 이름을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!emailPattern.matcher(Email).matches()) {
                    //다이얼로그로 수정할 것임.
                    Toast.makeText(getApplicationContext(), "이메일 형식을 다시 한번 확인해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String New_Email = jsonObject.getString("Email");
                                Log.d("이메일 변경 테스트", "바뀐 이메일 -> " + New_Email);
                                //완료됐다는 다이얼로그 넣기.
                                //다이얼로그 뜨고 확인 누르면 다시 개인설정으로 돌아가게 ㄱㄱ
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    ChangeEmailRequest changeEmailRequest = new ChangeEmailRequest(ID, Email, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(change_email.this);
                    queue.add(changeEmailRequest);
                    showDialog01();
                }
            }
        });
    }

    public void showDialog01() {
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 900;
        params.height = 650;
        dialog.getWindow().setAttributes(params);
        dialog.show();
        dialog.findViewById(R.id.dialog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_again = new Intent(change_email.this, LoginPage_cl.class);
                startActivity(login_again);
                dialog.dismiss();
            }
        });
    }
}
