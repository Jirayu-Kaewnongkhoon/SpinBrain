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


public class GameFragment2 extends Fragment {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private Button btnNext;
    int current_state;
    SharedPreferences save;
    SharedPreferences.Editor save_editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game2, container, false);

        btnNext = view.findViewById(R.id.btnNext2);

        sp = getActivity().getSharedPreferences("GameStateFragment2", Context.MODE_PRIVATE);
        editor = sp.edit();

        save = getActivity().getSharedPreferences("Save", Context.MODE_PRIVATE);
        save_editor = save.edit();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isPass", true);
                editor.commit();

                save_editor.putInt("CurrentState", 3);
                save_editor.commit();


                getFragmentManager().beginTransaction().replace(R.id.game_state_fragment,
                        (Fragment) GameStateFactory.getInstance().getGameState(3)).commit();
            }
        });


        return view;
    }

}
