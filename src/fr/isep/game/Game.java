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
    public ArrayList<ActionToken> actionTokensPair ;
    public ArrayList<ActionToken> actionTokensImpair ;

    private MainUI mainUI;
    public int turnCount;
    public int whoPlay;
    public Actions actions;

    public Game() {
        play();

    }

    public void play() {
        System.out.println("Game Playing");

        Board board = new Board();
        MrJackPlayer mrJackPlayer = new MrJackPlayer(null, 0);
        mrJackPlayer.setJackAlibiName(pickIdentityJack());
        initActionCard();
        initAlibiCard(mrJackPlayer);


        DetectivePlayer detectivePlayer = new DetectivePlayer();

        actions = new Actions(board);
        mainUI = new MainUI(actions, board, this, mrJackPlayer);

        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.updateUIDetective(board.getDetectiveBoard());
        mainUI.showpopup("Attention l'information qui va s'afficher par la suite ne concerne que Mr Jack : detectives, fermez vos yeux","Information relative à Mr Jack");
        mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
        turnCount = 1;
        whoPlay = 0;

        tourImpair(actions, mrJackPlayer, board);

    }

    public void initTurnToken() {

        actionTokensPair =new ArrayList<>();
        actionTokensImpair =new ArrayList<>();

        for (int i = 0; i < actionCards.size(); i++) {
            Random random = new Random();
            int a = random.nextInt(2);
            if (a == 0) {
                actionTokensPair.add(actionCards.get(i).getRecto());
                actionTokensImpair.add(actionCards.get(i).getVerso());
            } else {
                actionTokensImpair.add(actionCards.get(i).getRecto());
                actionTokensPair.add(actionCards.get(i).getVerso());
            }
        }





    }

    public void tourImpair(Actions actions, MrJackPlayer mrJackPlayer, Board board) {


        initTurnToken();
        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.setActionsEnabled(actionTokensImpair);


        actions.setLastActionPlayed(null);
        while (actionTokensImpair.size()!=0) {
            ActionToken lastActionPlayed = actions.getLastActionPlayed();
            actionTokensImpair.remove(lastActionPlayed);


            switch (actionTokensImpair.size()) {

                case 4:
                    whoPlay = 0;
                    break;
                case 3:

                    whoPlay = 1;
                break;
                case 2:
                    whoPlay = 1;;
                    break;

                case 1:
                    whoPlay = 0;
                    break;
                case 0:
                    mainUI.showpopup("Tour "+ (turnCount)+ " terminé "+" : appel à temoins","Informations");
                    appelATemoins(mrJackPlayer, board);


                   break;
           }

        mainUI.setTurn(whoPlay);
        }


    }

    public void tourPair(Actions actions, MrJackPlayer mrJackPlayer, Board board) {


        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.setActionsEnabled(actionTokensPair);
        actions.setLastActionPlayed(null);
        whoPlay = 1;


        while (actionTokensPair.size()!=0) {
            ActionToken lastActionPlayed = actions.getLastActionPlayed();
            actionTokensPair.remove(lastActionPlayed);

            switch (actionTokensPair.size()) {
                case 4:

                    whoPlay = 1;
                    break;

                case 3:

                    whoPlay = 0;
                    break;
                case 2:
                    whoPlay = 0;;
                    break;

                case 1:
                    whoPlay = 1;
                    break;
                case 0:
                    mainUI.showpopup("Tour "+ (turnCount)+ " terminé "+" : appel à temoins","Informations");
                    appelATemoins(mrJackPlayer, board);
                    break;
            }

            mainUI.setTurn(whoPlay);
        }



    }



    public void initActionCard() {
        actionCards = new ArrayList<>();
        actionCards.add(new ActionCard(ActionToken.ALIBI, ActionToken.HOLMES));
        actionCards.add(new ActionCard(ActionToken.LE_CHIEN, ActionToken.WATSON));
        actionCards.add(new ActionCard(ActionToken.ROTATION, ActionToken.ECHANGE));
        actionCards.add(new ActionCard(ActionToken.ROTATION2, ActionToken.JOKER));
    }

    public void initAlibiCard(MrJackPlayer mrJackPlayer) {
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
        AlibiName jackIdentity = mrJackPlayer.getJackAlibiName();
        for (int i=0;i<alibiCards.size();i++)
        { if(alibiCards.get(i).getName().equals(jackIdentity)){
            alibiCards.remove(alibiCards.get(i));
        }
        }

    }


    public AlibiName pickIdentityJack() {
        Random rand = new Random();
        AlibiName Jack = AlibiName.values()[(int) rand.nextInt(AlibiName.values().length)];

        return Jack;
    }

    public boolean DetectiveConditionToWin(Board board) {

        District[][] districtBoard = board.getDistrictBoard();
        ArrayList<AlibiName> personnagesRestant = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (districtBoard[i][j].isRecto() == true) {
                    personnagesRestant.add(districtBoard[i][j].getCharacter());
                }
            }

        }
        if (personnagesRestant.size()==1) {
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

    public boolean DetectiveSeeJack(MrJackPlayer mrJackPlayer, Board board) {
        ArrayList<AlibiName> visiblecharacters;
        visiblecharacters = board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard());
        if (visiblecharacters.contains(mrJackPlayer.getJackAlibiName())) {
            return true;
        } else {
            return false;
        }
    }

    public void appelATemoins(MrJackPlayer mrJackPlayer, Board board) {
        ArrayList<AlibiName> visiblecharacters;
        visiblecharacters = board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard());

        District[][] districtBoard = board.getDistrictBoard();
        boolean iSVisibleByDetectives = DetectiveSeeJack(mrJackPlayer, board);
        if (iSVisibleByDetectives == false) {
            mainUI.showpopup("Verdict de l'appel à témoins : Mr Jack est invisible","Informations");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (visiblecharacters.contains(districtBoard[i][j].getCharacter())) {
                        districtBoard[i][j].setRecto(false);

                    }
                }
            }
            mrJackPlayer.setHourglass(mrJackPlayer.getHourglass()+1);
            mainUI.updateHourglass(mrJackPlayer.getHourglass());
            mainUI.updateUIDistrict(board.getDistrictBoard());
        }
        else   if (iSVisibleByDetectives == true){
            mainUI.showpopup("Verdict de l'appel à témoins : Mr Jack est visible","Informations");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!visiblecharacters.contains(districtBoard[i][j].getCharacter())) {
                        districtBoard[i][j].setRecto(false);
                    }
                }
            }
            mainUI.updateUIDistrict(board.getDistrictBoard());

        }

        turnCount = turnCount + 1;
        if (turnCount<=8){


            conditionSatistisfield(mrJackPlayer,board);
        }
        else{
                if ((MrJackConditionToWin(mrJackPlayer) == false)&&(DetectiveConditionToWin(board) == false)){
                    mainUI.showpopup("Jack gagne. Aucun des joueurs n'a atteint son objectif avant les 8 tours ","Fin du jeu");
                    mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
                }
                else if((MrJackConditionToWin(mrJackPlayer) == true)&&(DetectiveConditionToWin(board) == true)){
                    if (iSVisibleByDetectives==true){
                        mainUI.showpopup("Les detectives gagnent. Tous les joueurs ont atteint leur objectif mais jack était visible à la fin du jeu","Fin du jeu");
                        mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
                    }
                    else if (iSVisibleByDetectives==false){
                        mainUI.showpopup("Mr Jack gagne. Tous les joueurs ont atteint leur objectif mais jack était invisible à la fin du jeu","Fin du jeu");
                        mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
                    }
                }

        }

    }


    public void conditionSatistisfield(MrJackPlayer mrJackPlayer,Board board) {
        boolean iSVisibleByDetectives = DetectiveSeeJack(mrJackPlayer, board);
        mainUI.updateUIDistrict(board.getDistrictBoard());
        if((MrJackConditionToWin(mrJackPlayer) == false)&&(DetectiveConditionToWin(board) == false)){
            mainUI.showpopup("debut du tour  "+turnCount,"Informations");
            if (turnCount % 2 == 0) {
                tourPair(actions, mrJackPlayer, board);


            } else {

                tourImpair(actions, mrJackPlayer, board);
            }
        }
        else if((MrJackConditionToWin(mrJackPlayer) ==true)&&(DetectiveConditionToWin(board) == false)){
            mainUI.showpopup("Mr Jack gagne avec ses 6 sabliers","Fin du jeu");
            mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
        }
        else if((MrJackConditionToWin(mrJackPlayer) ==false)&&(DetectiveConditionToWin(board) == true)){
            mainUI.updateUIDistrict(board.getDistrictBoard());
            mainUI.showpopup("Les detective gagnent","Fin du jeu");
            mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
        }
        else if ((MrJackConditionToWin(mrJackPlayer) ==true)&&(DetectiveConditionToWin(board) == true)){
            if (iSVisibleByDetectives==true){
                mainUI.showpopup("Les detectives gagnent. Tous les joueurs ont atteint leur objectif mais jack a été découverte avant la fin du jeu","Fin du jeu");
                mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
            }
            else{mainUI.showpopup("Course poursuite du tour " +turnCount ,"Course poursuite");
                if (turnCount % 2 == 0) {
                    tourPair(actions, mrJackPlayer, board);


                } else {

                    tourImpair(actions, mrJackPlayer, board);
                }

            }
        }

    }


}


