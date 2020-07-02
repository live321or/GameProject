package com.samoylov.gameproject.group;


import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.character.heroes.Hero;

import java.util.ArrayList;
import java.util.List;

public class AdapterGroupList extends RecyclerView.Adapter {

    private ArrayList<Hero> groupList;
    private boolean isLider,isGroup;

    public interface Update{
        void onUpdate(int tag);
    }
    private static Update onUpdate;

    public AdapterGroupList(ArrayList<Hero> dataSet,boolean a,boolean isGroup) {
        this.groupList = dataSet;
        this.isLider=a;
        this.isGroup=isGroup;
    }

    public void setOnUpdateListener(Update listener){
        onUpdate=listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GroupViewHolderFactory.create(parent,isGroup, isLider);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if(!isGroup){
            GroupViewHolderFactory.InviteHolder inviteHolder =
                    (GroupViewHolderFactory.InviteHolder  ) viewHolder;

            inviteHolder.name.setText(groupList.get(position).getName());
            inviteHolder.yes.setOnClickListener(v -> {
                Data.bdInvite.remove(position);
                Data.bdHeros.get(0).setGroup(true);
                Data.GroupList.add(Data.bdHeros.get(0));
                Data.GroupList.add(Data.bdHeros.get(1));
                onUpdate.onUpdate(1);
            });
            inviteHolder.no.setOnClickListener(v -> {
                Data.bdInvite.remove(position);
                onUpdate.onUpdate(0);
            });
        }else if(!isLider){
            GroupViewHolderFactory.Ne_Lider_Holder holder =
                    (GroupViewHolderFactory.Ne_Lider_Holder) viewHolder;
            holder.name.setText(groupList.get(position).getName());
            holder.hp.setText(""+groupList.get(position).getHp());


        }else {
            GroupViewHolderFactory.Lider_Holder liderHolder =
                    (GroupViewHolderFactory.Lider_Holder) viewHolder;
            liderHolder.name.setText(groupList.get(position).getName());
            liderHolder.hp.setText(""+groupList.get(position).getHp());
            liderHolder.gKikL.setOnClickListener(v -> {

                for (int i = 0; i <Data.bdHeros.size(); i++) {
                    if (groupList.get(position).getName().equals(Data.bdHeros.get(i).getName())) {
                        Data.bdHeros.get(i).setGroup(false);
                        Data.GroupList.remove(groupList.get(position));
                        i=Data.bdHeros.size();
                        onUpdate.onUpdate(0);
                    }
                }

            });

        }
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }


}
