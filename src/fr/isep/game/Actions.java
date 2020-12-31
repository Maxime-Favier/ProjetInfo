package fr.isep.game;

import fr.isep.board.*;

import java.util.ArrayList;

public class Actions {
    private Board board;

    public Actions(Board board) {
        this.board = board;
    }

    public void moveDetective(DetectiveName detectiveName, int numberOfCase) {
        ArrayList<DetectiveToken>[] detectiveBoard = board.getDetectiveBoard();

        int caseArrayIndex = 0;
        int caseListIndex;
        DetectiveToken token;
        // search detective index
        for (int i = 0; i < detectiveBoard.length; i++) {
            for (int j = 0; j < detectiveBoard[i].size(); j++) {
                if (detectiveBoard[i].get(j).getDetectiveName() == detectiveName) {
                    caseArrayIndex = i;
                    token = detectiveBoard[i].get(j);
                    detectiveBoard[i].remove(j);
                    int newIndex = (caseArrayIndex + numberOfCase) % detectiveBoard.length;
                    detectiveBoard[newIndex].add(token);
                }
            }
        }
    }

    public void rotateDistrict(int x, int y, Orientation orientation){
        District[][] districtBoard = board.getDistrictBoard();
        districtBoard[x][y].setOrientation(orientation);
    }

    public void swapDistrict(int x1, int y1, int x2, int y2){
        District[][] districtBoard = board.getDistrictBoard();
        District tmp = districtBoard[x1][y1];
        districtBoard[x1][y1] = districtBoard[x2][y2];
        districtBoard[x2][y2] = tmp;
    }


}
