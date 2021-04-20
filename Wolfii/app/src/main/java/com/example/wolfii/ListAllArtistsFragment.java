package com.example.wolfii;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAllArtistsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<String> mesArtistes;
    private MyArtisteAdapter monAdapter;
    private ArrayList<Musique> musiques;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mes_artistes, container, false);

        // creation du recyclerview
        mRecyclerView = (RecyclerView) root.findViewById(R.id.myRecyclerView);
        mesArtistes = MainActivity.mesArtistes; // on recupere ici toutes les musiques sous forme d'un tableau

        monAdapter = new MyArtisteAdapter (mesArtistes);
        monAdapter.setmArtisteItemClickListener(new MyArtisteAdapter.ArtisteItemClickListener() {

            @Override
            public void onArtisteItemClick (View view, String artiste, int position) {

                    musiques = recuperer_musique (artiste);
                    mRecyclerView.setAdapter (new MyMusiqueAdapter (musiques, getActivity ()));

            }

            @Override
            public void onArtisteItemLongClick (View view, String artiste, int position) {

            }

        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayout.VERTICAL, false));
        mRecyclerView.setAdapter(monAdapter);

        return root;
    }
    private ArrayList<Musique> recuperer_musique(String artiste) {
        // on recupere toutes les musiques selon l'artiste qui nous interesse
        ArrayList<Musique> musiques = new ArrayList<> ();
        for(Musique m : MainActivity.maMusique) if (m.getAuthor().equals (artiste) ) musiques.add (m);
        return musiques;
    }
}