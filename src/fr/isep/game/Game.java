package fr.isep.game;

import fr.isep.board.AlibiName;
import fr.isep.board.Board;
import fr.isep.board.District;
import fr.isep.ui.MainUI;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public ArrayList<AlibiCard> alibiCards;
    private ArrayList<ActionCard> actionCards;
    public  ArrayList<ActionToken> actionTokensPair ;
    public ArrayList<ActionToken> actionTokensImpair ;
    private MainUI mainUI;
    public int turnCount;
    public int whoPlay;
    public ActionToken actionChoose;
    public Actions actions;

    public Game() {
        play();

    }

    public void play() {
        System.out.println("Game Playing");

        Board board = new Board();

        initActionCard();
        initAlibiCard();

        MrJackPlayer mrJackPlayer = new MrJackPlayer(null, 0, false);
        mrJackPlayer.setJackAlibiName(pickIdentityJack());
        DetectivePlayer detectivePlayer = new DetectivePlayer();
         actions = new Actions(board);
        mainUI = new MainUI(actions, board,this, mrJackPlayer);
       // System.out.println(board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard()));

        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.updateUIDetective(board.getDetectiveBoard());

        mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
        turnCount = 1;
        whoPlay = 0;

     //turn(board,mrJackPlayer);
        tourImpair(actions,mrJackPlayer,board);

    }

    public void initTurnToken(){
        actionTokensPair = new ArrayList<>();
       actionTokensImpair = new ArrayList<>();
        for (int i = 0; i < actionCards.size(); i++) {
            Random random = new Random();
            int a = random.nextInt(2);
            //System.out.println(a);
            if (a == 0) {
                actionTokensPair.add(actionCards.get(i).getRecto());
                actionTokensImpair.add(actionCards.get(i).getVerso());
            } else {
                actionTokensImpair.add(actionCards.get(i).getRecto());
                actionTokensPair.add(actionCards.get(i).getVerso());
            }
        }

    }

    public void tourImpair(Actions actions,MrJackPlayer mrJackPlayer,Board board){
        initTurnToken();

        mainUI.setActionsEnabled(actionTokensImpair);

       // int numeroTour =actionTokensImpair.size();
        while (actionTokensImpair.size()!=0) {
            ActionToken lastActionPlayed = actions.getLastActionPlayed();
            int nbRotate=0;
            if (lastActionPlayed==ActionToken.ROTATION){
                for (int i=0;i<actionTokensImpair.size();i++){
                    if (actionTokensImpair.get(i).equals(ActionToken.ROTATION)){
                        nbRotate=nbRotate+1;
                    }
                }
                if(nbRotate==2){
                    actionTokensImpair.remove(ActionToken.ROTATION);
                    actionTokensImpair.add(ActionToken.ROTATION);
                }
                if(nbRotate==1){
                    actionTokensImpair.remove(ActionToken.ROTATION);
                }
            }
            else{
                actionTokensImpair.remove(lastActionPlayed);
            }

                switch (actionTokensImpair.size()) {

                case 4:
                    whoPlay=0;
                    //mainUI.setTurn(whoPlay);
                    break;
                case 3 :
                    whoPlay=1;
                    //mainUI.setTurn(whoPlay);
                    break;
                case 2:
                    whoPlay=1;
                   // mainUI.setTurn(whoPlay);
                    break;

                case 1:
                    whoPlay=0;

                    break;
                case 0:
                    appelATemoins(mrJackPlayer,board);

                   // turnCount=turnCount+1;
                  //  tourPair();

            }
            mainUI.setTurn(whoPlay);

        }


    }

    public void tourPair(Actions actions,MrJackPlayer mrJackPlayer,Board board){

        mainUI.setActionsEnabled(actionTokensPair);


        // int numeroTour =actionTokensImpair.size();
        while (actionTokensPair.size()!=0) {

            ActionToken lastActionPlayed = actions.getLastActionPlayed();
            int nbRotate=0;
            if (lastActionPlayed==ActionToken.ROTATION){
                for (int i=0;i<actionTokensPair.size();i++){
                    if (actionTokensPair.get(i).equals(ActionToken.ROTATION)){
                        nbRotate=nbRotate+1;
                    }
                }
                if(nbRotate==2){
                    actionTokensPair.remove(ActionToken.ROTATION);
                    actionTokensPair.add(ActionToken.ROTATION);
                }
                if(nbRotate==1){
                    actionTokensPair.remove(ActionToken.ROTATION);
                }
            }
            else{
                actionTokensPair.remove(lastActionPlayed);
            }

            switch (actionTokensPair.size()) {

                case 4:
                    whoPlay=1;
                    //mainUI.setTurn(whoPlay);
                    break;
                case 3 :
                    whoPlay=0;
                    //mainUI.setTurn(whoPlay);
                    break;
                case 2:
                    whoPlay=0;
                    // mainUI.setTurn(whoPlay);
                    break;

                case 1:
                    whoPlay=1;

                    break;
                case 0:
                   // turnCount=turnCount+1;
                    appelATemoins(mrJackPlayer,board);
                    //tourPair();

            }
            mainUI.setTurn(whoPlay);


        }



    }

    /*public void turn(Board board,MrJackPlayer mrJackPlayer) {
       boolean detectivecondition= DetectiveConditionToWin (board);
       boolean jackCondition=MrJackConditionToWin(mrJackPlayer);

        
        while((turnCount<8)||(jackCondition==false)||(detectivecondition==false)) {
           
            if (turnCount % 2 != 0){
            tourImpair(actions,mrJackPlayer,board);
            break;
            }
            else {
                tourPair(actions, mrJackPlayer, board);
                break;
                //mainUI.setActionsEnabled(actionTokensPair);

            }

        }
    }

     */

