package com.game.spinbrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.game.spinbrain.GameStateFragment.GameStateFactory;

public class GameActivity extends AppCompatActivity {

    private Button btnGameRestart, btnGameSelectState, btnSkip;
    int state,currentState;
    SharedPreferences save;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bind();
        setAction();

    }

    private void bind() {
        btnGameRestart = (Button) findViewById(R.id.btnGameRestart);
        btnGameSelectState = (Button) findViewById(R.id.btnGameSelectState);
        btnSkip = (Button) findViewById(R.id.btnSkip);
        state = getIntent().getExtras().getInt("GameState");
        save = getSharedPreferences("Save", Context.MODE_PRIVATE);
        editor = save.edit();

    }

    private void setAction() {
        btnGameRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentState = save.getInt("CurrentState", 0);
                getSupportFragmentManager().beginTransaction().replace(R.id.game_state_fragment,
                        (Fragment) GameStateFactory.getInstance().getGameState(currentState)).commit();
            }
        });

        btnGameSelectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameActivity.this, StateActivity.class));
                finish();
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentState = save.getInt("CurrentState", 0);
                if(save.getInt("CheckPoint", 1) == currentState) {
                    SharedPreferences state = getSharedPreferences("GameStateFragment"+currentState, Context.MODE_PRIVATE);
                    SharedPreferences.Editor state_editor = state.edit();
                    state_editor.putBoolean("isPass", true);
                    state_editor.commit();

                    editor.putInt("CheckPoint", ++currentState);
                    editor.commit();
                } else ++currentState;
                getSupportFragmentManager().beginTransaction().replace(R.id.game_state_fragment,
                        (Fragment) GameStateFactory.getInstance().getGameState(currentState)).commit();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportFragmentManager().beginTransaction().replace(R.id.game_state_fragment,
                (Fragment) GameStateFactory.getInstance().getGameState(state)).commit();


    }

}
