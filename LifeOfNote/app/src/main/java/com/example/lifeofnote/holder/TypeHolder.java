package com.example.lifeofnote.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeofnote.databinding.ItemTypeSelectBinding;

public class TypeHolder extends RecyclerView.ViewHolder {

    private ItemTypeSelectBinding binding;

    public TypeHolder(ItemTypeSelectBinding itemTypeSelectBinding) {
        super(itemTypeSelectBinding.getRoot());
        this.binding = itemTypeSelectBinding;
    }

    public ItemTypeSelectBinding getBinding() {
        return binding;
    }
}
