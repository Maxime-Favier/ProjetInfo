package fr.isep.game;

import fr.isep.board.AlibiName;
import fr.isep.board.Board;
import fr.isep.board.District;
import fr.isep.ui.MainUI;

import java.util.ArrayList;
import java.util.Random;

public class Game { // classe game

    //variables de classe :
    public ArrayList<AlibiCard> alibiCards;
    private ArrayList<ActionCard> actionCards;
    public ArrayList<ActionToken> actionTokensPair ;
    public ArrayList<ActionToken> actionTokensImpair ;

    private MainUI mainUI;
    public int turnCount;
    public int whoPlay;
    public Actions actions;

    public Game() {
        play(); // on lance le jeu

    }

    public void play() {
        System.out.println("Game Playing");

        Board board = new Board(); //on créé un nouveau board
        MrJackPlayer mrJackPlayer = new MrJackPlayer(null, 0); //on crée un joueur mr jack
        mrJackPlayer.setJackAlibiName(pickIdentityJack()); // on appelle la fonction pick identity jack pour donner a mr jack un alibi name
        initActionCard(); // on initialise les actions cartes
        initAlibiCard(mrJackPlayer); // on supprime de la liste alibi carte, la carte de mr jak


        DetectivePlayer detectivePlayer = new DetectivePlayer(); //on crée un joueur detective

        actions = new Actions(board);
        mainUI = new MainUI(actions, board, this, mrJackPlayer); // on crée l'affichage graphique

        mainUI.updateUIDistrict(board.getDistrictBoard()); //on met a jour l affichage graphique
        mainUI.updateUIDetective(board.getDetectiveBoard());
        mainUI.showpopup("Attention l'information qui va s'afficher par la suite ne concerne que Mr Jack : detective, fermez vos yeux","Information relative à Mr Jack");
        mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
        turnCount = 1; // on initialise à 1 le numero du tour
        whoPlay = 0; // on initialise à 0 le joueur qui joue la partie : detective commence à jouer

        tourImpair(actions, mrJackPlayer, board); // on demarre le jeu en appelant la fonction tour impair

    }

    public void initTurnToken() { // fonction qui permet d'inialiser les listes d actions tour token pour un tour pair et impair

        actionTokensPair =new ArrayList<>(); // on crée une nouvelle liste d'action token pour les tours pairs
        actionTokensImpair =new ArrayList<>(); // on crée une nouvelle liste d'action token pour les tours impairs

        for (int i = 0; i < actionCards.size(); i++) {
            Random random = new Random();
            int a = random.nextInt(2); // on lance aleatoirement le jeton action card
            if (a == 0) {
                actionTokensPair.add(actionCards.get(i).getRecto());
                actionTokensImpair.add(actionCards.get(i).getVerso());
            } else {
                actionTokensImpair.add(actionCards.get(i).getRecto());
                actionTokensPair.add(actionCards.get(i).getVerso());
            }
        }





    }

    public void tourImpair(Actions actions, MrJackPlayer mrJackPlayer, Board board) { // fonction qui est appele a chaque tour impair


        initTurnToken(); // on initialise nos deux listes action token pair et impair
        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.setActionsEnabled(actionTokensImpair); // on met à jour l'affichage graphique


        actions.setLastActionPlayed(null); // la derniere action qui a été joué par le joueur est null

        while (actionTokensImpair.size()!=0) { // tant que toutes les actions de la liste action token impair n ont pas été joué
            ActionToken lastActionPlayed = actions.getLastActionPlayed(); // on récupere la dernière action qui a été joué et on l'a supprime de la liste action token impair
            actionTokensImpair.remove(lastActionPlayed);


            switch (actionTokensImpair.size()) { // selon la taille de la liste action token impair

                case 4: // si aucune action n'a été joué, la taille de la liste vaut 4
                    whoPlay = 0; // le joueur qui joue est detective
                    break;
                case 3: // si 1 action a été joué, la taille de la liste vaut 3

                    whoPlay = 1; // le detective qui joue est mr jack
                    break;
                case 2: // si 2 action ont été joué, la taille de la liste vaut 2
                    whoPlay = 1;; // le detective qui joue est mr jack
                    break;

                case 1: // si 3 ont été joué, la taille de la liste vaut 1
                    whoPlay = 0; // le joueur qui joue est detective
                    break;
                case 0: // si toutes les actions ont été joué, la taille de la liste vaut 0
                    mainUI.showpopup("Tour "+ (turnCount)+ " terminé "+" : appel à temoins","Informations");
                    appelATemoins(mrJackPlayer, board); // on lance l'appel a temoins


                    break;
            }

            mainUI.setTurn(whoPlay); // met a jour le label indiquant qui est en train de jouer
        }


    }

