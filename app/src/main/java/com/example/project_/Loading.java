package com.example.project_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class Loading extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Loading.this, MainActivity.class);
                startActivity(intent);
            }
        }, 4000);
    }
    //4초 뒤에 로딩화면에서 우리 앱의 가장 첫 번째 메인화면인, 로그인 회원가입 선택창으로 넘어감
}