//    public void gameEnded(){
    //      if (turnCount>8)
    //}


    public void initActionCard() {
        actionCards = new ArrayList<>();
        actionCards.add(new ActionCard(ActionToken.ALIBI, ActionToken.HOLMES));
        actionCards.add(new ActionCard(ActionToken.LE_CHIEN, ActionToken.WATSON));
        actionCards.add(new ActionCard(ActionToken.ROTATION, ActionToken.ECHANGE));
        actionCards.add(new ActionCard(ActionToken.ROTATION, ActionToken.JOKER));
    }

    public void initAlibiCard() {
        alibiCards = new ArrayList<>();
        alibiCards.add(new AlibiCard(AlibiName.MADAME, 2));
        alibiCards.add(new AlibiCard(AlibiName.SERGENT_GOODLEY, 0));
        alibiCards.add(new AlibiCard(AlibiName.JEREMY_BART, 1));
        alibiCards.add(new AlibiCard(AlibiName.WILLIAM_GULL, 1));
        alibiCards.add(new AlibiCard(AlibiName.MISS_STEALTHY, 1));
        alibiCards.add(new AlibiCard(AlibiName.JOHN_SMITH, 1));
        alibiCards.add(new AlibiCard(AlibiName.LESTRADE, 0));
        alibiCards.add(new AlibiCard(AlibiName.JOHN_PIZER, 1));
        alibiCards.add(new AlibiCard(AlibiName.JOSEPH_LANG, 1));

    }


    public AlibiName pickIdentityJack() {
        Random rand = new Random();
        AlibiName Jack = AlibiName.values()[(int) rand.nextInt(AlibiName.values().length)];

        return Jack;
    }

    public boolean DetectiveConditionToWin(Board board) {
        District[][] districtBoard = board.getDistrictBoard();
        int somme = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (districtBoard[i][j].isRecto() == false) {
                    somme = somme + 1;
                }
            }

        }
        if (somme == 1) {
            return true;
        } else {
            return false;
        }

    }


    public boolean MrJackConditionToWin(MrJackPlayer mrJackPlayer) {
        int sablierJack = mrJackPlayer.getHourglass();
        if (sablierJack >= 6) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DetectiveSeeJack(MrJackPlayer mrJackPlayer, Board board){
        ArrayList<AlibiName> visiblecharacters;
        visiblecharacters = board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard());
        if (visiblecharacters.contains(mrJackPlayer.getJackAlibiName())){
            return true;
        }
        else{
            return false;
        }

    }
    public void appelATemoins(MrJackPlayer mrJackPlayer, Board board) {
        ArrayList<AlibiName> visiblecharacters;
        visiblecharacters = board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard());
        District[][] districtBoard = board.getDistrictBoard();
        boolean iSVisibleByDetectives =DetectiveSeeJack( mrJackPlayer, board);
        if (iSVisibleByDetectives== false) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (visiblecharacters.contains(districtBoard[i][j].getCharacter())) {
                        districtBoard[i][j].setRecto(false);
                        mrJackPlayer.setHourglass(mrJackPlayer.getHourglass() + 1);
                    }
                }
            }

        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!visiblecharacters.contains(districtBoard[i][j].getCharacter())) {
                        districtBoard[i][j].setRecto(false);
                    }
                }
            }
        }
        mainUI.updateUIDistrict(board.getDistrictBoard());
        if((turnCount<8)||(MrJackConditionToWin( mrJackPlayer)==false)||(DetectiveConditionToWin(board)==false))
        {   turnCount=turnCount+1;
            if (turnCount%2==0){
                tourPair(actions,mrJackPlayer,board);

            }
            else if (turnCount%2!=0){
                tourImpair(actions,mrJackPlayer,board);
            }
        }
        else if ((turnCount>8)||(MrJackConditionToWin( mrJackPlayer)==true)||(DetectiveConditionToWin(board)==true)){
            System.out.println(whoPlay+"a gagne");
        }
        System.out.println("sablier" + turnCount);
        System.out.println("condition jacke " + MrJackConditionToWin( mrJackPlayer));
        System.out.println("condition detect " + DetectiveConditionToWin(board));
    }


}
