package com.samoylov.gameproject.group;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.R;


public class Group_Fragment extends Fragment implements AdapterGroupList.Update{

    private RecyclerView groupList;
    private AdapterGroupList adapterGroupList;

    public Group_Fragment() {
        // Required empty public constructor
    }


    public static Group_Fragment newInstance() {
        Group_Fragment fragment = new Group_Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.group, container, false);
        groupList=v.findViewById(R.id.group_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        groupList.setLayoutManager(layoutManager);
        Create();
        return v;
    }



    @Override
    public void onUpdate(int tag) {
        if (tag==0)
        adapterGroupList.notifyDataSetChanged();
        if (tag==1){

            Create();
            adapterGroupList.notifyDataSetChanged();
        }

    }
    private void Create(){
        if (!Data.bdHeros.get(0).isGroup()){
            adapterGroupList =new AdapterGroupList(Data.bdInvite,Data.bdHeros.get(0).isGroupLider(),Data.bdHeros.get(0).isGroup());
        }else
            adapterGroupList =new AdapterGroupList(Data.GroupList,Data.bdHeros.get(0).isGroupLider(),Data.bdHeros.get(0).isGroup());
        adapterGroupList.setOnUpdateListener(this);
        groupList.setAdapter(adapterGroupList);
    }
}