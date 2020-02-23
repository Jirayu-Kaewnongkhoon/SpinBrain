package com.game.spinbrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.game.spinbrain.GameStateFragment.GameStateFactory;

public class GameActivity extends AppCompatActivity {

    private Button /*btnGameBack,*/ btnGameSelectState;
    private Fragment fragment;
    int state;
    GameStateFactory gameState;
    Fragment game;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bind();
        setAction();

    }

    private void bind() {
//        btnGameBack = (Button) findViewById(R.id.btnGameBack);
        btnGameSelectState = (Button) findViewById(R.id.btnGameSelectState);
        state = getIntent().getExtras().getInt("GameState");
        //sp = getSharedPreferences("GameStateFragment", Context.MODE_PRIVATE);
    }

    private void setAction() {
        /*btnGameBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

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

        gameState = GameStateFactory.getInstance();
        game = (Fragment) gameState.getGameState(state);

        getSupportFragmentManager().beginTransaction().replace(R.id.game_state_fragment, game).commit();

    }

}
