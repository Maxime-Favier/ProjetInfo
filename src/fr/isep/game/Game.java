package fr.isep.game;

import fr.isep.board.Board;
import fr.isep.board.DetectiveName;
import fr.isep.ui.MainUI;

import java.util.ArrayList;

public class Game {
    private ArrayList<ActionCard> actionCards;
    public int turnCount;
    public Game() {
        play();

    }

    public void play() {
        System.out.println("Game Playing");

        Board board = new Board();
        initActionCard();
        Actions actions = new Actions(board);
        MainUI mainUI = new MainUI(actions, board);
        System.out.println(board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard()));

        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.updateUIDetective(board.getDetectiveBoard());

        turnCount=1;

    }

    public void turn(){
        ArrayList<ActionToken> actionTokensPair= new ArrayList<>();
        ArrayList<ActionToken> actionTokensImpair= new ArrayList<>();

        if(turnCount%2==0){

        }

       else{
            for (int i=0;i<actionCards.size();i++){
                int a =(int) Math.random()*1;
                if (a==0) {
                    actionTokensPair.add(actionCards.get(i).getRecto());
                }
                else{
                    actionTokensImpair.add(actionCards.get(i).getRecto());
                }
            }


        }
    }

    public void initActionCard(){
        actionCards = new ArrayList<>();
        actionCards.add(new ActionCard(ActionToken.ALIBI,ActionToken.HOLMES));
        actionCards.add(new ActionCard(ActionToken.LE_CHIEN,ActionToken.WATSON));
        actionCards.add(new ActionCard(ActionToken.ROTATION,ActionToken.ECHANGE));
        actionCards.add(new ActionCard(ActionToken.ROTATION,ActionToken.JOKER));
    }

}
