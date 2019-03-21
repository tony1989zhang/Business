package org.zcn.business.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.zcn.business.R;
import org.zcn.business.fragment.HomeFragment;
import org.zcn.business.fragment.MessageFragment;
import org.zcn.business.fragment.MineFragment;

import cn.jzvd.Jzvd;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearContent;
    private TextView mIvTabHome;
    /**
     * 首页
     */
    private TextView mTvTabHome;
    private RelativeLayout mLayoutRelativitTabHome;
    private TextView mIvTabMessage;
    /**
     * 消息
     */
    private TextView mTvTabMessage;
    private RelativeLayout mLayoutRelativitTabMessage;
    private TextView mIvTabMine;
    /**
     * 我的
     */
    private TextView mTvTabMine;
    private RelativeLayout mLayoutRelativitTabMine;

    private FragmentManager manager;
    private HomeFragment homeFragment;
    private  MessageFragment messageFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
         manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
         homeFragment = new HomeFragment();
        transaction.replace(R.id.linear_content,homeFragment);
        transaction.commit();

        //刚进应用，默认显示第一个选项
        mIvTabHome.setBackgroundResource(R.drawable.comui_tab_home_selected);

    }

    private void initView() {
        mLinearContent = findViewById(R.id.linear_content);
        mIvTabHome = findViewById(R.id.iv_tab_home);
        mTvTabHome = findViewById(R.id.tv_tab_home);
        mLayoutRelativitTabHome = findViewById(R.id.layout_relativit_tab_home);
        mIvTabMessage = findViewById(R.id.iv_tab_message);
        mTvTabMessage = findViewById(R.id.tv_tab_message);
        mLayoutRelativitTabMessage = findViewById(R.id.layout_relativit_tab_message);
        mIvTabMine = findViewById(R.id.iv_tab_mine);
        mTvTabMine = findViewById(R.id.tv_tab_mine);
        mLayoutRelativitTabMine = findViewById(R.id.layout_relativit_tab_mine);


        //三个Tab 点击事件
        mLayoutRelativitTabHome.setOnClickListener(this);
        mLayoutRelativitTabMessage.setOnClickListener(this);
        mLayoutRelativitTabMine.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()){
            case  R.id.layout_relativit_tab_home:

                //更改Tab 颜色，将当前的Tab 颜色更改为选定，将其他Tab颜色更改为未选定
                mIvTabHome.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mIvTabMessage.setBackgroundResource(R.drawable.comui_tab_message);
                mIvTabMine.setBackgroundResource(R.drawable.comui_tab_person);

                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.linear_content,homeFragment);
                }

                    hintFragment(transaction,messageFragment);
                    hintFragment(transaction,mineFragment);
                    transaction.show(homeFragment);
                break;
            case R.id.layout_relativit_tab_message:

                //更改Tab 颜色，将当前的Tab 颜色更改为选定，将其他Tab颜色更改为未选定
                mIvTabHome.setBackgroundResource(R.drawable.comui_tab_home);
                mIvTabMessage.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mIvTabMine.setBackgroundResource(R.drawable.comui_tab_person);

                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.linear_content, messageFragment);
                }
                    //to do
                    hintFragment(transaction,homeFragment);
                    hintFragment(transaction,mineFragment);
                    transaction.show(messageFragment);

                break;
            case R.id.layout_relativit_tab_mine:

                //更改Tab 颜色，将当前的Tab 颜色更改为选定，将其他Tab颜色更改为未选定
                mIvTabHome.setBackgroundResource(R.drawable.comui_tab_home);
                mIvTabMessage.setBackgroundResource(R.drawable.comui_tab_message);
                mIvTabMine.setBackgroundResource(R.drawable.comui_tab_person_selected);

                if (mineFragment == null){
                    mineFragment = new MineFragment();
                    transaction.add(R.id.linear_content,mineFragment);
                }
                    hintFragment(transaction,homeFragment);
                    hintFragment(transaction,messageFragment);
                    transaction.show(mineFragment);
                break;

        }
        transaction.commit();
    }

    private void hintFragment(FragmentTransaction transaction, Fragment fragment) {
        if (fragment != null) {
            transaction.hide(fragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.resetAllVideos();
    }
}
