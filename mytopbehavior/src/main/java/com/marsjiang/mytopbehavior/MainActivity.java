package com.marsjiang.mytopbehavior;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private FrameLayout fl_layout;
    private TextView tv_icon;
    private ImageView iv_icon;
    private int sumY = 0;
    private ArrayList<Integer> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initViews();
    }

    private void initDatas() {
        arr = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            arr.add(i);
        }
    }

    private void initViews() {
        fl_layout = (FrameLayout) findViewById(R.id.fl_layout);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        tv_icon = (TextView) findViewById(R.id.tv_icon);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        recycler_view.setAdapter(new RecyclerViewAdapter(this, arr));
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        //RecycleView的滑动监听(用来处理顶部栏的透明度)
        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sumY += dy;
                if (sumY <= 60) {
                    double scaleX = sumY * 0.1 / (100 * 0.1);
                    double scaleY = sumY * 0.1 / (100 * 0.1);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_icon.getLayoutParams();
                    params.height = (int) (100 * (1 - scaleX));
                    params.width = (int) (100 * (1 - scaleY));
                    iv_icon.setLayoutParams(params);

//                    iv_icon.setX(0);
//                    tv_icon.setScaleX((float) (tv_icon.getHeight() * (1 - scaleY)));
//                    tv_icon.setScaleY((float) (tv_icon.getWidth() * (1 - scaleY)));
                    RelativeLayout.LayoutParams params01 = (RelativeLayout.LayoutParams) tv_icon.getLayoutParams();
                    if (0 <= scaleX && scaleX <= 0.3) {
                        params01.height = (int) (50 * (1 - scaleX));
                        params01.width = (int) (200 * (1 - scaleY) + 5 * scaleY * 10);
                        params01.setMargins(0, (int) (20 * (1 - scaleX)), 0, 0);
                        tv_icon.setTextSize((float) (30 * (1 - scaleX)));
                        tv_icon.setLayoutParams(params01);
                    }
//                    if (scaleX == 0) {
//                        params01.height = 50;
//                        params01.width = 200;
//                        params01.setMargins(0, 20, 0, 0);
//                        tv_icon.setTextScaleX(30);
//                        tv_icon.setLayoutParams(params01);
//                    }
                    Log.d("scaleInfo", tv_icon.getHeight() * (1 - scaleX) + "--------------------" + (float) (tv_icon.getWidth() * (1 - scaleY)) + "");
                }
            }
        });
    }
}
