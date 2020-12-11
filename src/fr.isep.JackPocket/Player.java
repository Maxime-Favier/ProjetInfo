package fr.isep.JackPocket;

import java.util.ArrayList;
import java.util.*;
public class Player {
    private ArrayList<AlibiCard> alibicard = new ArrayList<AlibiCard>();
    private ArrayList<TurnToken> turnTokens = new ArrayList<TurnToken>();

    private DetectivePlayer detectivePlayer;
    private MrJackPlayer mrJackPlayer;
    private TurnToken turn;
    public Player(){
        initPlayer();
    }

    public ArrayList<AlibiCard> getAlibicard() {
        return alibicard;
    }

    public void setAlibicard(ArrayList<AlibiCard> alibicard) {
        this.alibicard = alibicard;
    }

    public ArrayList<TurnToken> getTurnTokens() {
        return turnTokens;
    }

    public void setTurnTokens(ArrayList<TurnToken> turnTokens) {
        this.turnTokens = turnTokens;
    }

    public void initPlayer(){
        detectivePlayer = new DetectivePlayer();

        alibicard.add(new AlibiCard(AlibiName.Inspecteur_Lestrade, 0));
        alibicard.add(new AlibiCard(AlibiName.Miss_Stealthy, 1));
         alibicard.add(new AlibiCard(AlibiName.Jeremy_Bert, 1));
         alibicard.add(new AlibiCard(AlibiName.John_Pizer, 1));
         alibicard.add(new AlibiCard(AlibiName.John_Smith, 1));
         alibicard.add(new AlibiCard(AlibiName.Joseph_Lane, 1));
        alibicard.add(new AlibiCard(AlibiName.Madame, 2));
         alibicard.add(new AlibiCard(AlibiName.Sgt_Goodley, 0));
         alibicard.add(new AlibiCard(AlibiName.William_Gull, 1));
        Random rand = new Random();
        int randomIndex = rand.nextInt(alibicard.size());

        mrJackPlayer = new MrJackPlayer(alibicard.get(randomIndex),0,false);

         for (int i=0;i<9; i++){
             turnTokens.add(new TurnToken(i,1));

         }
        turn=new TurnToken(1,1);

    }

    public boolean hasDetectiveReachObjectif(){
        if (alibicard.size()==1)
            return true;
        else
            return false;
    }

    public boolean hasMrJackReachObjectif(){
        if (mrJackPlayer.getHourglassMrJack()>=6)
            return true;
        else
            return false;
    }

    public int whatTurn(){
        return turn.getTurn();
    }

    public boolean mrJackIsVisible()
        {
        return mrJackPlayer.isMrJackvisible();
    }
}