package com.samoylov.gameproject.group;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samoylov.gameproject.R;

public class GroupViewHolderFactory {
    public static class Ne_Lider_Holder extends RecyclerView.ViewHolder {
        public Ne_Lider_Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class Lider_Holder extends RecyclerView.ViewHolder {
        public Lider_Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static RecyclerView.ViewHolder create(ViewGroup parent, boolean viewType) {
        if (!viewType) {

            View neLider = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.group_item, parent, false);
            return new Ne_Lider_Holder(neLider);
        }
        if(viewType) {

            View lider = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.group_lider_item, parent, false);
            return new Lider_Holder(lider);
        }else
            return null;
    }

}
