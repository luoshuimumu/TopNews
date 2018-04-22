package com.example.luoshuimumu.TopNews.gank.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.luoshuimumu.TopNews.GankDayBinding;
import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.gank.vm.GankListViewModel;
import com.example.luoshuimumu.TopNews.gank.widget.GankDayListAdapter;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * 可以在这里选择日期，从而修改另一个fgm展示的gankio数据
 * Created by luoshuimumu on 2017/11/13.
 */

public class GankTitleFgm extends RxFragment {

    private GankListViewModel viewModel;

    private RecyclerView recyclerview;
    private ImageView ivTitle;
    private View vTitle;
    private View vNodata;


    public static GankTitleFgm newInstance(Bundle saveInstance) {
        GankTitleFgm fragment = new GankTitleFgm();
        if (null != saveInstance) {
            fragment.setArguments(saveInstance);
        }
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        GankDayBinding binding = DataBindingUtil.inflate(inflater, R.layout.fgm_top_news_title_layout, container, false);
        binding.setViewModel(viewModel);
        View root = binding.getRoot();
        initView(root);
        initTitleClickListener();
        initRecyclerview(binding.fgmGankDayTitleRecyclerview);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getDayHistory();
    }

    /**
     * 页面控件的弹出、响应事件应该是由view控制的，否则要靠vm和xml完成这些逻辑，真的很复杂
     */
    private void initTitleClickListener() {
        vTitle.setOnClickListener((clickView) -> {
            //TODO vNodata和recyclerview是互斥的显示状态
            //点击title会响应
            if (View.VISIBLE != recyclerview.getVisibility()
                    && View.VISIBLE != vNodata.getVisibility()
                    ) {
                //都不可见时，选一个显示

                if (recyclerview.getAdapter().getItemCount() < 1) {
                    //list无数据
                    vNodata.setVisibility(View.VISIBLE);
                } else {
                    recyclerview.setVisibility(View.VISIBLE);
                }
            } else {
                //将可见的那个设置为消失
                if (View.VISIBLE == vNodata.getVisibility()) {
                    vNodata.setVisibility(View.GONE);
                }
                if (View.VISIBLE == recyclerview.getVisibility()) {
                    recyclerview.setVisibility(View.GONE);
                }
            }

            if (null != ivTitle) {
                //TODO 反转图片
                ivTitle.setScaleY(-1);
            }
        });
    }

    private void initRecyclerview(RecyclerView recyclerView) {
        GankDayListAdapter gankDayListAdapter = new GankDayListAdapter(getActivity());
        //TODO 设置点击事件，修改vm的 todayStr值
        gankDayListAdapter.setItemClickListenerMVVM((view, dayStr) -> {
            viewModel.onDaySelected(dayStr);
            //使列表消失
            recyclerView.setVisibility(View.GONE);
        });
        gankDayListAdapter.setData(viewModel.gankDayList.get());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(gankDayListAdapter);
    }

    private void initView(View view) {
        recyclerview = view.findViewById(R.id.fgm_gank_day_title_recyclerview);
        vNodata = view.findViewById(R.id.fgm_gank_day_title_fl_nodata);
        ivTitle = view.findViewById(R.id.fgm_gank_day_title_iv);
        vTitle = view.findViewById(R.id.fgm_gank_day_title_ll);
    }

    public GankListViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(GankListViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
