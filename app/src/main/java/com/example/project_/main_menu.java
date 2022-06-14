package com.example.project_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class main_menu extends LoginPage_cl {
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 2;
    private CircleIndicator3 mIndicator;
    private RecyclerView mRecyclerView;
    private ArrayList<RecycleViewItem> mList;
    private RecycleViewAdapter mRecyclerViewAdapter;

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
        String ID = intent.getStringExtra("ID");
        String NN = intent.getStringExtra("NN");
        String Email = intent.getStringExtra("Email");
        String Pass = intent.getStringExtra("Pass");

        NavigationView navigationView = findViewById(R.id.navigationView);
        View HeaderView = navigationView.getHeaderView(0);
        TextView tv_user_name = (TextView) HeaderView.findViewById(R.id.tv_main_name);
        tv_user_name.setText(Name + "님!");
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.brand:
                        Toast.makeText(main_menu.this, "브랜드", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.product:
                        Toast.makeText(main_menu.this, "제품별", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.online:
                        Intent go_online = new Intent(main_menu.this, online_used.class);
                        startActivity(go_online);
                        break;
                    case R.id.store:
                        Toast.makeText(main_menu.this, "매장", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.information:
                        Intent go_info = new Intent(main_menu.this, user_info.class);
                        go_info.putExtra("Name", Name);
                        go_info.putExtra("ID", ID);
                        go_info.putExtra("NN", NN);
                        go_info.putExtra("Email", Email);
                        go_info.putExtra("Pass", Pass);
                        startActivity(go_info);
                        break;
                }
                return true;
            }
        });


        //ViewPager2
        mPager = findViewById(R.id.viewpager);
        //Adapter
        pagerAdapter = new MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
        //Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page, 0);
        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        // 거의 무한대로 스와이핑 하도록 시작

        mPager.setCurrentItem(1000); //시작 지점
        mPager.setOffscreenPageLimit(4); //최대 이미지 수

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position % num_page);
            }
        });

        firstInit();
        String[] imageName = {"LG 울트라 HD TV (스탠드형)",
                "LG 스탠바이미", "LG 올레드 evo 오브제컬렉션", "LG 올레드 TV (스탠드형)"};
        String[] imagePrice = {"1,950,000원", "월 42,900원", "1,040,000원",
                "9,900,000원", "1,890,000원"};

        for (int i = 0; i < imageName.length; i++) {
            addItem("iconName", imageName[i], imagePrice[i]);
        }

        mRecyclerViewAdapter = new RecycleViewAdapter(mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }

    public void firstInit() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mList = new ArrayList<>();
    }

    public void addItem(String imgName, String mainText, String subText) {
        RecycleViewItem item = new RecycleViewItem();
        item.setImgName(imgName);
        item.setMainText(mainText);
        item.setSubText(subText);
        mList.add(item);
    }

}
