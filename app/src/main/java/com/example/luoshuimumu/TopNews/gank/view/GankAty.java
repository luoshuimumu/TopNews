package com.example.luoshuimumu.TopNews.gank.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.gank.vm.GankListViewModel;
import com.example.luoshuimumu.TopNews.gank.vm.ViewModelHolder;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by luoshuimumu on 2017/11/13.
 */

public class GankAty extends RxAppCompatActivity {
    private static final String KEY_FGM_VM = "FGM_VM";
    //TODO 两个fragment可能要公用一个viewModel??但是只想共享数据，并不想共享contexd
    //viewModel放在 aty 层，多个fragment公用一个viewModel，有利于数据共享

    private GankTitleFgm fgmTitle;
    private GankListContentFgm fgmContent;

    GankListViewModel mViewModel;

    //fgm管理器，开启事务后才能用
    FragmentManager fm;

    /**
     * 取到当前日期，加载等待图片
     * 加载最近日期，contentFgm显示等待
     * 加载到最近日期，contentFgm显示加载
     * 加载完成显示页面
     * 点击切换时延迟读取，
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_top_news);
        //先展示广告页
        showAdsView();
        mViewModel = initViewModel();//从fragment初始化viewModel
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fgmTitle = GankTitleFgm.newInstance(savedInstanceState);
        fgmTitle.setViewModel(mViewModel);
        transaction.add(R.id.aty_gank_fgm_title, fgmTitle);
        fgmContent = GankListContentFgm.newInstance(savedInstanceState);
        fgmContent.setViewModel(mViewModel);
        transaction.add(R.id.aty_gank_fgm_content, fgmContent);
        transaction.commit();
    }

    /**
     * 显示大的页面
     */
    private void showAdsView() {
        AdsFragment adsFragment = new AdsFragment();
        adsFragment.show(getSupportFragmentManager(), AdsFragment.class.getSimpleName());
    }


    private GankListViewModel initViewModel() {
        ViewModelHolder<GankListViewModel> container
                = (ViewModelHolder<GankListViewModel>) getSupportFragmentManager().findFragmentByTag(KEY_FGM_VM);
        if (null != container) {
            return container.getViewModel();
        } else {
            //初始化并添加进aty管理
            GankListViewModel viewModel = new GankListViewModel(this);
            container = ViewModelHolder.createContainer(viewModel);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(container, KEY_FGM_VM);
            transaction.commit();
            return viewModel;
        }
    }


}
