package org.zcn.business.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * 网络图片的工具类
 * <p>
 * (1)ImageView设置图片（错误图片）
 * （2）ImageView设置图片---BitMap(不设置默认图)
 * （3）设置RelativeLayout
 * （4）设置LinearLayout
 * （5）设置FrameLayout
 * （6）高斯模糊------ RelativeLayout
 * （7）高斯模糊------ LinearLayout
 * （8）圆角显示图片  ImageView
 *
 * @author huangshuyuan
 */
public class ShowImageUtils {

    /**
     * (1)
     * 显示图片Imageview
     *
     * @param context  上下文
     * @param errorimg 错误的资源图片
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageView(Context context, int errorimg, String url,
                                     ImageView imgeview) {
        Glide.with(context).load(url)// 加载图片
                .error(errorimg)// 设置错误图片
                .placeholder(errorimg)// 设置占位图
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)// 缓存修改过的图片
                .into(imgeview);
        // Glide.with(context).load(url).thumbnail(0.1f).error(errorimg)
        // .into(imgeview);

        // Glide
        // .with(context)
        // .load(UsageExampleListViewAdapter.eatFoodyImages[0])
        // .placeholder(R.mipmap.ic_launcher) //设置占位图
        // .error(R.mipmap.future_studio_launcher) //设置错误图片
        // .crossFade() //设置淡入淡出效果，默认300ms，可以传参
        // //.dontAnimate() //不显示动画效果
        // .into(imageViewFade);

    }

    /**
     * （2）
     * 获取到Bitmap---不设置错误图片，错误图片不显示
     *
     * @param context
     * @param imageView
     * @param url
     */

    public static void showImageViewGone(Context context,
                                         final ImageView imageView, String url) {
        Glide.with(context).asBitmap().load(url)

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)// 缓存修改过的图片
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setVisibility(View.VISIBLE);
                        BitmapDrawable bd = new BitmapDrawable(resource);
                        imageView.setImageDrawable(bd);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        imageView.setVisibility(View.GONE);
                    }
                });



    }

    /**
     * （3）
     * 设置RelativeLayout
     * <p>
     * 获取到Bitmap
     *
     * @param context
     * @param errorimg
     * @param url
     * @param bgLayout
     */

    public static void showImageView(Context context, int errorimg, String url,
                                     final RelativeLayout bgLayout) {
        Glide.with(context).asBitmap().load(url).error(errorimg)// 设置错误图片

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)// 缓存修改过的图片
                .placeholder(errorimg)// 设置占位图
                .into(new SimpleTarget<Bitmap>() {

                    @SuppressLint("NewApi")
                    @Override
                    public void onResourceReady(Bitmap loadedImage,
                                                @Nullable Transition<? super Bitmap> transition) {
                        BitmapDrawable bd = new BitmapDrawable(loadedImage);

                        bgLayout.setBackground(bd);

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        // TODO Auto-generated method stub
                        super.onLoadFailed( errorDrawable);

                        bgLayout.setBackgroundDrawable(errorDrawable);
                    }

                });

    }

    /**
     * （4）
     * 设置LinearLayout
     * <p>
     * 获取到Bitmap
     *
     * @param context
     * @param errorimg
     * @param url
     * @param bgLayout
     */

    public static void showImageView(Context context, int errorimg, String url,
                                     final LinearLayout bgLayout) {
        Glide.with(context).asBitmap().load(url).error(errorimg)// 设置错误图片

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)// 缓存修改过的图片
                .placeholder(errorimg)// 设置占位图
                .into(new SimpleTarget<Bitmap>() {

                    @SuppressLint("NewApi")
                    @Override
                    public void onResourceReady(Bitmap loadedImage,
                                                @Nullable Transition<? super Bitmap> transition) {
                        BitmapDrawable bd = new BitmapDrawable(loadedImage);

                        bgLayout.setBackground(bd);

                    }

                    @Override
                    public void onLoadFailed( Drawable errorDrawable) {
                        // TODO Auto-generated method stub
                        super.onLoadFailed(errorDrawable);

                        bgLayout.setBackgroundDrawable(errorDrawable);
                    }

                });

    }

    /**
     * （5）
     * 设置FrameLayout
     * <p>
     * 获取到Bitmap
     *
     * @param context
     * @param errorimg
     * @param url
     * @param frameBg
     */

    public static void showImageView(Context context, int errorimg, String url,
                                     final FrameLayout frameBg) {
        Glide.with(context).asBitmap().load(url).error(errorimg)// 设置错误图片

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)// 缓存修改过的图片
                .placeholder(errorimg)// 设置占位图
                .into(new SimpleTarget<Bitmap>() {

                    @SuppressLint("NewApi")
                    @Override
                    public void onResourceReady(Bitmap loadedImage,
                                                @Nullable Transition<? super Bitmap> transition) {
                        BitmapDrawable bd = new BitmapDrawable(loadedImage);

                        frameBg.setBackground(bd);

                    }

                    @Override
                    public void onLoadFailed( Drawable errorDrawable) {
                        // TODO Auto-generated method stub
                        super.onLoadFailed(errorDrawable);

                        frameBg.setBackgroundDrawable(errorDrawable);
                    }

                });

    }


    /**
     * （8）
     * 显示图片 圆角显示  ImageView
     *
     * @param context  上下文
     * @param errorimg 错误的资源图片
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageViewToCircle(Application context, int errorimg,
                                             String url, ImageView imgeview) {
        Glide.with(context).load(url)
                // 加载图片
                .error(errorimg)
                // 设置错误图片
                // 设置淡入淡出效果，默认300ms，可以传参
                .placeholder(errorimg)
                // 设置占位图
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)// 缓存修改过的图片
                .into(imgeview);
        // Glide.with(context).load(url).thumbnail(0.1f).error(errorimg)
        // .into(imgeview);

        // Glide
        // .with(context)
        // .load(UsageExampleListViewAdapter.eatFoodyImages[0])
        // .placeholder(R.mipmap.ic_launcher) //设置占位图
        // .error(R.mipmap.future_studio_launcher) //设置错误图片
        // .crossFade() //设置淡入淡出效果，默认300ms，可以传参
        // //.dontAnimate() //不显示动画效果
        // .into(imageViewFade);

    }

}

