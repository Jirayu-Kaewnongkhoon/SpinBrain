package com.game.spinbrain.GameStateFragment;

public class GameStateFactory {

    private static GameStateFactory instance;

    GameStateFactory(){

    }

    public static GameStateFactory getInstance(){
        if(instance == null){
            instance = new GameStateFactory();
        }
        return instance;

    }

    public Object getGameState(int val) {

        switch (val){
            case 1: return new GameFragment1();
            case 2: return new GameFragment2();
            case 3: return new GameFragment3();
        }

        return null;
    }
}
