package com.example.luoshuimumu.TopNews.base;

/**
 * v 层接口,封装通用的网络响应
 * Created by luoshuimumu on 2017/11/13.
 */

public interface IView {
    /**
     * 展示等待进度条
     */
    void showLoadingProgress();

    /**
     * 展示覆盖页面的等待图
     */
    void showLoadingWholly();

    void dismissLoadingWholly();

    void dismissLoadingProgress();

    /**
     * 展示 toast
     *
     * @param mag
     */
    void showToast(String mag);
}
