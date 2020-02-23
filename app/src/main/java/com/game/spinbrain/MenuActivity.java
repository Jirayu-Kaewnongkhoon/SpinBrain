package com.game.spinbrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    private Button btnStart, btnSelectState;
    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bindView(); //to setup view
        setAction(); //to setup action of view

    }

    private void bindView() {
        btnStart = (Button) findViewById(R.id.btnStart);
        btnSelectState = (Button) findViewById(R.id.btnSelectState);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);
    }

    private void setAction() {

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, GameActivity.class));
            }
        });

        btnSelectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, StateActivity.class));
            }
        });

    }
}
