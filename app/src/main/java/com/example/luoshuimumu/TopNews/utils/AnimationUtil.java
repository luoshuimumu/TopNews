package com.example.luoshuimumu.TopNews.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by luoshuimumu on 2018/4/22.
 */

public class AnimationUtil {
    public static void startShowAnimation(View view, long duration) {
        if (null == view || duration < 0) {
            return;
        }
        AlphaAnimation showAlphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        showAlphaAnimation.setDuration(duration);
        showAlphaAnimation.setFillAfter(true);//动画结束时停留在最后一帧
        showAlphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(showAlphaAnimation);
    }
}
