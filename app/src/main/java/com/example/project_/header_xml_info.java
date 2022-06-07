package com.example.project_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class header_xml_info extends main_menu {

    TextView tv_main_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigation_header);

        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");
        Log.d("확인","아이디 : "+ID);

        tv_main_id = findViewById(R.id.tv_main_id);
        tv_main_id.setText(ID);

    }
}
