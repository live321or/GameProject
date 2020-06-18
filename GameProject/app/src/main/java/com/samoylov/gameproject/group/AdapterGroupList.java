package com.samoylov.gameproject.group;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samoylov.gameproject.character.heroes.Hero;

import java.util.ArrayList;
import java.util.List;

public class AdapterGroupList extends RecyclerView.Adapter {
    private ArrayList<ItemTypeGroup> dataSet;
    private ArrayList<Hero> groupList;
    private boolean a;

    public AdapterGroupList(ArrayList<Hero> dataSet,boolean a) {
        this.groupList = dataSet;
        this.a=a;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GroupViewHolderFactory.create(parent, a);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(!a){
            GroupViewHolderFactory.Ne_Lider_Holder neLiderHolder =
                    (GroupViewHolderFactory.Ne_Lider_Holder) viewHolder;
        }else {
            GroupViewHolderFactory.Lider_Holder liderHolder =
                    (GroupViewHolderFactory.Lider_Holder) viewHolder;

        }
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }


}
