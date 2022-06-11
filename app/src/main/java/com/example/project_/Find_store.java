package com.example.project_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class Find_store extends Activity {

    Spinner spinner;
    ImageButton back;
    EditText find_store;
    Button find_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_store);

        spinner = findViewById(R.id.spinner);
        back = findViewById(R.id.back_find_store);
        find_store = findViewById(R.id.find_store_edt);
        find_btn = findViewById(R.id.find_btn);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", "어디가 선택됐나 : " + parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //찾은 매장 이름을 맵 API가 자동으로 검색되게
                //Intent intent = new Intent(Find_store.this,)
            }
        });
    }


}
