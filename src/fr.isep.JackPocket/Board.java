package fr.isep.JackPocket;

import java.util.ArrayList;
import java.util.Random;


public class Board {
    //District[][] board;
    //DetectiveToken[][] Board2;
    ArrayList<DetectiveToken> DetectiveList  ;
    Cell [][] cells;

    public Board() {
        initBoard();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
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

        // ArrayDeque<Alibi> alibis = new ArrayDeque<>();
        // district.add(New District(New Alibi()));
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

        ArrayList<DetectiveToken> DetectiveList= new ArrayList<DetectiveToken>();
      // DetectiveList.add(new DetectiveToken(DetectiveName.Watson));
       // System.out.println(districts);
        //Cell [][] board = new Cell[3][3];
        //for (int i = 0; i < 3; i++) {
          //  for (int j = 0; j < 3; j++) {
              //Random rand = new Random();
           // int randomIndex = rand.nextInt(districts.size());
           //  board[i][j] = districts.get(randomIndex);
           // districts.remove(randomIndex);
            //   }
        }

        //District[][] board = new District[3][3];
        //for (int i = 0; i < 3; i++) {
            //for (int j = 0; j < 3; j++) {
              //  Random rand = new Random();
               //int randomIndex = rand.nextInt(districts.size());
               // board[i][j] = districts.get(randomIndex);
                //districts.remove(randomIndex);
         //   }
     //  }

       //  board[0][0].setOrientation(Orientation.WEST);
         //board[0][2].setOrientation(Orientation.EAST);
         //board[2][1].setOrientation(Orientation.SOUTH);






       // DetectiveToken[][] Board2 = new DetectiveToken[5][5];

        //Board2[3][0] = new DetectiveToken(DetectiveName.Sherlock);
        //Board2[3][4] = new DetectiveToken(DetectiveName.Watson);
        //Board2[0][2] = new DetectiveToken(DetectiveName.Tobby);

        //créer un seul tableau ou deux combinés ?
        //position de départ détective aléatoire ou même position pour toutes les parties ?
        //est-ce qu'il faut créer une liste?
        //penser aux autres objet sur le board tel que carte alibi sablier etc...







        }









        public void addDetective (DetectiveToken detectiveName){
            if(!(DetectiveList.contains(detectiveName))){
                DetectiveList.add(detectiveName);
            }
        }
public void test (){
            initBoard();

}
        public void abstractDetective (DetectiveToken detectiveName){
            if(DetectiveList.contains(detectiveName)){
                DetectiveList.remove(detectiveName);

            }
        }


    public void moveDetectiveToken(DetectiveName detectiveName, int boardcount){


    }





}



