package fr.isep.game;

import fr.isep.board.*;
import fr.isep.game.*;
import fr.isep.ui.MainUI;

import java.util.ArrayList;
import java.util.Random;

public class Actions { // classe où est implémenté la majorité des actions possibles
    public ActionToken actionTokenPlayed;
    private Board board;
    private ActionToken lastActionPlayed;
    private int actionTodo;


    public Actions(Board board) {
        this.board = board;
    }

    public void actionPlayed() {

    }


    public void moveDetective(DetectiveName detectiveName, int numberOfCase) {
        // fonction qui permet de deplacer des detectives, a pour attribut le nom du detective à deplacer et le nombres de cases qu'il se déplace
        ArrayList<DetectiveToken>[] detectiveBoard = board.getDetectiveBoard();

        int caseArrayIndex = 0;
        int caseListIndex = 0;
        boolean found = false;
        DetectiveToken token;
        // on cherche l'index du detective dans le detective
        for (int i = 0; i < detectiveBoard.length; i++) { // on parcourt tout le tableau
            for (int j = 0; j < detectiveBoard[i].size(); j++) {
                if (detectiveBoard[i].get(j).getDetectiveName() == detectiveName) {
                    //System.out.println("detective found in " + i + " - " + j);
                    caseArrayIndex = i;
                    caseListIndex = j;
                    found = true; // on a trouve le detective
                }
            }
        }
        if (found) { // si le detective a été trouvé
            token = detectiveBoard[caseArrayIndex].get(caseListIndex);
            detectiveBoard[caseArrayIndex].remove(caseListIndex); // on supprime le detective de son ancien emplacement
            int newIndex = (caseArrayIndex + numberOfCase) % detectiveBoard.length; // nouvelle position du detective selon le nombre de cases qu'il se deplace
            detectiveBoard[newIndex].add(token);
            board.setDetectiveBoard(detectiveBoard);
        } else {
            System.out.println("detective not found");
        }
        // faire un action token played

    }

    public void rotateDistrict(int x, int y, Orientation orientation) { //fonction qui permet de faire pivoter un district
        District[][] districtBoard = board.getDistrictBoard();
        districtBoard[x][y].setOrientation(orientation);
        board.setDistrictBoard(districtBoard);
        actionTokenPlayed = ActionToken.ECHANGE;
    }

    public void swapDistrict(int x1, int y1, int x2, int y2) { //fonction qui permet d'échanger deux districts
        District[][] districtBoard = board.getDistrictBoard();
        District tmp = districtBoard[x1][y1];
        districtBoard[x1][y1] = districtBoard[x2][y2];
        districtBoard[x2][y2] = tmp;
        board.setDistrictBoard(districtBoard);
        actionTokenPlayed = ActionToken.ECHANGE;
    }

    public void alibi(int whoPlay, Game game, MrJackPlayer mrJackPlayer, Board board, MainUI mainUI) { //fonction qui correspond a l'action token alibi
        District[][] districtBoard = board.getDistrictBoard();
        ArrayList<AlibiCard> alibicards;
        alibicards = game.alibiCards;
        Random random = new Random();
        int pioche = random.nextInt(alibicards.size() );
        AlibiCard carte = alibicards.get(pioche);   // on pioche aleatoirement une alibi carte
        if (whoPlay == 1) { //si le joueur est mr jack
            int sablierAlibi;
            sablierAlibi = alibicards.get(pioche).getHourGlassCount();
            mrJackPlayer.setHourglass(mrJackPlayer.getHourglass() + sablierAlibi);  // mr jack recupere les sabliers de la carte qu'il a pioché
            mainUI.showpopup("Attention l'information qui va s'afficher par la suite ne concerne que Mr Jack : detectives, fermez vos yeux","Information relative à Mr Jack");
            mainUI.showMrJackAlibiCard(alibicards.get(pioche));
            alibicards.remove(alibicards.get(pioche)); //on supprime la carte de la pioche
            mainUI.updateHourglass(mrJackPlayer.getHourglass()); // on main a jour l'affichage graphique
        } else {
            AlibiCard carteEnqueteur; //si le joueur est l enqueteur
            carteEnqueteur = alibicards.get(pioche); // on pioche aleatoirement une alibi carte


            for (int i = 0; i < 3; i++) { // on parcourt les districts
                for (int j = 0; j < 3; j++) {
                    if (districtBoard[i][j].getCharacter().equals(carteEnqueteur.getName())) { // si le joueur est toujours suspect
                        districtBoard[i][j].setRecto(false); // on l'innocente : sa face district vaut false
                    }
                }
            }
            mainUI.updateUIDistrict(districtBoard);
            mainUI.addAlibi(carte); // on main a jour l'affichage graphique
            alibicards.remove(alibicards.get(pioche)); //on supprime la carte de la pioche

        }

    }

    public ActionToken getLastActionPlayed() {
        return lastActionPlayed;
    } // permet de connaitre la derniere action qui a été joué

    public void setLastActionPlayed(ActionToken lastActionPlayed) { // permet de modifier la derniere action qui a été joué
        this.lastActionPlayed = lastActionPlayed;
    }

    public void setActionTodo(int actionTodo) { // permet de modifier le nombre d'actions qu'il reste à faire pendant un tour
        this.actionTodo = actionTodo;
    }

    public int getActionTodo() { // permet de connaitre le nombre d'actions qu'il reste à faire pendant un tour
        return actionTodo;
    }
}
