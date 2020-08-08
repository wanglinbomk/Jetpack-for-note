package com.example.lifeofnote.base;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author : Boggle
 * e-mail : 1105059963@qq.com
 * date   : 2019-11-2516:51
 * desc   :
 * version: 1.0
 */
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T mBinding;

    public BindingViewHolder(@NonNull T itemView) {
        super(itemView.getRoot());
        mBinding = itemView;
    }

    public T get(){
        return mBinding;
    }
}
