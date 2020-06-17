package com.samoylov.gameproject.group;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samoylov.gameproject.R;


public class Group extends Fragment {

    private RecyclerView groupList;

    public Group() {
        // Required empty public constructor
    }


    public static Group newInstance() {
        Group fragment = new Group();
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
        return v;
    }
}