package org.zcn.business.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.zcn.business.R;
import org.zcn.business.adapter.HomeAdapter;
import org.zcn.business.bean.HomeDataBean;
import org.zcn.business.network.RequestCenter;
import org.zcn.business.utils.GsonUtil;
import org.zcn.business.view.HomeHeadItem;
import org.zcn.zcnlib.okhttp.listener.DisposeDataListener;

public class HomeFragment extends BaseFragment {

    public static final String TAG = "HomeFragment";
    private View view;
    private TextView mTvScan;
    private TextView mTvMenu;
    private ImageView mIvPageLoading;
    private ListView mListviewHome;
    private HomeDataBean mHomeDataBean;
    private FragmentActivity mFragmentActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentActivity = getActivity();
        //创建时候加载数据
        loadData();
    }
    private void loadData() {
        RequestCenter.loadHomeData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object o) {
                Log.e(TAG, "onSuccess: " + o.toString() );
                 mHomeDataBean = GsonUtil.GsonToBean(o.toString(), HomeDataBean.class);
                //将ListView页面展示
                if (mHomeDataBean.ecode == 0 && mHomeDataBean.data != null) {
                         showListView();
                }else{
                    //显示正在加载中的页面，或则错误页面
                    mIvPageLoading.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Object o) {

            }
        });

    }

    private void showListView() {

        if ( mHomeDataBean.data.list != null &&   mHomeDataBean.data.list.size() > 0) {
            mIvPageLoading.setVisibility(View.GONE); //加载动画隐藏起来，
            mListviewHome.setVisibility(View.VISIBLE); //显示列表


            mListviewHome.setAdapter(new HomeAdapter(mFragmentActivity, mHomeDataBean.data.list));
            //g给ListView 添加头部布局
            mListviewHome.addHeaderView(new HomeHeadItem(mFragmentActivity,mHomeDataBean.data.head));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mTvScan = inflate.findViewById(R.id.tv_scan);
        mTvMenu = inflate.findViewById(R.id.tv_menu);
        mIvPageLoading = inflate.findViewById(R.id.iv_page_loading);
        mListviewHome = inflate.findViewById(R.id.listview_home);
        //开启帧动画
        AnimationDrawable drawable = (AnimationDrawable) mIvPageLoading.getDrawable();
        drawable.start();
    }
}
