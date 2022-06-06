package com.example.project_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class home extends LoginPage_cl {

    Button btn1;
    EditText edt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_);

        btn1 = findViewById(R.id.btn_reset);
        edt1 = findViewById(R.id.edt1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String ID = intent.getStringExtra("ID");
                String Pass = edt1.getText().toString();

                if (Pass.isEmpty()) {
                    Toast.makeText(home.this, "비었음", Toast.LENGTH_SHORT).show();
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                // pass = jsonObject.getString("Pass");
                                if (success) {
                                    Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Reset_Pass_Request reset_pass_request = new Reset_Pass_Request(ID, Pass, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(home.this);
                    queue.add(reset_pass_request);
                }
            }
        });


    }
}
