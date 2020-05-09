package com.example.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class TabViewPagerActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view_pager);

//        初始化总布局
        final TabHost tabHost = (TabHost) findViewById(R.id.tab_host);
        tabHost.setup();
//        三个tab处理
//       1. 构造数据
        String [] titleIDs = {"首页","消息","生日"};
        int [] drawableIDs= {R.drawable.main_tab_icon_book,
                R.drawable.main_tab_icon_home,
                R.drawable.mian_tab_icon_birth};
//        2. 把数据搞到视图里面
        for (int i = 0; i <titleIDs.length ; i++) {
            View view = getLayoutInflater().inflate(R.layout.main_tab_layout,null,false);
            ImageView imageView = (ImageView)view.findViewById(R.id.main_tab_icon);
            TextView textView = (TextView) view.findViewById(R.id.main_tab_txt);
            View tab = view.findViewById(R.id.tab_bg);
            imageView.setImageResource(drawableIDs[i]);
            textView.setText(titleIDs[i]);
            tab.setBackgroundColor(Color.rgb(255,255,255));
            tabHost.addTab(tabHost.newTabSpec(titleIDs[i]).setIndicator(view).setContent(this));
        }


//  三个fragment组成的view Pager
        final Fragment[] fragments = new Fragment[] {
                Test1.newInstance("Home"),
                Test1.newInstance("Main"),
                Test1.newInstance("More"),

        };
        final ViewPager viewPager =(ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (tabHost != null) {
                    tabHost.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int position = tabHost.getCurrentTab();
                if (tabHost != null && viewPager != null) {
                    viewPager.setCurrentItem(position);
                }
            }
        });
    }
//    此方法为implement实现的方法
    @Override
    public  View createTabContent(String s) {
        View view = new View(this);
        view.setMinimumHeight(0);
        view.setMinimumWidth(0);
        return view;
    };
}
