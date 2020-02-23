package com.game.spinbrain.GameStateFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.game.spinbrain.R;


public class GameFragment1 extends Fragment /*implements GameStateInterface*/ {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private Button btnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game1, container, false);

        btnNext = (Button) view.findViewById(R.id.btnNext1);
        sp = getActivity().getSharedPreferences("GameStateFragment1", Context.MODE_PRIVATE);
        editor = sp.edit();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isPass", true);
                editor.commit();
                getFragmentManager().beginTransaction().replace(R.id.game_state_fragment,
                        (Fragment) GameStateFactory.getInstance().getGameState(2)).commit();
            }
        });

        return  view;
    }






}
