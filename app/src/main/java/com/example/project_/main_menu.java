package com.example.project_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import android.app.Activity;
import android.widget.TextView;

public class main_menu extends LoginPage_cl {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start에 지정된 Drawer 열기
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        NavigationView navigationView = findViewById(R.id.navigationView);
        View HeaderView = navigationView.getHeaderView(0);
        TextView tv_user_name = (TextView) HeaderView.findViewById(R.id.tv_main_name);
        tv_user_name.setText(Name+"님!");
        navigationView.setItemIconTintList(null);
    }
}
