package com.example.project_;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class change_nn extends user_info {

    ImageButton back;
    EditText nn;
    Button cancel, save;
    Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_nn);

        back = findViewById(R.id.back_change_nn);
        nn = findViewById(R.id.edt_nn);
        cancel = findViewById(R.id.change_nn_cancel);
        save = findViewById(R.id.change_nn_save);

        dialog = new Dialog(change_nn.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.change_nn_success);

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
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    ChangeNNRequest changeNNRequest = new ChangeNNRequest(ID, NN, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(change_nn.this);
                    queue.add(changeNNRequest);
                    showDialog01();
                }
            }
        });
    }

    public void showDialog01() {
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 800;
        params.height = 650;
        dialog.getWindow().setAttributes(params);
        dialog.show();
        dialog.findViewById(R.id.dialog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_again = new Intent(com.example.project_.change_nn.this, LoginPage_cl.class);
                startActivity(login_again);
                dialog.dismiss();
            }
        });
    }
}
