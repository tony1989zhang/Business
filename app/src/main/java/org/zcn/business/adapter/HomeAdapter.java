package org.zcn.business.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.zcn.business.R;
import org.zcn.business.bean.HomeDataBean;
import org.zcn.business.bean.HomeDataBean.DataBean.ListBean;
import org.zcn.business.utils.ShowImageUtils;
import org.zcn.zcnlib.Utils;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;

public class HomeAdapter extends BaseAdapter {
    private Context mContext;
    private List<ListBean> mLists;
    private LayoutInflater mInflater;
    private ViewHolder mViewHolder;
    public static final int VIEW_TYPE_COUNT = 4;
    public static final int VIEW_TYPE_VIDEO = 0;
    public static final int VIEW_TYPE_ONE = 1;
    public static final int VIEW_TYPE_TWO = 2;
    public static final int VIEW_TYPE_THREE = 3;

    public HomeAdapter(Context context, List<ListBean> lists) {
        mContext = context;
        mLists = lists;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public ListBean getItem(int position) {
        return mLists.get(position);
    }

    //数据类型
    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        ListBean item = getItem(position);
        return item.type;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        ListBean listBean = getItem(position);
        if (convertView == null){
            mViewHolder = new ViewHolder();
            switch (itemViewType){
                case VIEW_TYPE_VIDEO:

                    // 第一种布局，第二种布局公用，视频布局公用
                    convertView = mInflater.inflate(R.layout.item_product_video_layout,parent,false);
                    mViewHolder.mItemLogoView = convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mItemTitleView = convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mItemInfoView = convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mItemFromView = convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mItemZanView = convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mItemFooterView = convertView.findViewById(R.id.item_footer_view);

                    mViewHolder.mVideo = convertView.findViewById(R.id.product_video_view);
                    break;
                case VIEW_TYPE_ONE:
                    convertView = mInflater.inflate(R.layout.item_product_card_one_layout, parent,false);
                    // 第一种布局，第二种布局公用，视频布局公用
                    mViewHolder.mItemLogoView = convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mItemTitleView = convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mItemInfoView = convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mItemFromView = convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mItemZanView = convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mItemFooterView = convertView.findViewById(R.id.item_footer_view);
                    //第一种，第二种布局公用
                    mViewHolder.mItemPriceView = convertView.findViewById(R.id.item_price_view);

                    //第一种布局独立使用
                    mViewHolder.mProductPhotoLayout = convertView.findViewById(R.id.product_photo_layout);
                    break;
                case VIEW_TYPE_TWO:
                    convertView = mInflater.inflate(R.layout.item_product_card_two_layout, parent,false);
                    // 第一种布局，第二种布局公用，视频布局公用
                    mViewHolder.mItemLogoView = convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mItemTitleView = convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mItemInfoView = convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mItemFromView = convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mItemZanView = convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mItemFooterView = convertView.findViewById(R.id.item_footer_view);
                    //第一种，第二种布局公用
                    mViewHolder.mItemPriceView = convertView.findViewById(R.id.item_price_view);

                    mViewHolder.mProductPhotoView = convertView.findViewById(R.id.product_video_view);
                    break;
                case VIEW_TYPE_THREE:
                    convertView = mInflater.inflate(R.layout.item_product_card_three_layout,  parent,false);
                    mViewHolder.viewPager = convertView.findViewById(R.id.pager);
                    break;
            }
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        switch (itemViewType){
            case VIEW_TYPE_VIDEO:
                mViewHolder.mItemTitleView.setText(listBean.title);
                mViewHolder.mItemInfoView.setText(listBean.info);
                mViewHolder.mItemFooterView.setText(listBean.text);
                mViewHolder.mItemFromView.setText(listBean.from);
                mViewHolder.mItemZanView.setText(listBean.zan);
                mViewHolder.mVideo.setUp(listBean.resource
                        , listBean.title , Jzvd.SCREEN_WINDOW_FULLSCREEN);
                break;
            case VIEW_TYPE_ONE:
                mViewHolder.mItemTitleView.setText(listBean.title);
                mViewHolder.mItemInfoView.setText(listBean.info);
                mViewHolder.mItemFooterView.setText(listBean.text);
                mViewHolder.mItemPriceView.setText(listBean.price);
                mViewHolder.mItemFromView.setText(listBean.from);
                mViewHolder.mItemZanView.setText(listBean.zan);
                ShowImageUtils.showImageViewGone(mContext, mViewHolder.mItemLogoView, listBean.logo);
//            ShowImageUtils.showImageViewGone(mContext,viewHolder.image,listBean.url.get(0));
                mViewHolder.mProductPhotoLayout.removeAllViews();
                for (String picUrl : listBean.url) {
                    ImageView imageView = getPhotoImageView(picUrl);
                    mViewHolder.mProductPhotoLayout.addView(imageView);
                }
                break;
            case VIEW_TYPE_TWO:
                mViewHolder.mItemTitleView.setText(listBean.title);
                mViewHolder.mItemInfoView.setText(listBean.info);
                mViewHolder.mItemFooterView.setText(listBean.text);
                mViewHolder.mItemPriceView.setText(listBean.price);
                mViewHolder.mItemFromView.setText(listBean.from);
                mViewHolder.mItemZanView.setText(listBean.zan);
                ShowImageUtils.showImageViewGone(mContext, mViewHolder.mItemLogoView, listBean.logo);
                ShowImageUtils.showImageView(mContext,R.drawable.page_loading_01,listBean.url.get(0),mViewHolder.mProductPhotoView);
                break;
            case VIEW_TYPE_THREE:
                ArrayList<ListBean> listBeans = getListBeans(listBean);
                mViewHolder.viewPager.setAdapter(new HomeHotPager(mContext,listBeans));
                mViewHolder.viewPager.setPageMargin(Utils.dip2px(mContext,12));
                mViewHolder.viewPager.setCurrentItem(listBeans.size() * 500);
                break;
        }


        return convertView;
    }

    private ArrayList<ListBean> getListBeans(ListBean listBean) {
        String[] titles = listBean.title.split("@");
        String[] prices = listBean.price.split("@");
        String[] infos = listBean.info.split("@");
        String[] texts = listBean.text.split("@");
        List<String> urls = listBean.url;

        ArrayList<ListBean> listBeans = new ArrayList<>();
        int urlSize = 3;
        for (int j = 0;j < titles.length; j++){
            ListBean listBean1 = new ListBean();
            listBean1.title = titles[j];
            listBean1.price = prices[j];
            listBean1.info = infos[j];
            listBean1.text = texts[j];
            listBean1.url = urls.subList(j*urlSize,(j+1)*urlSize);
            //图片设置
            listBeans.add(listBean1);
        }
        return listBeans;
    }

    //动态生成ImageView
    private ImageView getPhotoImageView(String picUrl) {
        ImageView imageView = new ImageView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Utils.dip2px(mContext, 120),
                LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = Utils.dip2px(mContext, 5);
        imageView.setLayoutParams(layoutParams);
        ShowImageUtils.showImageViewGone(mContext, imageView, picUrl);
        return imageView;
    }




    static class ViewHolder {

        // 第一种布局，第二种布局公用，视频布局公用
        ImageView mItemLogoView;
        TextView mItemTitleView;

        TextView mItemInfoView;
        TextView mItemFromView;
        TextView mItemZanView;

        //第一种，第二种布局公用
        TextView mItemPriceView;

        //第一种布局独立使用
        LinearLayout mProductPhotoLayout;
        HorizontalScrollView mItemProductLayout;
        TextView mItemFooterView;
        //第二种布局独立使用
        ImageView mProductPhotoView;

        //第三种布局
        ViewPager viewPager;

        //视频布局独立使用
        Jzvd mVideo;
    }

}
