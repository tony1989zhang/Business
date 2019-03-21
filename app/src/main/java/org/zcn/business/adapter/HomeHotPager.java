package org.zcn.business.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.zcn.business.R;
import org.zcn.business.bean.HomeDataBean;

import java.util.ArrayList;
import org.zcn.business.bean.HomeDataBean.DataBean.ListBean;
import org.zcn.business.utils.ShowImageUtils;

public class HomeHotPager extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ListBean> listBeans;

    public HomeHotPager(Context context,ArrayList<ListBean> listBeans) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listBeans = listBeans;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_hot_product_pager_layout, null);
        ListBean listBean = listBeans.get(position%listBeans.size());
        TextView titleView = view.findViewById(R.id.title_view);
        TextView infoView = view.findViewById(R.id.info_view);
        TextView gonggaoView = view.findViewById(R.id.gonggao_view);
        TextView saleNumView = view.findViewById(R.id.sale_num_view);
        ImageView[] imageViews = new ImageView[3];
        imageViews[0] = view.findViewById(R.id.image_one);
        imageViews[1] = view.findViewById(R.id.image_two);
        imageViews[2] = view.findViewById(R.id.image_three);
        for (int i = 0; i <imageViews.length;i++) {
            ShowImageUtils.showImageView(context,R.drawable.page_loading_01,listBean.url.get(i),imageViews[i]);
        }

        titleView.setText(listBean.title);
        infoView.setText(listBean.info);
        gonggaoView.setText(listBean.text);
        saleNumView.setText(listBean.price);

        //添加布局
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
