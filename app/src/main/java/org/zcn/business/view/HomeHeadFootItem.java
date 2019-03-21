package org.zcn.business.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.zcn.business.R;
import org.zcn.business.bean.HomeDataBean.DataBean.HeadBean.FooterBean;
import org.zcn.business.utils.ShowImageUtils;
import java.util.List;
public class HomeHeadFootItem extends RelativeLayout {
    private Context mContext;
    private FooterBean mBean;
    private LayoutInflater mInflater;
    public HomeHeadFootItem(Context context, FooterBean bean) {
        this(context, null, bean);
    }
    public HomeHeadFootItem(Context context, AttributeSet attrs, FooterBean bean) {
        super(context, attrs);
        this.mContext = context;
        this.mBean = bean;
        mInflater = LayoutInflater.from(context);
        initView();
    }
    private void initView() {
        View view = mInflater.inflate(R.layout.item_home_listview_head_footer, this);
        TextView titleTv = view.findViewById(R.id.item_title_view);
        TextView infoTv = view.findViewById(R.id.item_info_view);
        TextView fromTv = view.findViewById(R.id.item_from_view);
        ImageView imageOne =view.findViewById(R.id.iv_one);
        ImageView imageTwo = view.findViewById(R.id.iv_two);
        titleTv.setText(mBean.title);
        infoTv.setText(mBean.info);
        fromTv.setText(mBean.from);
        ShowImageUtils.showImageViewGone(mContext,imageOne,mBean.imageOne);
        ShowImageUtils.showImageViewGone(mContext,imageTwo,mBean.imageTwo);
    }
}
