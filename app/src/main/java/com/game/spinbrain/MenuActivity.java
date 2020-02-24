package com.game.spinbrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    private Button btnStart, btnSelectState;
    private ImageView ivLogo;
    SharedPreferences save;
    int current_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnSelectState = (Button) findViewById(R.id.btnSelectState);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                intent.putExtra("GameState", current_state);
                startActivity(intent);
            }
        });

        btnSelectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, StateActivity.class));
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        save = getSharedPreferences("Save", Context.MODE_PRIVATE);
        current_state = save.getInt("CurrentState", 1);
    }
}
