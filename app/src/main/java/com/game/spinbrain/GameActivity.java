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

    private Button btnGameRestart, btnGameSelectState;
    int state,currentState;
    SharedPreferences save;

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
        state = getIntent().getExtras().getInt("GameState");
    }

    private void setAction() {
        btnGameRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save = getSharedPreferences("Save", Context.MODE_PRIVATE);
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


    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportFragmentManager().beginTransaction().replace(R.id.game_state_fragment,
                (Fragment) GameStateFactory.getInstance().getGameState(state)).commit();

//        save = getSharedPreferences("Save", Context.MODE_PRIVATE);
//        currentState = save.getInt("CurrentState", 1);

    }

}
