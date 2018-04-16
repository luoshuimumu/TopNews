package com.example.luoshuimumu.TopNews.gank.vm;

import android.databinding.ObservableField;
import android.widget.Toast;

import com.example.luoshuimumu.TopNews.base.BaseViewModel;
import com.example.luoshuimumu.TopNews.gank.GankItemClickListenerContainer;
import com.example.luoshuimumu.TopNews.gank.widget.GankContentListAdapter;
import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;
import com.example.luoshuimumu.TopNews.gankio.entity.resp.GankDayResp;
import com.example.luoshuimumu.TopNews.net.service.TopNewsApiHelper;
import com.example.luoshuimumu.TopNews.widget.ListItemClickListenerMVVM;
import com.example.luoshuimumu.TopNews.widget.ListItemLongClickListenerMVVM;
import com.trello.rxlifecycle2.components.RxFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by luoshuimumu on 2017/11/9.
 */

public class GankListViewModel extends BaseViewModel implements IGankListViewModel {

    public ObservableField<GankContentListAdapter> gnakContentListAdapter = new ObservableField();
    private GankItemClickListenerContainer mContentClickListenerContainer
            = new GankItemClickListenerContainer();
    TopNewsApiHelper topNewsApiHelper;


    public GankListViewModel(RxFragment fragment) {
        super(fragment);
        topNewsApiHelper = new TopNewsApiHelper();
        //提前注册adaper
        GankContentListAdapter gankContentListAdapter = new GankContentListAdapter(mContext);
        gnakContentListAdapter.set(gankContentListAdapter);
        //TODO 初始化mGankStoreModel
    }

    /**
     * 获取某日数据
     */
    @Override
    public void getDayContent(String year, String month, String day) {
        requestWrap(topNewsApiHelper.getOneList(year, month, day))
                .subscribe(new Observer<GankDayResp>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }

                    @Override
                    public void onNext(GankDayResp resp) {
                        Toast.makeText(mContext, resp.toString(), Toast.LENGTH_SHORT).show();
                        //TODO 测试 添加福利数据
//                        if (null == adapter.get()) {
//                            GankContentListAdapter onelistAdapter = new GankContentListAdapter(mContext);
//                            onelistAdapter.appendData(resp.getWelfare());
//                            onelistAdapter.appendData(resp.getAndroid());
//                            adapter.set(onelistAdapter);
//                        } else {

                        gnakContentListAdapter.get().appendData(resp.getData());
//                        }
                        gnakContentListAdapter.get().notifyDataSetChanged();
                    }
                });
    }

    public void setContentClickListener(ListItemClickListenerMVVM<GankContent> listener) {
        mContentClickListenerContainer.setContentListener(listener);
        gnakContentListAdapter.get().setClickListenerContainer(mContentClickListenerContainer);
    }

    public void setLikeClickListener(ListItemClickListenerMVVM<GankContent> listener) {
        mContentClickListenerContainer.setLikeListener(listener);
        gnakContentListAdapter.get().setClickListenerContainer(mContentClickListenerContainer);
    }

    public void setShareClickListener(ListItemClickListenerMVVM<GankContent> listener) {
        mContentClickListenerContainer.setShareListener(listener);
        gnakContentListAdapter.get().setClickListenerContainer(mContentClickListenerContainer);
    }

    public void setLongClickClickListener(ListItemLongClickListenerMVVM<GankContent> listener) {
        mContentClickListenerContainer.setLongClickListener(listener);
        gnakContentListAdapter.get().setClickListenerContainer(mContentClickListenerContainer);
    }

}
