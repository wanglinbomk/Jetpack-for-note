package com.example.lifeofnote.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeofnote.databinding.ItemTypeTitleBinding;

public class TitleHolder extends RecyclerView.ViewHolder {

    private ItemTypeTitleBinding binding;

    public TitleHolder(ItemTypeTitleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ItemTypeTitleBinding getBinding() {
        return binding;
    }
}
