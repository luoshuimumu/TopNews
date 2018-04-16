package com.example.luoshuimumu.TopNews.gank.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.luoshuimumu.TopNews.GankDayBinding;
import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.gank.vm.GankDayViewModel;
import com.example.luoshuimumu.TopNews.utils.LogUtil;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * 可以在这里选择日期，从而修改另一个fgm展示的gankio数据
 * Created by luoshuimumu on 2017/11/13.
 */

public class GankTitleFgm extends RxFragment implements GankDayViewModel.IGankDayCallbak {

    GankDayViewModel viewModel;

    private RecyclerView recyclerview;
    private ImageView ivTitle;
    private View vNodata;

    @Override
    public void onUpdateListComplete(String year, String month, String day) {
        //先改变自身控件的状态，如果点击的时候list是隐藏的，那么也应该隐藏
        //TODO 唤起另一个fragment（其中定义了接口）
        try {
            ((GankDayViewModel.IGankDayCallbak) getActivity()).onUpdateListComplete(year, month, day);
        } catch (ClassCastException e) {
            LogUtil.e(GankTitleFgm.class.getSimpleName(), "getActivity cast to " + GankDayViewModel.IGankDayCallbak.class.getSimpleName() + " error");
        }
    }

    public static GankTitleFgm newInstance(Bundle saveInstance) {
        GankTitleFgm fragment = new GankTitleFgm();
        if (null != saveInstance) {
            fragment.setArguments(saveInstance);
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        viewModel = new GankDayViewModel(this, this);
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
        initTitleClickListener(root);
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
    private void initTitleClickListener(View view) {
        recyclerview = view.findViewById(R.id.fgm_gank_day_title_recyclerview);
        vNodata = view.findViewById(R.id.fgm_gank_day_title_fl_nodata);
        ivTitle = view.findViewById(R.id.fgm_gank_day_title_iv);

        view.findViewById(R.id.fgm_gank_day_title_ll).setOnClickListener((clickView) -> {
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

}
