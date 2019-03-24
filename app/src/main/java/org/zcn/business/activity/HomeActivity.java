package org.zcn.business.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.king.zxing.Intents;

import org.zcn.business.R;
import org.zcn.business.fragment.HomeFragment;
import org.zcn.business.fragment.MessageFragment;
import org.zcn.business.fragment.MineFragment;
import org.zcn.business.utils.zxing.CustomXingActivity;

import cn.jzvd.Jzvd;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 扫描二维码 常量使用
     */
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_IS_CONTINUOUS = "key_continuous_scan";
    public static final int REQUEST_CODE_SCAN = 0X01;

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
        FragmentTransaction  transaction = manager.beginTransaction();
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
                showMessageFragment(transaction);

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

    private void showMessageFragment(FragmentTransaction transaction) {
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
    }

    private void hintFragment(FragmentTransaction transaction, Fragment fragment) {
        if (fragment != null) {
            transaction.hide(fragment);
        }
    }





    //跳转二维码扫描页面
    private void startScan() {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(this,R.anim.in,R.anim.out);
        Intent intent = new Intent(this, CustomXingActivity.class);
        intent.putExtra(KEY_TITLE,"我想扫一扫");
        intent.putExtra(KEY_IS_CONTINUOUS,false);
        ActivityCompat.startActivityForResult(this,intent,REQUEST_CODE_SCAN,optionsCompat.toBundle());
    }

    //检查当前是否有调用摄像头权限
    public void checkPermission() {
        performCodeWithPermission("调用摄像头", new PermissionCallback() {
            @Override
            public void hasPermission() {
                //开启二维码扫描
                startScan();
            }

            @Override
            public void noPermission() {
                //做其他处理
                Toast.makeText(HomeActivity.this, "需要到设置里面开启调用摄像机权限", Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.CAMERA);
    }

    //扫描二维码后返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
            switch (requestCode){
                case REQUEST_CODE_SCAN:
                    String result = data.getStringExtra(Intents.Scan.RESULT);
                    Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
                    //扫描成功后，切换到消息页面
                    onClick(mLayoutRelativitTabMessage);
                    break;
            }

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
