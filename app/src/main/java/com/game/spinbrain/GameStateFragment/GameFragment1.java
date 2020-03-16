package com.game.spinbrain.GameStateFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.game.spinbrain.R;

import java.util.Timer;
import java.util.TimerTask;


public class GameFragment1 extends Fragment {

    SharedPreferences sp;
    SharedPreferences save;
    SharedPreferences.Editor game_editor;
    SharedPreferences.Editor save_editor;

    private Handler handler = new Handler();
    private final static long Interval = 30;//กำหนดเวลา refresh
    private Button minus,add,btnConfirm;
    private TextView result;
    private int Count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game1, container, false);

        minus =  view.findViewById(R.id.minusBtn);
        add = view.findViewById(R.id.addBtn);
        result = view.findViewById(R.id.resultText);

        btnConfirm = view.findViewById(R.id.btnConfirm);

        //get state info
        sp = getActivity().getSharedPreferences("GameStateFragment1", Context.MODE_PRIVATE);
        game_editor = sp.edit();

        //set current state
        save = getActivity().getSharedPreferences("Save", Context.MODE_PRIVATE);
        save_editor = save.edit();
        save_editor.putInt("CurrentState", sp.getInt("StateLevel", 0));
        save_editor.commit();

        //Test Loop
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        checkWin();
                    }
                });
            }
        },0 ,Interval);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Count++;
                result.setText(Integer.toString(Count));
                //checkWin();


            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Count > 0){
                    Count--;
                }

                result.setText(Integer.toString(Count));
            }
        });



        return  view;
    }

    public void checkWin(){
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(result.getText().toString()) == 7){
                    Toast.makeText(getContext(), "You Win", Toast.LENGTH_SHORT).show();
                    //if is 1st pass, will set isPass = true & set new checkpoint
                    if(sp.getBoolean("isPass", false) == false) {
                        game_editor.putBoolean("isPass", true);
                        game_editor.commit();

                        save_editor.putInt("CheckPoint", 2);
                        save_editor.commit();
                    }
                    getFragmentManager().beginTransaction().replace(R.id.game_state_fragment,
                            (Fragment) GameStateFactory.getInstance().getGameState(2)).commit();
                }
            }
        });
    }




}
