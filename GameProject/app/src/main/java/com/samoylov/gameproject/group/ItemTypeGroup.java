package com.samoylov.gameproject.group;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemTypeGroup {
    int NE_LIDER=0;
    int LIDER=1;

    int getItemViewType();

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder);
}
