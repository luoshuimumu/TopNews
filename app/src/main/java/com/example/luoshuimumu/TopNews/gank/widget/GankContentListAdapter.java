package com.example.luoshuimumu.TopNews.gank.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luoshuimumu.TopNews.ItemGankContentBinding;
import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.gank.GankItemClickListenerContainer;
import com.example.luoshuimumu.TopNews.gank.vm.BaseGankItemVM;
import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;
import com.example.luoshuimumu.TopNews.widget.ListItemClickListenerMVVM;
import com.example.luoshuimumu.TopNews.widget.ListItemLongClickListenerMVVM;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 0208策略需要改一下 有图片的显示图片 链接的允许跳转链接 无连接有图片的显示大图
 * 所有的判断以类型为前提
 * Created by luoshuimumu on 2018/2/2.
 */

public class GankContentListAdapter extends RecyclerView.Adapter<GankContentListAdapter.BaseGankHolder> {

    public final int TYPE_CONTENT = 1;
    public final int TYPE_DIVISION = 2;

    private Context mContext;
    private List<GankContent> data = new ArrayList();

    private GankItemClickListenerContainer mClickListenerContainer
            = new GankItemClickListenerContainer();

    public GankContentListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<GankContent> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return
                TextUtils.isEmpty(data.get(position).getType()) ?
                        TYPE_DIVISION : TYPE_CONTENT;
//        switch (data.get(position).getType()) {
//            case ContentCategory.ValueRestVideo:
//                return ContentCategory.CodeRestVideo;
//            case ContentCategory.ValueFrontEnd:
//                return ContentCategory.CodeFrontEnd;
//            case ContentCategory.ValueIos:
//                return ContentCategory.CodeIos;
//            case ContentCategory.ValueExtendResource:
//                return ContentCategory.CodeExtendResource;
//            case ContentCategory.ValueAndroid:
//                return ContentCategory.CodeAndroid;
//            case ContentCategory.ValueWelfare:
//                return ContentCategory.CodeWelfare;
//        }
//        return super.getItemViewType(position);
    }

    @Override
    public BaseGankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        switch (viewType) {
            case TYPE_DIVISION:
                return null;
            case TYPE_CONTENT:

                ItemGankContentBinding contentBinding = DataBindingUtil.inflate(inflater, R.layout.item_gank_content, parent, false);
                return new ContentHolder(contentBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseGankHolder holder, int position) {
        GankContent gankContent = data.get(position);
        BaseGankItemVM vm = new BaseGankItemVM();
        vm.setData(gankContent);
        vm.setListenerContainer(mClickListenerContainer);
        holder.bind(vm);
    }


    @Override
    public int getItemCount() {
        return null == data ? 0 : data.size();
    }


    public void setContentClickListener(ListItemClickListenerMVVM<GankContent> listener) {
        mClickListenerContainer.setContentListener(listener);
    }

    public void setLikeClickListener(ListItemClickListenerMVVM<GankContent> listener) {
        mClickListenerContainer.setLikeListener(listener);
    }

    public void setShareClickListener(ListItemClickListenerMVVM<GankContent> listener) {
        mClickListenerContainer.setShareListener(listener);
    }

    public void setLongClickClickListener(ListItemLongClickListenerMVVM<GankContent> listener) {
        mClickListenerContainer.setLongClickListener(listener);
    }


    /**********             业务  item             *************/
    class ContentHolder extends BaseGankHolder {
        ItemGankContentBinding mBinding;

        public ContentHolder(ItemGankContentBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void bind(BaseGankItemVM vm) {
            if (null != mBinding) {
                mBinding.setVm(vm);
                mBinding.executePendingBindings();
            }
        }
    }

    /**********           base  item             *************/
    /**
     * 封装点击按钮，like按钮，转发按钮等
     */
    abstract class BaseGankHolder extends RecyclerView.ViewHolder {
        //TODO 应该有个item，类似vm的角色让view绑定
        BaseGankItemVM data;
//        View m

        /**
         * 在子类声明实例成员 xxxBinding，然后阻碍这里绑定
         *
         * @param vm
         */
        protected abstract void bind(BaseGankItemVM vm);

        public BaseGankHolder(View itemView) {
            super(itemView);
        }

    }
}
