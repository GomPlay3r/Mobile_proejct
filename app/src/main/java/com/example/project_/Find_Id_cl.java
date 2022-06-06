package com.example.project_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Find_Id_cl extends LoginPage_cl {
    ImageButton back_button; //뒤로가기 버튼임
    EditText name, email; //이름, 이메일 적는 칸임
    Button Complete; //아이디 찾기 버튼임.
    TextView si_bal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_id);
        back_button = findViewById(R.id.back_button2);
        name = findViewById(R.id.find_name1);
        email = findViewById(R.id.find_email);
        Complete = findViewById(R.id.find_button1);

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Email = email.getText().toString();
                if (Name.equals("") || Email.equals("")) {
                    Toast.makeText(getApplicationContext(), "이름과 이메일을 적어주세요!", Toast.LENGTH_SHORT).show();
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                String findID = jsonObject.getString("ID");
                                Intent intent = new Intent(Find_Id_cl.this,Find_ID_result.class);
                                intent.putExtra("ID",findID);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Find_Id_cl.this, "정보에 맞는 아이디가 없는데?", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                FindIDRequest findIDRequest = new FindIDRequest(Name, Email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Find_Id_cl.this);
                queue.add(findIDRequest);
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
