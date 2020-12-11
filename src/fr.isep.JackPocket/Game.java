package fr.isep.JackPocket;

public class Game {
    private Board board;
    private Player player;

    public void play(){
        initBoard();
        initPlayer();
        }
    private void initBoard() {
        board = new Board();
    }
    private void initPlayer(){
        player= new Player();

    }

    public boolean gameEnded(){
        if (player.hasDetectiveReachObjectif()==true || player.hasDetectiveReachObjectif()==true)
            return true;
        else
            return false;
    }
    public void whoWin(){
        if (player.hasDetectiveReachObjectif()==true)
        {System.out.println("le detective gagne");}
        else if (player.hasMrJackReachObjectif()==true)
        {  System.out.println("mr jack gagne");}
        else if ((player.hasMrJackReachObjectif()==true)&& (player.hasDetectiveReachObjectif()==true))
        {
            if ((player.whatTurn()==8)&&(player.mrJackIsVisible()==true)){
            System.out.println("le detective gagne");
        }
            if ((player.whatTurn()==8)&&(player.mrJackIsVisible()==false)){
                System.out.println("Mr Jack gagne");
            }
            if ((player.whatTurn()<8)&&gameEnded()==false){
                // course poursuite
            }
        }

        else if ((player.hasMrJackReachObjectif()==false)&& (player.hasDetectiveReachObjectif()==false))
        {
            System.out.println("Mr Jack gagne");
        }
    }


}
