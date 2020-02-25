package com.game.spinbrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.spinbrain.GameStateFragment.GameStateFactory;

import java.util.ArrayList;
import java.util.List;

public class StateActivity extends AppCompatActivity {

    private RecyclerView state;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        state = (RecyclerView) findViewById(R.id.rvStateRow);

    }

    List<Object> stateList;
    StateAdapter stateAdapter;
    Object stateObj;

    @Override
    protected void onStart() {
        super.onStart();

        stateList = new ArrayList<>();

        GameStateFactory gameStateFactory = GameStateFactory.getInstance();
        for(int i = 1; i>0; i++) {
            stateObj = gameStateFactory.getGameState(i);
            if(stateObj == null) break;
            stateList.add(stateObj);

            sp = getSharedPreferences("GameStateFragment"+i, Context.MODE_PRIVATE);
            sp.getBoolean("isPass", false);
            editor = sp.edit();
            editor.putInt("StateLevel", i);
            editor.commit();


        }

        stateAdapter = new StateAdapter(getApplicationContext(), stateList);
        state.setAdapter(stateAdapter);
    }

    public class StateAdapter extends RecyclerView.Adapter<StateViewHolder> {

        Context mContext;
        List<Object> mData;
        SharedPreferences setVal_sp;
        int i =1;

        public StateAdapter(Context mContext, List<Object> mData) {
            this.mContext = mContext;
            this.mData = mData;
        }

        @NonNull
        @Override
        public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.state_row, parent, false);
            return new StateViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull StateViewHolder holder, final int position) {
            setVal_sp = mContext.getSharedPreferences("GameStateFragment"+(position+1), Context.MODE_PRIVATE);

            holder.stateLv.setText("Lv." +  setVal_sp.getInt("StateLevel", 0));
            if((setVal_sp.getBoolean("isPass", false) == true)) {
                holder.statePass.setImageResource(R.mipmap.green_check);
            }
            else {
                holder.statePass.setVisibility(View.VISIBLE);
                holder.stateLayout.setEnabled(false);
                if((mContext.getSharedPreferences("Save", Context.MODE_PRIVATE).getInt("CurrentState", 1))
                        >= (setVal_sp.getInt("StateLevel", 0))) {
                    holder.stateLayout.setEnabled(true);
                }
            }

            holder.statePic.setImageResource(R.mipmap.ic_launcher_round);

            holder.stateLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StateActivity.this, GameActivity.class);
                    intent.putExtra("GameState", mContext.getSharedPreferences("GameStateFragment"+(position+1), Context.MODE_PRIVATE)
                            .getInt("StateLevel", 0));
                    mContext.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {

        TextView stateLv;
        ImageView statePic;
        ImageView statePass;
        ConstraintLayout stateLayout;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);

            stateLv = (TextView)itemView.findViewById(R.id.stateLv);
            statePic = (ImageView) itemView.findViewById(R.id.statePic);
            statePass = (ImageView)itemView.findViewById(R.id.stateCheck);

            stateLayout = (ConstraintLayout) itemView.findViewById(R.id.stateLayout);

        }

    }
}



