package com.game.spinbrain.GameStateFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.game.spinbrain.R;


public class GameFragment3 extends Fragment {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    SharedPreferences save;
    SharedPreferences.Editor save_editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game3, container, false);

        //get state info
        sp = getActivity().getSharedPreferences("GameStateFragment3", Context.MODE_PRIVATE);
        editor = sp.edit();

        //set current state
        save = getActivity().getSharedPreferences("Save", Context.MODE_PRIVATE);
        save_editor = save.edit();
        save_editor.putInt("CurrentState", sp.getInt("StateLevel", 0));
        save_editor.commit();

        return view;
    }


}