    public void tourPair(Actions actions, MrJackPlayer mrJackPlayer, Board board) { // fonction qui est appele a chaque tour pair


        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.setActionsEnabled(actionTokensPair); //met a jour l affichage graphique
        actions.setLastActionPlayed(null); // la derniere action qui a été joué par le joueur est null
        whoPlay = 1;


        while (actionTokensPair.size()!=0) { // tant que toutes les actions de la liste action token pair n ont pas été joué
            ActionToken lastActionPlayed = actions.getLastActionPlayed();
            actionTokensPair.remove(lastActionPlayed); // on récupere la dernière action qui a été joué et on l'a supprime de la liste action token pair

            switch (actionTokensPair.size()) { // selon la taille de la liste action token pair

                case 4: // si aucune action n'a été joué, la taille de la liste vaut 4

                    whoPlay = 1; // le joueur qui joue est mr jack
                    break;

                case 3: // si 1 action a été joué, la taille de la liste vaut 3

                    whoPlay = 0; // le joueur qui joue est detective
                    break;
                case 2: // si 2 action ont été joué, la taille de la liste vaut 2
                    whoPlay = 0;; // le joueur qui joue est detective
                    break;

                case 1: // si 3 ont  été joué, la taille de la liste vaut 4
                    whoPlay = 1; // le joueur qui joue est mr jack
                    break;
                case 0: // si toutes les actions ont été joué la taille de la liste vaut 0
                    mainUI.showpopup("Tour "+ (turnCount)+ " terminé "+" : appel à temoins","Informations");
                    appelATemoins(mrJackPlayer, board); // on lance l appel a temoins
                    break;
            }

            mainUI.setTurn(whoPlay); // met a jour le label indiquant qui est en train de jouer
        }



    }



    public void initActionCard() { // fonction qui permet d ininialiser et de créer la liste d action carte qui existe
        actionCards = new ArrayList<>();
        actionCards.add(new ActionCard(ActionToken.ALIBI, ActionToken.HOLMES));
        actionCards.add(new ActionCard(ActionToken.LE_CHIEN, ActionToken.WATSON));
        actionCards.add(new ActionCard(ActionToken.ROTATION, ActionToken.ECHANGE));
        actionCards.add(new ActionCard(ActionToken.ROTATION2, ActionToken.JOKER));
    }

    public void initAlibiCard(MrJackPlayer mrJackPlayer) { // fonction qui permet de creer une liste alibi cartes et d enlever de la liste alibi carte, l'identite de mr jack
        alibiCards = new ArrayList<>(); // on crée la liste alibi cartes
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
        { if(alibiCards.get(i).getName().equals(jackIdentity)){ // on parcourt la liste et on supprime de la liste la carte de mr jack
            alibiCards.remove(alibiCards.get(i));
        }
        }

    }


    public AlibiName pickIdentityJack() { // fonction qui permet de choisir aleatoirement une identite pour mr jack
        Random rand = new Random();
        AlibiName Jack = AlibiName.values()[(int) rand.nextInt(AlibiName.values().length)];

        return Jack;
    }

