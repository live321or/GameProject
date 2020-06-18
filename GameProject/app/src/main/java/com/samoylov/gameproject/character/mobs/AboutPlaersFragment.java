package com.samoylov.gameproject.character.mobs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.World;
import com.samoylov.gameproject.character.heroes.Hero;
import com.samoylov.gameproject.character.heroes.HeroStat;
import com.samoylov.gameproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutPlaersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutPlaersFragment extends Fragment {
    private Hero hero;
    private ArrayList<HeroStat> gg;
    TextView getName, getLvl;
    RecyclerView profileList;
    AboutProfileAdapter aboutProfileAdapter;
    private int i = 0;
    private Button pGroup, pFriend, pMes;

    public static AboutPlaersFragment newInstance(int i) {
        AboutPlaersFragment fragment = new AboutPlaersFragment();
        fragment.i = i;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (i != 0) {
            hero = Data.bdHeros.get(i);
        } else {
            hero = Data.bdHeros.get(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        getName = (TextView) v.findViewById(R.id.name_Player);
        getLvl = (TextView) v.findViewById(R.id.lvl_Player);
        getName.setText(hero.getName());

        getName.setText(hero.getName());
        getLvl.setText("Уровень: " + Double.toString(hero.getLvl()));

        pGroup = v.findViewById(R.id.pGroup);
        pGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Data.bdHeros.get(0).isGroup()||Data.bdHeros.get(0).isGroupLider())
                    Toast.makeText(getActivity().getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
                if (!hero.isGroup()) {
                    Toast.makeText(getActivity().getApplicationContext(),"3",Toast.LENGTH_LONG).show();
                    Data.GroupList.add(hero);
                    hero.setGroup(true);
                    Data.bdHeros.get(0).setGroup(true);
                    Data.bdHeros.get(0).setGroupLider(true);
                }
            }
        });

        profileList = v.findViewById(R.id.list_Profile_Stat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        profileList.setLayoutManager(layoutManager);
        aboutProfileAdapter = new AboutProfileAdapter(hero.heroStats);
        profileList.setAdapter(aboutProfileAdapter);
        return v;
    }

}
