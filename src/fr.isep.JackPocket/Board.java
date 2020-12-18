package fr.isep.JackPocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Board {
    //District[][] board;
    DetectiveToken[][] detectiveTokens;
    ArrayList<DetectiveToken> DetectiveList  ;
    Cell [][] board;

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
        ArrayList<District> districts = new ArrayList<>();
        //ArrayList<DetectiveToken> DetectiveList= new ArrayList<>();
        //DetectiveList.add(new DetectiveToken(DetectiveName.Watson,null,null,Orientation.EAST));
        //DetectiveList.add(new DetectiveToken(DetectiveName.Sherlock,null,null,Orientation.WEST));
        //DetectiveList.add(new DetectiveToken(DetectiveName.Tobby,null,null,Orientation.SOUTH));
        // ArrayDeque<Alibi> alibis = new ArrayDeque<>();
        // district.add(New District(New Alibi()));
        /*
        DetectiveToken [][] detectiveTokens = new DetectiveToken[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                detectiveTokens[i][j]=new DetectiveToken(null,null,null,null);
            }
            }
        detectiveTokens[0][1]=new DetectiveToken(DetectiveName.Watson,null,null,Orientation.EAST);
        detectiveTokens[4][1]=new DetectiveToken(DetectiveName.Sherlock,null,null,Orientation.WEST);
        detectiveTokens[2][4]=new DetectiveToken(DetectiveName.Tobby,null,null,Orientation.SOUTH);

*/


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


       // System.out.println(districts);
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
       System.out.println((board[0][2].getDetectiveTokens().getNomDetective1()));
/*
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("i = "+i +"j = "+j);

               if ((board[i][j].getDetectiveTokens().getNomDetective1())!=null)
               {
                 System.out.println((board[i][j].getDetectiveTokens().getNomDetective1()));
               }
                //System.out.println(board[i][j].getDetectiveTokens().getNomDetective2());
                //System.out.println(board[i][j].getDetectiveTokens().getNomDetective3());
                //System.out.println(board[i][j].getDetectiveTokens().getOrientationDetective());

              //  System.out.println(board[i][j].getDistrict().getalibiCards());
            }
        }






       // DetectiveToken[][] Board2 = new DetectiveToken[5][5];

        //Board2[3][0] = new DetectiveToken(DetectiveName.Sherlock);
        //Board2[3][4] = new DetectiveToken(DetectiveName.Watson);
        //Board2[0][2] = new DetectiveToken(DetectiveName.Tobby);

        //créer un seul tableau ou deux combinés ?
        //position de départ détective aléatoire ou même position pour toutes les parties ?
        //est-ce qu'il faut créer une liste?
        //penser aux autres objet sur le board tel que carte alibi sablier etc...







        //}







/*
        public void addDetective (DetectiveToken detectiveName){
            if(!(DetectiveList.contains(detectiveName))){
                DetectiveList.add(detectiveName);
            }
        }


 */

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

}


