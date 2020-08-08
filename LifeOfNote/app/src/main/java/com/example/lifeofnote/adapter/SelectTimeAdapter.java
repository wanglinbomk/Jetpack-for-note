package com.example.lifeofnote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeofnote.BR;
import com.example.lifeofnote.R;
import com.example.lifeofnote.base.BindingViewHolder;
import com.example.lifeofnote.base.OnItemClickListener;
import com.example.lifeofnote.db.time.SelectTimeEntity;

import java.util.List;

public class SelectTimeAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<SelectTimeEntity> datas;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SelectTimeAdapter(List<SelectTimeEntity> datas, LayoutInflater inflater) {
        this.datas = datas;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_time_select, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, final int position) {
        holder.get().setVariable(BR.timeSelect, datas.get(position));
        holder.get().executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
