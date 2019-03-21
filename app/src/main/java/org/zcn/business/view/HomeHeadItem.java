package org.zcn.business.view;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import org.zcn.business.bean.HomeDataBean.DataBean.HeadBean;

import org.zcn.business.R;
import org.zcn.business.adapter.HomeHeadAdapter;
import org.zcn.business.utils.ShowImageUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeHeadItem extends RelativeLayout {
    LayoutInflater mInflate;
    private HeadBean headBean;
    private Context mContext;
    private int currentPage = 0;

    public HomeHeadItem(Context context,HeadBean headBean) {
        this(context,null,headBean);
    }

    public HomeHeadItem(Context context, AttributeSet attrs,HeadBean headBean) {
        super(context, attrs);

        mInflate = LayoutInflater.from(context);
        mContext = context;
        //初始数据
        this.headBean = headBean;
        initView();
    }

    private void initView() {
        View inflate = mInflate.inflate(R.layout.item_home_listview_head, this);
        LinearLayout linearLayout = inflate.findViewById(R.id.linear_layout);
        /*
            实现自动轮播效果
         */
        ViewPager viewPager= inflate.findViewById(R.id.viewPager);
        TabLayout tabLayout = inflate.findViewById(R.id.tab_layout);
        viewPager.setAdapter(new HomeHeadAdapter(mContext, headBean.ads));
        tabLayout.setupWithViewPager(viewPager);

        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {

                if (currentPage == headBean.ads.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);

        ImageView[] imageViews = new ImageView[4];
        imageViews[0] = inflate.findViewById(R.id.iv_01);
        imageViews[1] = inflate.findViewById(R.id.iv_02);
        imageViews[2] = inflate.findViewById(R.id.iv_03);
        imageViews[3] = inflate.findViewById(R.id.iv_04);

        List<String> stringList = headBean.middle;
        for (int i = 0;i < stringList.size();i++){
            ShowImageUtils.showImageViewGone(mContext,imageViews[i],stringList.get(i));
        }

        for (int i = 0;i < headBean.footer.size();i++){
            HomeHeadFootItem homeHeadFootItem = new HomeHeadFootItem(mContext, headBean.footer.get(i));
            linearLayout.addView(homeHeadFootItem);
        }
    }
}
