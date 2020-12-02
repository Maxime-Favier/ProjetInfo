package fr.isep.JackPocket;

import java.util.ArrayList;

public class Player {
    private ArrayList<Alibi> alibicard = new ArrayList<Alibi>();
    private ArrayList<TurnToken> turnTokens = new ArrayList<TurnToken>();

    public ArrayList<Alibi> getAlibicard(){
        return alibicard;
    }
    public void setAlibicard(ArrayList<Alibi> alibicard){
        this.alibicard = alibicard;
    }

    public ArrayList<TurnToken> getTurnTokens(){
        return turnTokens;
    }

    public void setTurnTokens(ArrayList<TurnToken> turnTokens){
        this.turnTokens = turnTokens;
    }
}
