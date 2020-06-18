package com.samoylov.gameproject.group;

import androidx.recyclerview.widget.RecyclerView;

public class ItemGroup implements ItemTypeGroup {
    @Override
    public int getItemViewType() {
        return ItemTypeGroup.NE_LIDER;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        GroupViewHolderFactory.Ne_Lider_Holder neLiderHolder =(GroupViewHolderFactory.Ne_Lider_Holder) viewHolder;

    }
}
