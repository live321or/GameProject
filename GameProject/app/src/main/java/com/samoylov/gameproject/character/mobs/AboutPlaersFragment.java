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
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutPlaersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutPlaersFragment extends Fragment {
    private int hero;
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
            hero = i;
        } else {
            hero = 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        getName = (TextView) v.findViewById(R.id.name_Player);
        getLvl = (TextView) v.findViewById(R.id.lvl_Player);
        getName.setText(Data.bdHeros.get(hero).getName());

        getName.setText(Data.bdHeros.get(hero).getName() + Data.bdHeros.get(hero).getPosition());
        getLvl.setText("Уровень: " + (Data.bdHeros.get(hero).getLvl()));

        pGroup = v.findViewById(R.id.pGroup);
        if (Data.bdHeros.get(hero).getName().equals(Data.bdHeros.get(0).getName())) {
            pGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!Data.bdHeros.get(hero).isGroup()) {
                        Toast.makeText(getActivity().getApplicationContext(), "Вас пригласили в группу", Toast.LENGTH_LONG).show();
                        Data.bdInvite.add(Data.bdHeros.get(1).getPosition());
                    } else {
                        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Уже в группе", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            pGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!Data.bdHeros.get(0).isGroup() || Data.bdHeros.get(0).isGroupLider()) {
                        Toast.makeText(getActivity().getApplicationContext(), "Ты можешь пригласить", Toast.LENGTH_SHORT).show();
                        if (!Data.bdHeros.get(hero).isGroup()) {
                            Toast.makeText(getActivity().getApplicationContext(), "Добавлен", Toast.LENGTH_LONG).show();
                            Data.GroupList.add(Data.bdHeros.get(hero).getPosition());
                            Data.bdHeros.get(hero).setGroup(true);
                            Data.bdHeros.get(0).setGroup(true);
                            Data.bdHeros.get(0).setGroupLider(true);
                        } else {
                            Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Уже в группе", Toast.LENGTH_LONG).show();
                        }
                    } else
                        Toast.makeText(getActivity().getApplicationContext(), "Ты НЕ можешь пригласить", Toast.LENGTH_SHORT).show();


                }
            });
        }
        profileList = v.findViewById(R.id.list_Profile_Stat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        profileList.setLayoutManager(layoutManager);
        aboutProfileAdapter = new AboutProfileAdapter(Data.bdHeros.get(hero).heroStats);
        profileList.setAdapter(aboutProfileAdapter);
        return v;
    }

}
