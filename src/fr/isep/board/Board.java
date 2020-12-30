package fr.isep.board;

import java.util.ArrayList;

public class Board {
    District[][] districtBoard;
    ArrayList<DetectiveToken>[] detectiveBoard;

    public Board() {
        initDistricts();
        initDetective();
    }

    private void initDistricts() {
        System.out.println("init districts...");

        districtBoard = new District[3][3];

        // populate board
        districtBoard[0][0] = new District(Orientation.SOUTH, AlibiName.JEREMY_BART);
        districtBoard[1][0] = new District(Orientation.NORTH, AlibiName.JOSEPH_LANG);
        districtBoard[2][0] = new District(Orientation.NORTH, AlibiName.MISS_STEALTHY);

        districtBoard[0][1] = new District(Orientation.EAST, AlibiName.JOHN_PIZER);
        districtBoard[1][1] = new District(Orientation.NORTH, AlibiName.LESTRADE);
        districtBoard[2][1] = new District(Orientation.NORTH, AlibiName.SERGENT_GOODLEY);

        districtBoard[0][2] = new District(Orientation.WEST, AlibiName.JOHN_SMITH);
        districtBoard[1][2] = new District(Orientation.NORTH, AlibiName.MADAME);
        districtBoard[2][2] = new District(Orientation.NORTH, AlibiName.WILLIAM_GULL);
    }

    private void initDetective() {
        System.out.println("init detective...");

        detectiveBoard = new ArrayList[12];
        for (int i = 0; i < 12; i++) {
            detectiveBoard[i] = new ArrayList<DetectiveToken>();
        }

        //populate detectives
        detectiveBoard[3].add(new DetectiveToken(DetectiveName.WATSON));
        detectiveBoard[7].add(new DetectiveToken(DetectiveName.TOBBY));
        detectiveBoard[11].add(new DetectiveToken(DetectiveName.SHERLOCK));

    }


    public District[][] getDistrictBoard() {
        return districtBoard;
    }

    public void setDistrictBoard(District[][] districtBoard) {
        this.districtBoard = districtBoard;
    }

    public ArrayList<DetectiveToken>[] getDetectiveBoard() {
        return detectiveBoard;
    }

    public void setDetectiveBoard(ArrayList<DetectiveToken>[] detectiveBoard) {
        this.detectiveBoard = detectiveBoard;
    }
}
