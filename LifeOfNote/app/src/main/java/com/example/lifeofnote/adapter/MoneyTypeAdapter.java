package com.example.lifeofnote.adapter;

import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeofnote.BR;
import com.example.lifeofnote.R;
import com.example.lifeofnote.base.OnItemClickListener;
import com.example.lifeofnote.databinding.ItemTypeSelectBinding;
import com.example.lifeofnote.databinding.ItemTypeTitleBinding;
import com.example.lifeofnote.db.type.MoneyTypeEntity;
import com.example.lifeofnote.holder.TypeHolder;
import com.example.lifeofnote.holder.TitleHolder;

import java.util.List;

public class MoneyTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MoneyTypeEntity> datas;
    private final LayoutInflater mLayoutInflater;
    private OnItemClickListener onItemClickListener;

    public MoneyTypeAdapter(LayoutInflater inflater, List<MoneyTypeEntity> datas) {
        this.mLayoutInflater = inflater;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 2:
                ItemTypeTitleBinding titleBinding = DataBindingUtil.inflate(mLayoutInflater,
                        R.layout.item_type_title, parent, false);
                return new TitleHolder(titleBinding);
            default:
                ItemTypeSelectBinding selectBinding = DataBindingUtil.inflate(mLayoutInflater,
                        R.layout.item_type_select, parent, false);
                return new TypeHolder(selectBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleHolder) {
            ((TitleHolder) holder).getBinding().setVariable(BR.titleType, datas.get(position));
            ((TitleHolder) holder).getBinding().executePendingBindings();
        } else {
            ((TypeHolder) holder).getBinding().setVariable(BR.moneyType, datas.get(position));
            ((TypeHolder) holder).getBinding().executePendingBindings();
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }
}
