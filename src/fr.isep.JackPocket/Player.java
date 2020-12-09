package fr.isep.JackPocket;

import java.util.ArrayList;

public class Player {
    private ArrayList<AlibiCard> alibicard = new ArrayList<AlibiCard>();
    private ArrayList<TurnToken> turnTokens = new ArrayList<TurnToken>();

    public ArrayList<AlibiCard> getAlibicard(){
        return alibicard;
    }
    public void setAlibicard(ArrayList<AlibiCard> alibicard){
        this.alibicard = alibicard;
    }

    public ArrayList<TurnToken> getTurnTokens(){
        return turnTokens;
    }

    public void setTurnTokens(ArrayList<TurnToken> turnTokens){
        this.turnTokens = turnTokens;
    }
}
