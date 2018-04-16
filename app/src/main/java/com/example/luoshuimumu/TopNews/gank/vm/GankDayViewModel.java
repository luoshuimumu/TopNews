package com.example.luoshuimumu.TopNews.gank.vm;

import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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


/**
 * 注意区分关键数据和页面元素相关的数据
 * Created by luoshuimumu on 2017/11/9.
 */

public class GankDayViewModel extends BaseViewModel implements IGankDayViewModel {
    //存储数据的model
    GankStoreModel gankStoreModel;
    //当日日期
    public ObservableField<String> todayStr = new ObservableField();
    //天气情况
    public ObservableField<String> weatherStr = new ObservableField();

    //在google的demo里，控件的弹出、是由fragment控制的，而不是说全部由vm控制
    //vm只持有关键数据
    //展示日期选择框
    public ObservableField<Boolean> showDaySelection = new ObservableField();
    //adapter应该是可变的吗？
    public ObservableField<GankDayListAdapter> gankDayListAdapter = new ObservableField();

    public ObservableField<RecyclerView.LayoutManager> layoutManager = new ObservableField();

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

    public GankDayViewModel(RxFragment fragment, IGankDayCallbak callback) {
        super(fragment);
        topNewsApiHelper = new TopNewsApiHelper();

        mCallbak = callback;
        //TODO 初始化mGankStoreModel
        gankStoreModel = new GankStoreModel(mContext);
        //提前注册adaper
        GankDayListAdapter gankDayListAdapter = new GankDayListAdapter(mContext);
        this.gankDayListAdapter.set(gankDayListAdapter);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
        layoutManager.set(manager);

        //点击事件相关
        setDayItemListener((view, date) -> {
                    //解析这个日期
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
                    //通知外部点击事件
                    if (null != mCallbak) {
                        mCallbak.onUpdateListComplete(year, month, day);
                    }
                }
        );
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

    public void setDayItemListener(ListItemClickListenerMVVM<String> listener) {
        mDayClickListenerContainer.setDayItemListener(listener);
        gankDayListAdapter.get().setClickListenerContainer(mDayClickListenerContainer);
    }

    /**
     * 日期按钮点击事件，弹出日期选择框，点击后更新日期
     */
    public void onTodayBtnClick(View view) {
        if (null != gankDayListAdapter.get()
                && gankDayListAdapter.get().getItemCount() > 0) {
            showDaySelection.set(true);
        } else {
            //展示无日期
            showDaySelection.set(false);
        }
    }

    private void initData() {
        showDaySelection.set(false);
    }

    /**
     * 为日期字符串添加样式
     */
    private void decorateTodayStr(String year, String month, String day) {
        todayStr.set(year + "-" + month + "-" + day);
    }

    /**
     * TODO 为天气字符串添加样式
     */
    private void decorateWeatherStr(OnelistResp data) {
        weatherStr.set("stub");
    }

}
