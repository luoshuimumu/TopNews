package com.example.luoshuimumu.TopNews.gank.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.luoshuimumu.TopNews.GankListContentBinding;
import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.gank.WebviewActivity;
import com.example.luoshuimumu.TopNews.gank.vm.GankListViewModel;
import com.example.luoshuimumu.TopNews.gank.widget.GankContentListAdapter;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * 头条新闻的内容的 fragment
 * Created by luoshuimumu on 2017/11/13.
 */

public class GankListContentFgm extends RxFragment {
    //业务交互vm
    GankListViewModel viewModel;


    public static GankListContentFgm newInstance(Bundle saveInstance) {
        GankListContentFgm fragment = new GankListContentFgm();
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
        GankListContentBinding binding = DataBindingUtil.inflate(inflater, R.layout.fgm_gank_list_content_layout, container, false);
        binding.setViewModel(viewModel);
        initRecyclerview(binding.fgmGankListRecyclerview);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getDayHistory();
        //读取本地数据 异步加载网络数据
    }

    private void initRecyclerview(RecyclerView recyclerView) {
        GankContentListAdapter gankContentListAdapter = new GankContentListAdapter(getActivity());
        initListItemClickListener(gankContentListAdapter);
        //TODO 设置点击事件，修改vm的 todayStr值
        gankContentListAdapter.setData(viewModel.gankContentList.get());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(gankContentListAdapter);
    }

    private void initListItemClickListener(GankContentListAdapter adapter) {
        //喜欢按钮
        adapter.setLikeClickListener((view, gankContent) -> {
            Toast.makeText(getActivity(), "点击喜欢:" + gankContent.getDesc(), Toast.LENGTH_SHORT).show();
        });
        //分享按钮
        adapter.setShareClickListener((view, gankContent) -> {
            Toast.makeText(getActivity(), "点击分享:" + gankContent.getDesc(), Toast.LENGTH_SHORT).show();
        });
        //item点击
        adapter.setContentClickListener((view, gankContent) -> {
            viewModel.onContentSelected(gankContent);
            //短按直接从app的打开网页
            //TODO 跳转到webview
            if (!TextUtils.isEmpty(gankContent.getUrl())) {
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                Uri uri = Uri.parse(gankContent.getUrl());
                intent.setData(uri);
                startActivity(intent);
            }
        });
        //长按气泡
        adapter.setLongClickClickListener((view, gankContent) -> {
            //TODO 弹出选择框 允许选择外部浏览器打开
            Toast.makeText(getActivity(), "弹出气泡框选择外部浏览器打开安卓文章详情:" + gankContent.getDesc(), Toast.LENGTH_SHORT).show();

//            if (!TextUtils.isEmpty(gankContent.getUrl())) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri uri = Uri.parse(gankContent.getUrl());
//                intent.setData(uri);
//                startActivity(intent);
//            }
            return true;
        });
    }


    public GankListViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(GankListViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
