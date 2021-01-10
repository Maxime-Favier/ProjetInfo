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

    public ArrayList<AlibiName> getVisibleCharacters( District[][] districtBoard,ArrayList <DetectiveToken>[] detectiveBoard)
    {
        ArrayList<AlibiName> listCharactersVisible = new ArrayList<>();

        int caseArrayIndex = 0;
        int caseListIndex = 0;
        boolean found = false;

        for (int i = 0; i < detectiveBoard.length; i++) {
            if (detectiveBoard[i].size()!=0){
                if ((i==0)||(i==1)||(i==2)){
                    for(int j=0;j<3;j++){
                    if (districtBoard [i][j].getOrientation().equals(Orientation.WEST)||districtBoard [i][j].getOrientation().equals(Orientation.EAST)||districtBoard [i][j].getOrientation().equals(Orientation.CROSS))
                     {
                         if (!listCharactersVisible.contains((districtBoard[i][j].getCharacter()))) {
                             listCharactersVisible.add(districtBoard[i][j].getCharacter());
                         }
                         continue;
                    }
                    if (districtBoard [i][j].getOrientation().equals(Orientation.NORTH))
                    {
                        if (!listCharactersVisible.contains((districtBoard[i][j].getCharacter()))) {
                            listCharactersVisible.add(districtBoard[i][j].getCharacter());
                        }
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
            }
                else if ((i==3)||(i==4)||(i==5))  {

                    for(int j=0;j<3;j++){
                    if (districtBoard [2-j][i%3].getOrientation().equals(Orientation.SOUTH)||districtBoard [2-j][i%3].getOrientation().equals(Orientation.NORTH)||districtBoard [2-j][i%3].getOrientation().equals(Orientation.CROSS))
                    {

                        if (!listCharactersVisible.contains((districtBoard[2-j][i%3].getCharacter()))) {
                            listCharactersVisible.add(districtBoard[2-j][i%3].getCharacter());
                        }

                        continue;
                    }
                    if (districtBoard [2-j][i%3].getOrientation().equals(Orientation.EAST))
                    {
                        if (!listCharactersVisible.contains((districtBoard[2-j][i%3].getCharacter()))) {
                            listCharactersVisible.add(districtBoard[2-j][i%3].getCharacter());
                        }
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
            }
                else if ((i==6)||(i==7)||(i==8))  {

                    for(int j=0;j<3;j++){
                        if (districtBoard [2-i%3][2-j].getOrientation().equals(Orientation.EAST)||districtBoard  [2-i%3][2-j].getOrientation().equals(Orientation.WEST)||districtBoard  [2-i%3][2-j].getOrientation().equals(Orientation.CROSS))
                        {

                            if (!listCharactersVisible.contains((districtBoard[2-i%3][2-j].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[2-i%3][2-j].getCharacter());
                            }
                            continue;
                        }
                        if (districtBoard  [2-i%3][2-j].getOrientation().equals(Orientation.SOUTH))
                        {
                            if (!listCharactersVisible.contains((districtBoard[2-i%3][2-j].getCharacter()))) {
                            listCharactersVisible.add(districtBoard[2-i%3][2-j].getCharacter());
                        }
                            break;
                        }
                        else
                        {
                            break;
                        }
                    }
                }

                else  {

                    for(int j=0;j<3;j++){
                        if (districtBoard [j][2-i%3].getOrientation().equals(Orientation.SOUTH)||districtBoard  [j][2-i%3].getOrientation().equals(Orientation.NORTH)||districtBoard  [j][2-i%3].getOrientation().equals(Orientation.CROSS))
                        {
                            if (!listCharactersVisible.contains((districtBoard[j][2-i%3].getCharacter()))) {
                                listCharactersVisible.add(districtBoard[j][2-i%3].getCharacter());
                            }
                            continue;
                        }
                        if (districtBoard  [j][2-i%3].getOrientation().equals(Orientation.WEST))
                        {
                            if (!listCharactersVisible.contains((districtBoard[j][2-i%3].getCharacter()))) {
                            listCharactersVisible.add(districtBoard[j][2-i%3].getCharacter());
                        }
                            break;
                        }
                        else
                        {
                            break;
                        }
                    }
                }
        }
        System.out.println("test");
        }

        /*
        int Position=0;
        for (int p=0;p<detectives.size();p++){
            if ((detectives.get(p).getOrientationDetective().equals(Orientation.EAST)))
            {
                Position=detectives.get(p).getPositionY();
                for (int i =0; i <3; i++)
                {

                    if (board [Position][2-i].district.getOrientation().equals(Orientation.SOUTH)||((board [Position][2-i].district.getOrientation().equals(Orientation.NORTH))))
                    {
                        listCharactersVisible.add(board[Position][2-i].district.getAlibiCards().getName());
                        continue;
                    }
                    else if(board [Position][2-i].district.getOrientation().equals(Orientation.WEST))
                    {
                        listCharactersVisible.add(board[Position][2-i].district.getAlibiCards().getName());
                        break;
                    }
                    else
                    {
                        break;
                    }

                }

            }
            if ((detectives.get(p).getOrientationDetective().equals(Orientation.WEST)))
            {
                Position=detectives.get(p).getPositionY();
                for (int i =0; i <3; i++)
                {
                    if (board [Position][i].district.getOrientation().equals(Orientation.SOUTH)||board [Position][i].district.getOrientation().equals(Orientation.NORTH))
                    {

                        listCharactersVisible.add(board[Position][i].district.getAlibiCards().getName());
                    }
                    else if(board [Position][i].district.getOrientation().equals(Orientation.EAST))
                    {
                        listCharactersVisible.add(board[Position][i].district.getAlibiCards().getName());
                        break;
                    }
                    else
                    {
                        break;
                    }


                }

            }
            else if (detectives.get(p).getOrientationDetective().equals(Orientation.SOUTH))
            {
                Position=detectives.get(p).getPositionX();
                for (int i =0; i <3; i++)
                {
                    if (board [2-i][Position].district.getOrientation().equals(Orientation.WEST)||(board [2-i][Position].district.getOrientation().equals(Orientation.EAST)))
                    {

                        listCharactersVisible.add(board[2-i][Position].district.getAlibiCards().getName());
                    }
                    else if(board [2-i][Position].district.getOrientation().equals(Orientation.NORTH))
                    {
                        listCharactersVisible.add(board[2-i][Position].district.getAlibiCards().getName());
                        break;
                    }
                    else
                    {
                        break;
                    }


                }


            }
            else if (detectives.get(p).getOrientationDetective().equals(Orientation.NORTH))
            {
                Position=detectives.get(p).getPositionX();
                for (int i =0; i <3; i++)
                {
                    if (board [i][Position].district.getOrientation().equals(Orientation.WEST)||board [i][Position].district.getOrientation().equals(Orientation.EAST))
                    {

                        listCharactersVisible.add(board[i][Position].district.getAlibiCards().getName());
                    }
                    else if(board [i][Position].district.getOrientation().equals(Orientation.SOUTH))
                    {
                        listCharactersVisible.add(board[i][Position].district.getAlibiCards().getName());
                        break;
                    }
                    else
                    {
                        break;
                    }



                }

            }

         */
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
