package fr.isep.game;

import fr.isep.board.*;

import java.util.ArrayList;

public class Actions {
    private Board board;
    private ActionToken lastActionPlayed;

    public Actions(Board board) {
        this.board = board;
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

    }

    public void rotateDistrict(int x, int y, Orientation orientation){
        District[][] districtBoard = board.getDistrictBoard();
        districtBoard[x][y].setOrientation(orientation);
        board.setDistrictBoard(districtBoard);
    }

    public void swapDistrict(int x1, int y1, int x2, int y2){
        District[][] districtBoard = board.getDistrictBoard();
        District tmp = districtBoard[x1][y1];
        districtBoard[x1][y1] = districtBoard[x2][y2];
        districtBoard[x2][y2] = tmp;
        board.setDistrictBoard(districtBoard);
    }

    public ActionToken getLastActionPlayed() {
        return lastActionPlayed;
    }

    public void setLastActionPlayed(ActionToken lastActionPlayed) {
        this.lastActionPlayed = lastActionPlayed;
    }
}