    public boolean DetectiveConditionToWin(Board board) { // fonction de type boolean qui determine si le detective a remplit sa condition pour gagner ou pas

        District[][] districtBoard = board.getDistrictBoard();
        ArrayList<AlibiName> personnagesRestant = new ArrayList<>(); // liste determinant les suspects

        for (int i = 0; i < 3; i++) { // on parcourt le board
            for (int j = 0; j < 3; j++) {
                if (districtBoard[i][j].isRecto() == true) { // si un district n a pas sa face suspect retourne
                    personnagesRestant.add(districtBoard[i][j].getCharacter()); // on ajoute a la liste ce suspect
                }
            }

        }
        if (personnagesRestant.size()==1) { // si la taille de la liste des suspects restant vaut 1 ( il ne reste qu'un suspect)
            return true; // le detective a remplit sa condition pour gagner
        } else { // sinon
            return false; // le detective n'a pas remplit sa condition pour gagner
        }

    }


    public boolean MrJackConditionToWin(MrJackPlayer mrJackPlayer) { // fonction de type boolean qui determine si mr jack a remplit sa condition pour gagner ou pas

        int sablierJack = mrJackPlayer.getHourglass(); // on recupere le nombre de sabiers que possede mr jack
        if (sablierJack >= 6) { // si mr jack a recuperer au moins 6 sabliers
            return true; // mr jack a remplit sa condition pour gagner
        } else { // sinon
            return false; // mr jack a remplit sa condition pour gagner
        }
    }

