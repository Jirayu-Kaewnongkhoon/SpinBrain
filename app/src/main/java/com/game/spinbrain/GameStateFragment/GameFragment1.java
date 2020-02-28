package com.game.spinbrain.GameStateFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.game.spinbrain.R;


public class GameFragment1 extends Fragment {

    SharedPreferences sp;
    SharedPreferences save;
    SharedPreferences.Editor game_editor;
    SharedPreferences.Editor save_editor;
    private Button btnNext,btnSetText;
    private TextView text;
    int current_state;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game1, container, false);

        btnNext = (Button) view.findViewById(R.id.btnNext1);
        text = (TextView) view.findViewById(R.id.tvInFragment1);
        btnSetText = (Button) view.findViewById(R.id.setText);
        sp = getActivity().getSharedPreferences("GameStateFragment1", Context.MODE_PRIVATE);
        game_editor = sp.edit();

        save = getActivity().getSharedPreferences("Save", Context.MODE_PRIVATE);
        save_editor = save.edit();
        save_editor.putInt("CurrentState", 1);
        save_editor.commit();


        btnSetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Hello Another World!");
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sp.getBoolean("isPass", false) == false) {
                    game_editor.putBoolean("isPass", true);
                    game_editor.commit();

                    save_editor.putInt("CheckPoint", 2);
                    save_editor.commit();
                }
                getFragmentManager().beginTransaction().replace(R.id.game_state_fragment,
                        (Fragment) GameStateFactory.getInstance().getGameState(2)).commit();
            }
        });

        return  view;
    }






}
