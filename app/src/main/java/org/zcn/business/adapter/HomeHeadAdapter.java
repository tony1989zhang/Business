package org.zcn.business.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.zcn.business.bean.HomeDataBean.DataBean.HeadBean;

import org.zcn.business.R;
import org.zcn.business.utils.ShowImageUtils;

import java.util.List;

import static org.zcn.business.fragment.HomeFragment.TAG;

public class HomeHeadAdapter extends PagerAdapter {
    private Context context;
    LayoutInflater inflater;
    List<String> ads;
    public HomeHeadAdapter(Context context, List<String> ads){
        this.context = context;
         inflater = LayoutInflater.from(context);
         this.ads = ads;
    }
    @Override
    public int getCount() {
        return ads.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.imageview_home_head_ad, null);
        ImageView image= view.findViewById(R.id.image);
        String str = ads.get(position);
        Log.e(TAG, "instantiateItem: " + str);
        ShowImageUtils.showImageViewGone(context,image,str);
        container.addView(view); //添加到viewGroup当中
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);

    }
}
