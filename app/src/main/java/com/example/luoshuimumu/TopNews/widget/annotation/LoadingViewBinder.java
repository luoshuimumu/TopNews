package com.example.luoshuimumu.TopNews.widget.annotation;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.gank.vm.LoadingViewContainer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by luoshuimumu on 2018/5/7.
 */

public class LoadingViewBinder {
    @BindingAdapter(value = {"loading_status"}, requireAll = false)
    public static void bindLoadingStatus(RelativeLayout view, Integer status) {
        if (null == status) {
            return;
        }
        TextView tv = view.findViewById(R.id.tv);

        //TODO 修改状态
        switch (status) {
            case LoadingViewContainer.STATUS_LOAD_SUCCESS:
                //修改显示状态
                if (null != tv) {
                    tv.setText(view.getContext().getResources().getString(R.string.loading_view_success));
                }
                hideViewDelay(view, 3000);
                break;
            case LoadingViewContainer.STATUS_LOAD_FAIL:
                if (null != tv) {
                    tv.setText(view.getContext().getResources().getString(R.string.loading_view_fail));
                }
                hideViewDelay(view, 3000);
                break;
            case LoadingViewContainer.STATUS_LOADING:
                if (null != tv) {
                    tv.setText(view.getContext().getResources().getString(R.string.loading_view_loading));
                }
                break;
            default:
        }
    }

    private static void hideViewDelay(View view, long delay) {
        if (null != view) {
            Observable.empty().delay(delay, TimeUnit.MILLISECONDS).subscribe((o) -> {
                if (null != view && View.VISIBLE == view.getVisibility()) {
                    //加入动画
                    AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            view.setVisibility(View.GONE);
                        }
                    });
                }
            });
        }
    }

}
