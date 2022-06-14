package com.example.project_;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class change_name extends user_info {

    ImageButton back_btn;
    Button cancel, save;
    EditText name1, name2;
    Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_name);

        back_btn = findViewById(R.id.back_change_name);
        cancel = findViewById(R.id.name_btn_cancel);
        save = findViewById(R.id.name_btn_save);
        name1 = findViewById(R.id.edt_name1);
        name2 = findViewById(R.id.edt_name2);

        dialog = new Dialog(com.example.project_.change_name.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.change_name_success);

        Intent get_ID = getIntent();
        String ID = get_ID.getStringExtra("ID");

        back_btn.setOnClickListener(new View.OnClickListener() {
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
        //ID를 받아와서 그 ID에 맞는 이름을 수정함
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name1.getText().toString() + name2.getText().toString();
                Log.d("이름 테스트", "이름은 : " + Name);
                if (name1.getText().toString().equals("") || name2.getText().toString().equals("")) {
                    Toast.makeText(change_name.this, "성과 이름을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!Pattern.matches("^[가-힣]*$", Name)) {
                    Toast.makeText(change_name.this, "성과 이름은 한글로만 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String New_Name = jsonObject.getString("Name");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    ChangeNameRequest changeNameRequest = new ChangeNameRequest(ID, Name, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(change_name.this);
                    queue.add(changeNameRequest);
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
                Intent login_again = new Intent(change_name.this, LoginPage_cl.class);
                startActivity(login_again);
                dialog.dismiss();
            }
        });
    }
}
