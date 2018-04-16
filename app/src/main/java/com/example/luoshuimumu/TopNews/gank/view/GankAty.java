package com.example.luoshuimumu.TopNews.gank.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.gank.vm.GankDayViewModel;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by luoshuimumu on 2017/11/13.
 */

public class GankAty extends RxAppCompatActivity implements GankDayViewModel.IGankDayCallbak {
    //TODO 两个fragment可能要公用一个viewModel??但是只想共享数据，并不想共享contex

    private GankTitleFgm fgmTitle;
    private GankListContentFgm fgmContent;

    //fgm管理器，开启事务后才能用
    FragmentManager fm;

    @Override
    public void onUpdateListComplete(String year, String month, String day) {
        fgmContent.onUpdateListComplete(year, month, day);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_top_news);
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fgmTitle = GankTitleFgm.newInstance(savedInstanceState);
        transaction.add(R.id.aty_gank_fgm_title, fgmTitle);
        fgmContent = GankListContentFgm.newInstance(savedInstanceState);
        transaction.add(R.id.aty_gank_fgm_content, fgmContent);
        transaction.commit();
    }


}
