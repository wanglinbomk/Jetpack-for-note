package com.example.lifeofnote.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.lifeofnote.R;
import com.example.lifeofnote.entity.CratePayEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CreatePayAdapter extends BaseQuickAdapter<CratePayEntity, BaseViewHolder> {

    private boolean isPay;

    public CreatePayAdapter(int layoutResId, @Nullable List<CratePayEntity> data, boolean isPay) {
        super(layoutResId, data);
        this.isPay = isPay;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CratePayEntity cratePayEntity) {
        baseViewHolder.setText(R.id.tv_name, cratePayEntity.getName());
        if (cratePayEntity.isSelect()) {
            if(isPay){
                baseViewHolder.setBackgroundResource(R.id.cl_all, R.drawable.item_select)
                        .setBackgroundResource(R.id.iv_icon, cratePayEntity.getIconSelect());
            }else {
                baseViewHolder.setBackgroundResource(R.id.cl_all, R.drawable.bg_tv_today_change)
                        .setBackgroundResource(R.id.iv_icon, cratePayEntity.getIconSelect());
            }
        } else {
            baseViewHolder.setBackgroundResource(R.id.cl_all, R.color.colorWhile)
                    .setBackgroundResource(R.id.iv_icon, cratePayEntity.getIcon());
        }
    }
}