    public boolean DetectiveSeeJack(MrJackPlayer mrJackPlayer, Board board) { // // fonction de type boolean qui determine si les detectives voient mr jack
        ArrayList<AlibiName> visiblecharacters;
        visiblecharacters = board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard()); // on recupere la liste visible characters
        if (visiblecharacters.contains(mrJackPlayer.getJackAlibiName())) { // si mr jack est dans la liste visibles characters
            return true; // les detectives voient mr jack => renvoie vrai
        } else { //sinon
            return false; // les detectives ne voient pas mr jack => renvoie faux
        }
    }

    public void appelATemoins(MrJackPlayer mrJackPlayer, Board board) { // fonction qui modelise l appel a temoins
        ArrayList<AlibiName> visiblecharacters;
        visiblecharacters = board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard());

        District[][] districtBoard = board.getDistrictBoard();
        boolean iSVisibleByDetectives = DetectiveSeeJack(mrJackPlayer, board);
        if (iSVisibleByDetectives == false) { // si les detectives ne voient pas mr jack
            mainUI.showpopup("Verdict de l'appel à témoins : Mr Jack est invisible","Informations");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (visiblecharacters.contains(districtBoard[i][j].getCharacter())) {
                        districtBoard[i][j].setRecto(false);
                        //on innocente toutes les personnes qui sont dans la ligne de vue des detectives
                    }
                }
            }
            // on met a jour l affichage graphique
            mrJackPlayer.setHourglass(mrJackPlayer.getHourglass()+1);
            mainUI.updateHourglass(mrJackPlayer.getHourglass());
            mainUI.updateUIDistrict(board.getDistrictBoard());
        }
        else   if (iSVisibleByDetectives == true){ // si les detectives voient mr jack
            mainUI.showpopup("Verdict de l'appel à témoins : Mr Jack est visible","Informations");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!visiblecharacters.contains(districtBoard[i][j].getCharacter())) {
                        districtBoard[i][j].setRecto(false);
                        //on innocente toutes les personnes qui ne sont pas dans la ligne de vue des detectives
                    }
                }
            }
            mainUI.updateUIDistrict(board.getDistrictBoard());
            // on met a jour l affichage graphique
        }

        turnCount = turnCount + 1; // le numero de tour augmente de 1
        if (turnCount<=8){ // si les 8 tours n'ont pas encore été joué


            conditionSatistisfield(mrJackPlayer,board); // on appelle la fonction condition satisfield pour savoir si on continue la partie ou pas
        }
        else{ // si les 8 tours ont  encore été joué
            if ((MrJackConditionToWin(mrJackPlayer) == false)&&(DetectiveConditionToWin(board) == false)){ // si aucun des joueurs sn'a remplit sa condition pour gagner
                // mr jack gagne on montre au detective l identite de mr jack
                mainUI.showpopup("Jack gagne. Aucun des joueurs n'a atteint son objectif avant les 8 tours ","Fin du jeu");
                mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
            }
            else if((MrJackConditionToWin(mrJackPlayer) == true)&&(DetectiveConditionToWin(board) == true)){ // si a la fin du 8eme tour, tous les joueurs ont remplit leur conditions pour gagner
                if (iSVisibleByDetectives==true){ // si le detective voie mr jack
                    //detective gagne on montre au detective l identite de mr jack
                    mainUI.showpopup("Le detective gagne. Tous les joueurs ont atteint leur objectif mais jack était visible à la fin du jeu","Fin du jeu");
                    mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
                }
                else if (iSVisibleByDetectives==false){ // si le  detective ne voie pas mr jack
                    //mr jack gagne on montre au detective l identite de mr jack
                    mainUI.showpopup("Mr Jack gagne. Tous les joueurs ont atteint leur objectif mais jack était invisible à la fin du jeu","Fin du jeu");
                    mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
                }
            }

        }

    }


    public void conditionSatistisfield(MrJackPlayer mrJackPlayer,Board board) { // fonction qui determine si la partie est termine et si il faut faire une course poursuite
        boolean iSVisibleByDetectives = DetectiveSeeJack(mrJackPlayer, board);
        mainUI.updateUIDistrict(board.getDistrictBoard());
        if((MrJackConditionToWin(mrJackPlayer) == false)&&(DetectiveConditionToWin(board) == false)){ // si aucun des joueurs n'a atteint sa condition pour gagner
            mainUI.showpopup("debut du tour  "+turnCount,"Informations");
            if (turnCount % 2 == 0) { // si le turn count est pair
                tourPair(actions, mrJackPlayer, board); // on continue la partie avec un tour pair


            } else {  // si le turn count est impair

                tourImpair(actions, mrJackPlayer, board); // on continue la partie avec un tour impair
            }
        }
        else if((MrJackConditionToWin(mrJackPlayer) ==true)&&(DetectiveConditionToWin(board) == false)){ // si mr jack a remplie sa condition pour gagner
            //mr jack gagne on montre au detective l identite de mr jack
            mainUI.showpopup("Mr Jack gagne avec ses 6 sabliers","Fin du jeu");
            mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
        }
        else if((MrJackConditionToWin(mrJackPlayer) ==false)&&(DetectiveConditionToWin(board) == true)){ // si detective a remplie sa condition pour gagner
            //detective gagne on montre au detective l identite de mr jack
            mainUI.updateUIDistrict(board.getDistrictBoard());
            mainUI.showpopup("Le detective gagne","Fin du jeu");
            mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
        }
        else if ((MrJackConditionToWin(mrJackPlayer) ==true)&&(DetectiveConditionToWin(board) == true)){  // si les joueurs ont remplie leur condition pour gagner en meme temps

            if (iSVisibleByDetectives==true){ // si le  detective  voie mr jack
                //detective gagne on montre au detective l identite de mr jack
                mainUI.showpopup("Le detective gagne. Tous les joueurs ont atteint leur objectif mais jack a été découvert avant la fin du jeu","Fin du jeu");
                mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
            }
            else{ // si le  detective  ne voie pas mr jack
                // on lance la course poursuite
                mainUI.showpopup("Course poursuite du tour " +turnCount ,"Course poursuite");
                if (turnCount % 2 == 0) { // si le turn count est pair
                    tourPair(actions, mrJackPlayer, board); // on continue la partie avec un tour pair


                } else { // si le turn count est impair

                    tourImpair(actions, mrJackPlayer, board); // on continue la partie avec un tour impair
                }

            }
        }

    }


}


