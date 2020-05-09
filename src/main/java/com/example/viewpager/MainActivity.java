package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager guideViewPager;
    private int [] guideViewListId = {
      R.layout.activity_first,
      R.layout.activity_second,
      R.layout.activity_third,
      R.layout.activity_four
    };

    private List<View> views;
//    本事是xml中的view容器
    private ViewGroup mDotViewGroup ;
    private  List<ImageView> mDotViews = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        找到这个viewPager
        guideViewPager = findViewById(R.id.guideViewPager);
        mDotViewGroup = findViewById(R.id.dotViews);
//        需要设置viewPager的adapter
        guideViewPager.setAdapter(mPagerAdapter);
        guideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <mDotViews.size(); i++) {
                    mDotViews.get(i).setImageResource(position == i ? R.mipmap.lamp: R.mipmap.ic_launcher);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        初始化数据
         views = new ArrayList<>();
//         2.引导图
        for (int i = 0; i < guideViewListId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            views.add(imageView);
            ImageView dot = new ImageView(this);
            dot.setImageResource(R.mipmap.ic_launcher);
            dot.setMaxHeight(100);
            dot.setMaxWidth(100);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20,20);
            layoutParams.leftMargin = 20;
            dot.setLayoutParams(layoutParams);
            dot.setEnabled(false);
            mDotViewGroup.addView(dot);
            mDotViews.add(dot);
        }
//         1.最简单的实现
//        for (int i = 0; i < guideViewListId.length; i++) {
////            把xml解析成view添加到views里
//            views.add(getLayoutInflater().inflate(guideViewListId[i],null));
//        }




    }
    PagerAdapter mPagerAdapter = new PagerAdapter() {
//        必须实现4个方法
        @Override
        public int getCount() {
            return  guideViewListId.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View child = views.get(position);
            container.addView(child);
            return child;
            //            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
             container.removeView(views.get(position));
            //            super.destroyItem(container, position, object);
        }
    };
}
