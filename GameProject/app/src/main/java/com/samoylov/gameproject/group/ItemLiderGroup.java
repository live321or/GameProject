package com.samoylov.gameproject.group;

import androidx.recyclerview.widget.RecyclerView;

public class ItemLiderGroup implements ItemTypeGroup {
    @Override
    public int getItemViewType() {
        return ItemTypeGroup.LIDER;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        GroupViewHolderFactory.Lider_Holder liderHolder =(GroupViewHolderFactory.Lider_Holder) viewHolder;
    }
}
