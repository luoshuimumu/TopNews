package com.example.luoshuimumu.TopNews.widget.annotation;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.utils.ScreenUtils;


/**
 * Created by luoshuimumu on 2018/2/5.
 */

/**
 * gank网站的图片需要按宽度获取
 */
public class ImgBinder {
    @BindingAdapter(value = {"src", "defaultImg"}, requireAll = false)
    public static void loadImg(final ImageView imageView, String url
            , final Drawable defaultImg) {
        //TODO 只有gank开头的图片需要按宽度获取图片
        //获取imageview宽度，只获取该宽度的图片
        if (null != url && !TextUtils.isEmpty(url) && url.contains("img.gank.io")) {
            ViewGroup.LayoutParams para = imageView.getLayoutParams();
            int imageWidth = para.width;
            String urlSuffix = "?imageView2/0/w/" + imageWidth;
            url += urlSuffix;
        }
        final BitmapRequestBuilder builder = Glide.with(imageView.getContext())
                .load(url)
                .asBitmap()//使用CircleImageView需要转换为bitmap，否则第一次加载只显示占位图片
                .fitCenter()
//                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        if (null == defaultImg) {
            builder.placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher);
        } else {
            builder.placeholder(defaultImg)
                    .error(defaultImg);
        }
        builder.into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                if (null != resource) {
                    int imageWidth = resource.getWidth();
                    int imageHeight = resource.getHeight();
                    int height = ScreenUtils.getScreenWidth(imageView.getContext())
                            * imageHeight / imageWidth;
                    ViewGroup.LayoutParams para = imageView.getLayoutParams();
                    para.height = height;
//                    para.width = ScreenUtils.getScreenWidth(imageView.getContext());
                    imageView.setImageBitmap(resource);
                } else {
                    imageView.setImageDrawable(defaultImg);
                }
            }
        });
        builder.into(imageView);
    }
}
