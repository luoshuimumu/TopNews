package com.example.luoshuimumu.TopNews.gank.vm;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.example.luoshuimumu.TopNews.base.BaseViewModel;
import com.example.luoshuimumu.TopNews.gank.GankDayClickListenerContainer;
import com.example.luoshuimumu.TopNews.gank.entity.response.OnelistResp;
import com.example.luoshuimumu.TopNews.gank.model.GankStoreModel;
import com.example.luoshuimumu.TopNews.gank.widget.GankDayListAdapter;
import com.example.luoshuimumu.TopNews.net.service.TopNewsApiHelper;
import com.example.luoshuimumu.TopNews.widget.ListItemClickListenerMVVM;
import com.trello.rxlifecycle2.components.RxFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.http.HEAD;


/**
 * 注意区分关键数据和页面元素相关的数据
 * Created by luoshuimumu on 2017/11/9.
 */

public class GankDayViewModel extends BaseViewModel implements IGankDayViewModel {
    //存储数据的model
    GankStoreModel gankStoreModel;
    //当日日期
    public ObservableField<String> todayStr = new ObservableField();
    //当日日期，用于显示的样式
    public ObservableField<String> todaySpanStr = new ObservableField();
    //天气情况
    public ObservableField<String> weatherStr = new ObservableField();

    //在google的demo里，控件的弹出、是由fragment控制的，而不是说全部由vm控制
    //vm只持有关键数据
    //展示日期选择框
    public ObservableField<Boolean> showDaySelection = new ObservableField();

    //model就用于保存实体类
    private GankDayClickListenerContainer mDayClickListenerContainer
            = new GankDayClickListenerContainer();

    private IGankDayCallbak mCallbak;
    private TopNewsApiHelper topNewsApiHelper;

    /**
     * TODO 要想从vm->fgm->another fgm->another model 上面四个东西都要定义接口，真的是很麻烦，不如用广播？
     */
    public interface IGankDayCallbak {
        void onUpdateListComplete(String year, String month, String day);
    }

    public GankDayViewModel(RxFragment fragment) {
        super(fragment);
        topNewsApiHelper = new TopNewsApiHelper();

        //TODO 初始化mGankStoreModel
        gankStoreModel = new GankStoreModel(mContext);

        initTodayStrCallback();
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

    @Override
    public void onDaySelected(String date) {
        //请求
        todayStr.set(date);
    }

    /**
     * 为日期字符串添加样式
     */
    private void decorateTodayStr(String year, String month, String day) {
        todaySpanStr.set(year + "-" + month + "-" + day);
    }

    /**
     * TODO 为天气字符串添加样式
     */
    private void decorateWeatherStr(OnelistResp data) {
        weatherStr.set("stub");
    }

    public IGankDayCallbak getCallbak() {
        return mCallbak;
    }

    public void setCallbak(IGankDayCallbak callbak) {
        mCallbak = callbak;
    }
}
