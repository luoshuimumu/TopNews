package com.example.luoshuimumu.TopNews.gank.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luoshuimumu.TopNews.ItemGankDayBinding;
import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.gank.GankDayClickListenerContainer;
import com.example.luoshuimumu.TopNews.gank.vm.BaseGankDayVM;
import com.example.luoshuimumu.TopNews.widget.ListItemClickListenerMVVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布gank的历史日期
 * Created by luoshuimumu on 2018/2/2.
 */

public class GankDayListAdapter extends RecyclerView.Adapter<GankDayListAdapter.BaseGankHolder> {

    private Context mContext;
    private List<String> data = new ArrayList();

    private GankDayClickListenerContainer mClickListenerContainer
            = new GankDayClickListenerContainer();

    private ListItemClickListenerMVVM<String> mItemClickListenerMVVM;

    public GankDayListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> data) {
        this.data = data;
    }


    @Override
    public BaseGankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ItemGankDayBinding contentBinding = DataBindingUtil.inflate(inflater, R.layout.item_gank_day, parent, false);
        return new DayHolder(contentBinding);
    }

    @Override
    public void onBindViewHolder(BaseGankHolder holder, int position) {
        String gankday = data.get(position);
        BaseGankDayVM vm = new BaseGankDayVM();
        vm.setDay(gankday);
        vm.setListenerContainer(mClickListenerContainer);
        holder.bind(vm);
    }


    @Override
    public int getItemCount() {
        return null == data ? 0 : data.size();
    }


    /**********             业务  item             *************/
    class DayHolder extends BaseGankHolder {
        ItemGankDayBinding mBinding;

        public DayHolder(ItemGankDayBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void bind(BaseGankDayVM vm) {
            if (null != mBinding) {
                mBinding.setVm(vm);
            }
        }
    }


    public ListItemClickListenerMVVM<String> getItemClickListenerMVVM() {
        return mItemClickListenerMVVM;
    }

    public void setItemClickListenerMVVM(ListItemClickListenerMVVM<String> itemClickListenerMVVM) {
        mItemClickListenerMVVM = itemClickListenerMVVM;
        mClickListenerContainer.setDayItemListener(mItemClickListenerMVVM);
    }
/**********           base  item             *************/
    /**
     * 封装点击按钮，like按钮，转发按钮等
     */
    abstract class BaseGankHolder extends RecyclerView.ViewHolder {
        //TODO 应该有个item，类似vm的角色让view绑定
        BaseGankDayVM data;
//        View m

        /**
         * 在子类声明实例成员 xxxBinding，然后阻碍这里绑定
         *
         * @param vm
         */
        protected abstract void bind(BaseGankDayVM vm);

        public BaseGankHolder(View itemView) {
            super(itemView);
        }

    }
}
