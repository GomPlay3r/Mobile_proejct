package com.example.project_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import android.widget.TextView;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class main_menu extends AppCompatActivity {
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 2;
    private CircleIndicator3 mIndicator;
    TextView userName;
    private RecyclerView mRecyclerView;
    private ArrayList<RecycleViewItem> mList;
    private RecycleViewAdapter mRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        // 소메뉴 스크롤
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start에 지정된 Drawer 열기
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // 소메뉴 스와이핑
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);


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
