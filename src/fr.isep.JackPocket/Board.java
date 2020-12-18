package fr.isep.JackPocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Board {
    //District[][] board;
    DetectiveToken[][] detectiveTokens;
    ArrayList<DetectiveToken> DetectiveList  ;
    Cell [][] board;
    ArrayList<Detective> detectives;

    public Board() {
        initBoard();
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    //public District[][] getBoard() {
      //  return board;
    //}


    //public void setBoard(District[][] board) {
      //  this.board = board;
    //}
    //public DetectiveToken[][] getBoard2(){
      //  return Board2;
    //}
    //public void setBoard2(DetectiveToken[][] Board2){
      //  this.Board2 = Board2;
    //}

    public void initBoard() {





        ArrayList<Detective> detectives  = new ArrayList<>();
        detectives.add(new Detective(DetectiveName.Watson,0,2,Orientation.EAST));
        detectives.add(new Detective(DetectiveName.Sherlock,0,0,Orientation.WEST));
        detectives.add(new Detective(DetectiveName.Tobby,1,2,Orientation.SOUTH));

        ArrayList<District> districts = new ArrayList<>();



        districts.add(new District(new AlibiCard(AlibiName.Inspecteur_Lestrade, 0),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false, false));
        districts.add(new District(new AlibiCard(AlibiName.Miss_Stealthy, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false, false));
        districts.add(new District(new AlibiCard(AlibiName.Jeremy_Bert, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false, false));
        districts.add(new District(new AlibiCard(AlibiName.John_Pizer, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false, false));
        districts.add(new District(new AlibiCard(AlibiName.John_Smith, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false, false));
        districts.add(new District(new AlibiCard(AlibiName.Joseph_Lane, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false, false));
        districts.add(new District(new AlibiCard(AlibiName.Madame, 2),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false, false));
        districts.add(new District(new AlibiCard(AlibiName.Sgt_Goodley, 0),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false, false));
        districts.add(new District(new AlibiCard(AlibiName.William_Gull, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false, false));

        Cell [][] board = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
              Random rand = new Random();
           int randomIndex = rand.nextInt(districts.size());
            board[i][j] = new Cell(null,districts.get(randomIndex));

            districts.remove(randomIndex);
              }
        }
        board [0][2].setDetectiveTokens(new DetectiveToken(DetectiveName.Watson,null,null,Orientation.EAST));
        board [0][0].setDetectiveTokens(new DetectiveToken(DetectiveName.Sherlock,null,null,Orientation.WEST));
        board [1][2].setDetectiveTokens(new DetectiveToken(DetectiveName.Tobby,null,null,Orientation.SOUTH));
        board[0][0].district.setOrientation(Orientation.WEST);
        board[0][2].district.setOrientation(Orientation.EAST);
        board[2][1].district.setOrientation(Orientation.SOUTH);


     //   getVisibleCharacters(detectives,board);
    }

    public void swap(District district1, District district2){
        District district3=new District(new AlibiCard(AlibiName.John_Smith, 3),Orientation.EAST,true,true);
        district3.setAlibiCards(district1.getAlibiCards());
        district3.setOrientation(district1.getOrientation());
        district3.setRecto(district1.isRecto());
        district3.setVisible(district1.isVisible());

        district1.setAlibiCards(district2.getAlibiCards());
        district1.setOrientation(district2.getOrientation());
        district1.setRecto(district2.isRecto());
        district1.setVisible(district2.isVisible());

        district2.setAlibiCards(district3.getAlibiCards());
        district2.setOrientation(district3.getOrientation());
        district2.setRecto(district3.isRecto());
        district2.setVisible(district3.isVisible());

    }
/*
    public List<AlibiName> getVisibleCharacters(ArrayList<Detective> detectives,Cell[][] board)
    {
        ArrayList<AlibiName> listCharactersVisible = new ArrayList<>();
        int tobbyPositionX= detectives.get(2).getPositionX();
        int tobbyPositionY= detectives.get(2).getPositionY();
        int tobbyPosition=0;
        Orientation tobbyOrientation=detectives.get(2).getOrientationDetective();
        if ((tobbyOrientation.equals(Orientation.EAST)||tobbyOrientation.equals(Orientation.WEST)))

        {
            tobbyPosition=tobbyPositionY;
            for (int i = 0; i < 3; i++)
            {
                if (board [i][tobbyPosition].district.getOrientation().equals(detectives.get(2).getOrientationDetective()))
                {
                    listCharactersVisible.add(board[i][tobbyPosition].district.getAlibiCards().getName());
                }


            }
            System.out.println(tobbyPosition);
            System.out.println(listCharactersVisible);
        }
        else
        {
            tobbyPosition=tobbyPositionX;
            for (int i = 0; i < 3; i++)
            {
            if (board [tobbyPosition][i].district.getOrientation().equals(detectives.get(2).getOrientationDetective()))
            {
                listCharactersVisible.add(board[tobbyPosition][i].district.getAlibiCards().getName());
            }
            System.out.println(tobbyPosition);
                System.out.println(listCharactersVisible);
        }

        }

        return listCharactersVisible;
    }

*/
        public void addDetective (DetectiveToken detectiveName){
            if(!(DetectiveList.contains(detectiveName))){
                DetectiveList.add(detectiveName);
            }
        }




/*
        public void abstractDetective (DetectiveToken detectiveName){
            if(DetectiveList.contains(detectiveName)){
                DetectiveList.remove(detectiveName);

            }
        }


    public void moveDetectiveToken(DetectiveName detectiveName, int boardcount){


    }


*/


}




