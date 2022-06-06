package com.example.project_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Find_ID_result extends Find_Id_cl {

    TextView tv_result;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_id_result);

        tv_result = findViewById(R.id.tv_result);
        login_btn = findViewById(R.id.login_btn);

        Intent intent = getIntent();
        String Find_ID = intent.getStringExtra("ID");

        tv_result.setText(Find_ID);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_login = new Intent(Find_ID_result.this,LoginPage_cl.class);
                startActivity(go_to_login);
                finish();
            }
        });

    }
}
