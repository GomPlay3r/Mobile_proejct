package com.example.project_;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class change_nn extends user_info {

    ImageButton back;
    EditText nn;
    Button cancel, save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_nn);

        back = findViewById(R.id.back_change_nn);
        nn = findViewById(R.id.edt_nn);
        cancel = findViewById(R.id.change_nn_cancel);
        save = findViewById(R.id.change_nn_save);

        //테스트용 임시변수
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
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NN = nn.getText().toString();
                if (nn.getText().toString().equals("")) {
                    Toast.makeText(change_nn.this, "성과 이름을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
//                } else if (!Pattern.matches("^[가-힣]*$", Name)) {
//                    Toast.makeText(change_name.this, "성과 이름은 한글로만 입력해주세요.", Toast.LENGTH_SHORT).show();
//                    //다이얼로그로 수정할 것임.
                    //닉네임은 유효성 검사 어떻게 할건지? 생각해보기.

                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String New_NN = jsonObject.getString("NN");
                                Log.d("닉네임 테스트", "바뀐 닉네임 -> " + New_NN);
                                //완료됐다는 다이얼로그 넣기.
                                //다이얼로그 뜨고 확인 누르면 다시 개인설정으로 돌아가게 ㄱㄱ
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    ChangeNNRequest changeNNRequest = new ChangeNNRequest(ID, NN, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(change_nn.this);
                    queue.add(changeNNRequest);
                }
            }
        });
    }
}
