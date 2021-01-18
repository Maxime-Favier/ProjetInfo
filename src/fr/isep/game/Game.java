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
    public ArrayList<ActionToken> actionTokensPair = new ArrayList<>();
    public ArrayList<ActionToken> actionTokensImpair = new ArrayList<>();

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
        mainUI = new MainUI(actions, board, this, mrJackPlayer);
        // System.out.println(board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard()));

        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.updateUIDetective(board.getDetectiveBoard());

        mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
        turnCount = 1;
        whoPlay = 0;

        //turn(board,mrJackPlayer);
        tourImpair(actions, mrJackPlayer, board);

    }

    public void initTurnToken() {


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

    public void tourImpair(Actions actions, MrJackPlayer mrJackPlayer, Board board) {
        for(int a =0;a<actionTokensImpair.size();a++){
            System.out.print(actionTokensImpair.get(a)+"  ");
        }

        System.out.println("  ");


        initTurnToken();
        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.setActionsEnabled(actionTokensImpair);

        while (actionTokensImpair.size() != 0) {
            ActionToken lastActionPlayed = actions.getLastActionPlayed();
            int nbRotate = 0;
            int index=0;
            if (lastActionPlayed == ActionToken.ROTATION) {
                for (int i = 0; i < actionTokensImpair.size(); i++) {
                    if (actionTokensImpair.get(i).equals(ActionToken.ROTATION)) {
                        nbRotate = nbRotate + 1;


                    }
                }
                if (nbRotate == 2) {
                    ArrayList<ActionToken> listeintermediaire = new ArrayList<>();
                    for (int i = 0; i < actionTokensImpair.size(); i++) {
                        if (!actionTokensImpair.get(i).equals(ActionToken.ROTATION)) {
                            listeintermediaire.add(actionTokensImpair.get(i));

                        }
                    }
                    listeintermediaire.add(ActionToken.ROTATION);
                    actionTokensImpair.clear();
                    actionTokensImpair.addAll(listeintermediaire);
                    listeintermediaire.clear();

                }
               else {
                    actionTokensImpair.remove(ActionToken.ROTATION);

                }
            }
            else {
                actionTokensImpair.remove(lastActionPlayed);
            }

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
                    appelATemoins(mrJackPlayer, board);
                    break;
            }
            mainUI.setTurn(whoPlay);

        }


    }

    public void tourPair(Actions actions, MrJackPlayer mrJackPlayer, Board board) {
        for(int a =0;a<actionTokensPair.size();a++){
            System.out.print(actionTokensPair.get(a)+"  ");
        }

        System.out.println("  ");
        mainUI.setActionsEnabled(actionTokensPair);
        mainUI.updateUIDistrict(board.getDistrictBoard());
        while (actionTokensPair.size() != 0) {
            for(int a=0;a<actionTokensPair.size();a++){
                System.out.println(actionTokensPair.get(a));
            }

            ActionToken lastActionPlayed = actions.getLastActionPlayed();
            int nbRotate = 0;
            int index=0;
            if (lastActionPlayed == ActionToken.ROTATION) {
                for (int i = 0; i < actionTokensPair.size(); i++) {
                    if (actionTokensPair.get(i).equals(ActionToken.ROTATION)) {
                        nbRotate = nbRotate + 1;

                    }
                }
                if (nbRotate == 2) {

                    ArrayList<ActionToken> listeintermediaire = new ArrayList<>();
                    for (int i = 0; i < actionTokensPair.size(); i++) {
                        if (!actionTokensPair.get(i).equals(ActionToken.ROTATION)) {
                            listeintermediaire.add(actionTokensPair.get(i));

                        }
                    }
                    listeintermediaire.add(ActionToken.ROTATION);
                    actionTokensPair.clear();
                   actionTokensPair.addAll(listeintermediaire);
                   listeintermediaire.clear();
                }
                else {
                    actionTokensPair.remove(ActionToken.ROTATION);

                }
            }
            else {
                actionTokensPair.remove(lastActionPlayed);
            }

            switch (actionTokensPair.size()) {

                case 4:
                    whoPlay = 1;
                    break;
                case 3:
                    whoPlay = 0;
                    break;
                case 2:
                    whoPlay = 0;
                    break;

                case 1:
                    whoPlay = 1;
                    break;
                case 0:

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
    public void InvisibleCharacters(MrJackPlayer mrJackPlayer, Board board) {
        ArrayList<AlibiName> visiblecharacters;
        visiblecharacters = board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard());
        ArrayList<AlibiName> invisiblecharacters=new ArrayList<>();
        District[][] districtBoard = board.getDistrictBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!visiblecharacters.contains(districtBoard[i][j].getCharacter())) {
                    invisiblecharacters.add(districtBoard[i][j].getCharacter());

                }
            }
        }
    }

    public void appelATemoins(MrJackPlayer mrJackPlayer, Board board) {
        ArrayList<AlibiName> visiblecharacters;
        visiblecharacters = board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard());

        District[][] districtBoard = board.getDistrictBoard();
        boolean iSVisibleByDetectives = DetectiveSeeJack(mrJackPlayer, board);
        if (iSVisibleByDetectives == false) {
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
            mainUI.showpopup("jack est visible","Informations");
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
        if (turnCount<8){

            mainUI.showpopup("Tour terminé : appel à temoins","Informations");
            conditionSatistisfield(mrJackPlayer,board);
        }
        else{
            mainUI.showpopup("vous avez fini les 8 tours","jack gagnant");
        }

    }


    public void conditionSatistisfield(MrJackPlayer mrJackPlayer,Board board) {
        mainUI.updateUIDistrict(board.getDistrictBoard());
        if((MrJackConditionToWin(mrJackPlayer) == false)&&(DetectiveConditionToWin(board) == false)){
            mainUI.showpopup("tour  "+turnCount,"jack gagnant");
            if (turnCount % 2 == 0) {
                tourPair(actions, mrJackPlayer, board);


            } else {
                tourImpair(actions, mrJackPlayer, board);
            }
        }
        else if((MrJackConditionToWin(mrJackPlayer) ==true)&&(DetectiveConditionToWin(board) == false)){
            mainUI.showpopup("jack gagne avec ses 6 sabliers","Victoire");
        }
        else if((MrJackConditionToWin(mrJackPlayer) ==false)&&(DetectiveConditionToWin(board) == true)){
            mainUI.updateUIDistrict(board.getDistrictBoard());
            mainUI.showpopup("detective gagne","Victoi");

        }

    }


}


