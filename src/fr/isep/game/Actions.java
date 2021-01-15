package fr.isep.game;

import fr.isep.board.*;
import fr.isep.game.*;
import java.util.ArrayList;
import java.util.Random;

public class Actions {
    public ActionToken actionTokenPlayed;
    private Board board;
    private ActionToken lastActionPlayed;


    public Actions(Board board) {
        this.board = board;
    }

    public void actionPlayed(){

    }



    public void moveDetective(DetectiveName detectiveName, int numberOfCase) {
        ArrayList<DetectiveToken>[] detectiveBoard = board.getDetectiveBoard();

        int caseArrayIndex = 0;
        int caseListIndex = 0;
        boolean found = false;
        DetectiveToken token;
        // search detective index
        for (int i = 0; i < detectiveBoard.length; i++) {
            for (int j = 0; j < detectiveBoard[i].size(); j++) {
                if (detectiveBoard[i].get(j).getDetectiveName() == detectiveName) {
                    //System.out.println("detective found in " + i + " - " + j);
                    caseArrayIndex = i;
                    caseListIndex = j;
                    found = true;
                }
            }
        }
        if(found){
            token = detectiveBoard[caseArrayIndex].get(caseListIndex);
            detectiveBoard[caseArrayIndex].remove(caseListIndex);
            int newIndex = (caseArrayIndex + numberOfCase) % detectiveBoard.length;
            detectiveBoard[newIndex].add(token);
            board.setDetectiveBoard(detectiveBoard);
        }else {
            System.out.println("detective not found");
        }
       // faire un action token played

    }

    public void rotateDistrict(int x, int y, Orientation orientation){
        District[][] districtBoard = board.getDistrictBoard();
        districtBoard[x][y].setOrientation(orientation);
        board.setDistrictBoard(districtBoard);
        actionTokenPlayed=ActionToken.ECHANGE;
    }

    public void swapDistrict(int x1, int y1, int x2, int y2){
        District[][] districtBoard = board.getDistrictBoard();
        District tmp = districtBoard[x1][y1];
        districtBoard[x1][y1] = districtBoard[x2][y2];
        districtBoard[x2][y2] = tmp;
        board.setDistrictBoard(districtBoard);
        actionTokenPlayed=ActionToken.ECHANGE;
    }

    public void alibi(int whoPlay,Game game,MrJackPlayer mrJackPlayer, Board board){
        District[][] districtBoard = board.getDistrictBoard();
        ArrayList<AlibiCard>  alibicards ;
        ArrayList<AlibiName> suspects;
        alibicards= game.alibiCards;
        suspects=game.suspectsRestants;
        Random random=new Random();
        int pioche = random.nextInt(alibicards.size()+1);
       if (whoPlay==0){ // detective qui joue
           int sablierAlibi;
           sablierAlibi=alibicards.get(pioche).getHourGlassCount();
            mrJackPlayer.setHourglass(mrJackPlayer.getHourglass()+sablierAlibi);
            alibicards.remove(alibicards.get(pioche));
       }
       else{
           AlibiCard carteEnqueteur;
           carteEnqueteur=alibicards.get(pioche);
           int colonnes=0;
           int lignes=0;
           if(suspects.contains(carteEnqueteur.getName()))
           {
               for (int i=0;i<3;i++){
                   for (int j=0;j<3;j++){
                    if (districtBoard[i][j].getCharacter().equals(carteEnqueteur.getName()))
                    {
                        colonnes=i;
                        lignes=j;
                    }
                   }
               }
               districtBoard[colonnes][lignes].setRecto(false);

           }
           alibicards.remove(alibicards.get(pioche));
       }

        }

    public ActionToken getLastActionPlayed() {
        return lastActionPlayed;
    }

    public void setLastActionPlayed(ActionToken lastActionPlayed) {
        this.lastActionPlayed = lastActionPlayed;
    }
}
