package com.example.luoshuimumu.TopNews.gank.vm;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.luoshuimumu.TopNews.base.BaseViewModel;
import com.example.luoshuimumu.TopNews.gank.GankItemClickListenerContainer;
import com.example.luoshuimumu.TopNews.gank.model.GankStoreModel;
import com.example.luoshuimumu.TopNews.gank.widget.GankContentListAdapter;
import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;
import com.example.luoshuimumu.TopNews.gankio.entity.resp.GankDayResp;
import com.example.luoshuimumu.TopNews.net.service.TopNewsApiHelper;
import com.example.luoshuimumu.TopNews.widget.ListItemClickListenerMVVM;
import com.example.luoshuimumu.TopNews.widget.ListItemLongClickListenerMVVM;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by luoshuimumu on 2017/11/9.
 */

public class GankListViewModel extends BaseViewModel implements IGankListViewModel, IGankDayViewModel {

    public ObservableField<GankContentListAdapter> gnakContentListAdapter = new ObservableField();
    private GankItemClickListenerContainer mContentClickListenerContainer
            = new GankItemClickListenerContainer();
    TopNewsApiHelper topNewsApiHelper;

    //存储数据的model
    GankStoreModel gankStoreModel;
    //当日日期
    public ObservableField<String> todayStr = new ObservableField();
    //当日日期，用于显示的样式
    public ObservableField<String> todaySpanStr = new ObservableField();
    //天气情况
    public ObservableField<String> weatherStr = new ObservableField();
    //维护数据列表
    public ObservableField<ArrayList<String>> gankdayList = new ObservableField();

    public GankListViewModel(LifecycleProvider lifecycleProvider) {
        super(lifecycleProvider);
        topNewsApiHelper = new TopNewsApiHelper();
        //提前注册adaper
        GankContentListAdapter gankContentListAdapter = new GankContentListAdapter(mContext);
        gnakContentListAdapter.set(gankContentListAdapter);
        //TODO 初始化mGankStoreModel
        gankStoreModel = new GankStoreModel(mContext);
        initTodayStrCallback();
        initGankdayListStrCallback();
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

    /**
     * 获取最新 idlist，对列表下拉刷新时调用
     */
    @Override
    public void getDayHistory() {
        Map<String, String> params = new HashMap<>();
        requestWrap(topNewsApiHelper.getIdList(params))
                .subscribe(new Observer<ArrayList<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //TODO 通用的错误处理封装在baseVM层
                        handleError(e);
                        //TODO 触发本地读取操作
                    }

                    @Override
                    public void onNext(ArrayList<String> gankDayListResp) {
                        //TODO 根据id获取获取文章
                        //展示列表
                        if (null != gankDayListResp && gankDayListResp.size() > 0) {
                            gankDayListAdapter.get().appendData(gankDayListResp);
                            gankDayListAdapter.get().notifyDataSetChanged();
                            //TODO 还应该触发其他vm的操作 考虑用EventBus
                            String[] days = gankDayListResp.get(0).split("-");
                            String year = "";
                            String month = "";
                            String day = "";
                            if (null != days && days.length == 3) {
                                year = days[0];
                                month = days[1];
                                day = days[2];
                                decorateTodayStr(year, month, day);
                            }
                            if (null != mCallbak) {
                                mCallbak.onUpdateListComplete(year, month, day);
                            }
                            //TODO 将数据存入数据库 没有的需要更新
                            //取下来一组列表 每次会删除旧表数据并更新表
                            gankStoreModel.storeGankDayHistory(gankDayListResp);
                        }
                    }
                });

        ;
    }

    private void initTodayStrCallback() {
        todayStr.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                //修改title显示的当前日期
                String date = todayStr.get();
                if (TextUtils.isEmpty(date)) {
                    return;
                }

                String[] days = date.split("-");
                String year = "";
                String month = "";
                String day = "";
                if (null != days && days.length == 3) {
                    year = days[0];
                    month = days[1];
                    day = days[2];
                    //修改title显示的日期
                    decorateTodayStr(year, month, day);
                    //设置该项为选中
                }
                //
                getDayContent(year, month, day);
            }
        });
    }

    private void initGankdayListStrCallback() {
        gankdayList.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                //TODO vm里写绑定的v的逻辑 没什么问题
                //但是vm没有持有v的引用，
                //demo的做法是xml的listview里绑定了item，为了
            }
        });
    }

    /**
     * 为日期字符串添加样式
     */
    private void decorateTodayStr(String year, String month, String day) {
        todayStr.set(year + "-" + month + "-" + day);
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
