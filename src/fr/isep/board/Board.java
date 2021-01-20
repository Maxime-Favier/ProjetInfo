package fr.isep.board;

import java.util.ArrayList;
import java.util.Random;

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

        //populate random district

       ArrayList<AlibiName> alibiNamesDistrict = new ArrayList<>();
        ArrayList<Orientation> orientationList = new ArrayList<>();
        for (int a = 0; a < AlibiName.values().length; a++) {
            alibiNamesDistrict.add(AlibiName.values()[a]);
        }


        orientationList.add(Orientation.EAST);
        orientationList.add(Orientation.WEST);
        orientationList.add(Orientation.NORTH);
        orientationList.add(Orientation.SOUTH);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                    Random orientationRan = new Random();
                    Random nameRan = new Random();
                    Orientation orientationDistrict = orientationList.get(orientationRan.nextInt(orientationList.size()));
                    AlibiName alibiDistrict = alibiNamesDistrict.get(nameRan.nextInt(alibiNamesDistrict.size()));
                    districtBoard[i][j] = new District(orientationDistrict, alibiDistrict);
                    alibiNamesDistrict.remove(alibiDistrict);
            }
        }
        districtBoard[0][0].setOrientation(Orientation.EAST);
        districtBoard[2][0].setOrientation(Orientation.WEST);
        districtBoard[1][2].setOrientation(Orientation.NORTH);
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

    public ArrayList<AlibiName> getVisibleCharacters(District[][] districtBoard, ArrayList<DetectiveToken>[] detectiveBoard) {
        ArrayList<AlibiName> listCharactersVisible = new ArrayList<>();


        for (int i = 0; i < detectiveBoard.length; i++) {
            if (detectiveBoard[i].size() != 0) {
                if ((i == 0) || (i == 1) || (i == 2)) {
                    for (int j = 0; j < 3; j++) {
                        if (districtBoard[i][j].getOrientation().equals(Orientation.WEST) || districtBoard[i][j].getOrientation().equals(Orientation.EAST) || districtBoard[i][j].getOrientation().equals(Orientation.CROSS)) {
                            if (!listCharactersVisible.contains((districtBoard[i][j].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[i][j].getCharacter());
                            }
                            continue;
                        }
                        if (districtBoard[i][j].getOrientation().equals(Orientation.NORTH)) {
                            if (!listCharactersVisible.contains((districtBoard[i][j].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[i][j].getCharacter());
                            }
                            break;
                        } else {
                            break;
                        }
                    }
                } else if ((i == 3) || (i == 4) || (i == 5)) {

                    for (int j = 0; j < 3; j++) {
                        if (districtBoard[2 - j][i % 3].getOrientation().equals(Orientation.SOUTH) || districtBoard[2 - j][i % 3].getOrientation().equals(Orientation.NORTH) || districtBoard[2 - j][i % 3].getOrientation().equals(Orientation.CROSS)) {

                            if (!listCharactersVisible.contains((districtBoard[2 - j][i % 3].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[2 - j][i % 3].getCharacter());
                            }

                            continue;
                        }
                        if (districtBoard[2 - j][i % 3].getOrientation().equals(Orientation.EAST)) {
                            if (!listCharactersVisible.contains((districtBoard[2 - j][i % 3].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[2 - j][i % 3].getCharacter());
                            }
                            break;
                        } else {
                            break;
                        }
                    }
                } else if ((i == 6) || (i == 7) || (i == 8)) {

                    for (int j = 0; j < 3; j++) {
                        if (districtBoard[2 - i % 3][2 - j].getOrientation().equals(Orientation.EAST) || districtBoard[2 - i % 3][2 - j].getOrientation().equals(Orientation.WEST) || districtBoard[2 - i % 3][2 - j].getOrientation().equals(Orientation.CROSS)) {

                            if (!listCharactersVisible.contains((districtBoard[2 - i % 3][2 - j].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[2 - i % 3][2 - j].getCharacter());
                            }
                            continue;
                        }
                        if (districtBoard[2 - i % 3][2 - j].getOrientation().equals(Orientation.SOUTH)) {
                            if (!listCharactersVisible.contains((districtBoard[2 - i % 3][2 - j].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[2 - i % 3][2 - j].getCharacter());
                            }
                            break;
                        } else {
                            break;
                        }
                    }
                } else {

                    for (int j = 0; j < 3; j++) {
                        if (districtBoard[j][2 - i % 3].getOrientation().equals(Orientation.SOUTH) || districtBoard[j][2 - i % 3].getOrientation().equals(Orientation.NORTH) || districtBoard[j][2 - i % 3].getOrientation().equals(Orientation.CROSS)) {
                            if (!listCharactersVisible.contains((districtBoard[j][2 - i % 3].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[j][2 - i % 3].getCharacter());
                            }
                            continue;
                        }
                        if (districtBoard[j][2 - i % 3].getOrientation().equals(Orientation.WEST)) {
                            if (!listCharactersVisible.contains((districtBoard[j][2 - i % 3].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[j][2 - i % 3].getCharacter());
                            }
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }

        }


        return listCharactersVisible;
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
