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

public class pass_reset_cl extends Find_pass_cl{
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
                if(pass1.getText().toString().equals("")) {
                    tv_reset1.setText("tv_reset1.setText(비밀번호는 숫자+영문자+특수문자(~!@#$%^&amp;*-+=)를 조합하여\n반드시 8자리 이상으로 설정해 주시기를 바랍니다.\n");
                    tv_reset1.setTextColor(Color.parseColor("#ff0000"));
                }
                else if (pass1.equals(pass2.getText().toString())) {
                    tv_reset2.setText("비밀번호가 일치하지 않습니다.");
                    tv_reset2.setTextColor(Color.parseColor("#ff0000"));
                }
                // else if 유효성 검사를 하면 됨 유효성 검사 전에 EditText에 비밀번호가 먼저 적혀있는지 확인하기 위해서 먼저 위에 써놨음.
                // 유효성 검사까지 끝나고 비밀번호 재설정을 누르면 재설정한 비밀번호가 DB에 들어가고 다시 로그인 화면으로 돌아감.
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
