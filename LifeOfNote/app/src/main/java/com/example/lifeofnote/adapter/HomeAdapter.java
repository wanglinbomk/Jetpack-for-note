package com.example.lifeofnote.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeofnote.BR;
import com.example.lifeofnote.R;
import com.example.lifeofnote.base.BindingItemClick;
import com.example.lifeofnote.base.BindingViewHolder;
import com.example.lifeofnote.db.create.CreateEntity;
import com.example.lifeofnote.db.create.CreateGroupEntity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<CreateGroupEntity> datas;
    private final LayoutInflater inflater;
    private BindingItemClick bindingItemClick;

    public HomeAdapter(List<CreateGroupEntity> datas, LayoutInflater inflater) {
        this.datas = datas;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_create,parent,false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, final int position) {
        holder.get().setVariable(BR.createGroupEntity, datas.get(position));
        holder.get().executePendingBindings();
        holder.itemView.setOnClickListener(v -> bindingItemClick.onBindingItemCLick(datas.get(position)));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setBindingItemClick(BindingItemClick bindingItemClick) {
        this.bindingItemClick = bindingItemClick;
    }
}
