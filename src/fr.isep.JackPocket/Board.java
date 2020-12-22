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
        detectives.add(new Detective(DetectiveName.Sherlock,0,0,Orientation.NORTH));
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
        board [0][2].setDetectiveTokens(new DetectiveToken(DetectiveName.Watson,null,null,Orientation.NORTH));
        board [0][0].setDetectiveTokens(new DetectiveToken(DetectiveName.Sherlock,null,null,Orientation.EAST));
        board [1][2].setDetectiveTokens(new DetectiveToken(DetectiveName.Tobby,null,null,Orientation.SOUTH));
        board[0][0].district.setOrientation(Orientation.WEST);
        board[0][2].district.setOrientation(Orientation.EAST);
        board[2][1].district.setOrientation(Orientation.SOUTH);



       //getVisibleCharacters(detectives,board);
        moveDetectiveTokenTwo(DetectiveName.Tobby,detectives);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                System.out.print(board[i][j].getDistrict().getAlibiCards().getName()+ "  "+ board[i][j].getDistrict().getOrientation()+"     ");


            }
            System.out.println();
        }


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
        System.out.print("test");
    }

    public void moveDetectiveTokenOne(DetectiveName name, int count,ArrayList<Detective> detectives){
        int index=0;
        int detectivePositionX=0;
        int detectivePositionY=0;
        Orientation detectiveOrientation=null;
        for (int i=0;i<detectives.size();i++){
            if (detectives.get(i).getName().equals(name)){
                index=i;
                detectivePositionX=detectives.get(i).getPositionX();
                detectivePositionY=detectives.get(i).getPositionY();
                detectiveOrientation=detectives.get(i).getOrientationDetective();
            }

        }
        System.out.println ("tobby x = "+ detectivePositionX);
        System.out.println ("tobby y = "+ detectivePositionY);
        System.out.println ("orientation = "+ detectiveOrientation);
        if (count==1){
            if (detectiveOrientation.equals(Orientation.NORTH)){
                if (detectivePositionX<2){
                    detectivePositionX=(detectivePositionX+1);
                }
                else{
                    detectiveOrientation=(Orientation.EAST);
                    detectivePositionX=(2);
                    detectivePositionY=0;
                }
            }

            if (detectiveOrientation.equals(Orientation.EAST)){
                if (detectivePositionY<2){
                   detectivePositionY=(detectivePositionY+1);
                }
                else{
                   detectiveOrientation=(Orientation.SOUTH);
                   detectivePositionX=2;
                detectivePositionY=(2);
                }
            }

            if (detectiveOrientation.equals(Orientation.SOUTH)){
                if (detectivePositionX>0){
                    detectivePositionX=(detectivePositionX-1);
                    System.out.println("condition 1");
                }
                else{
                    System.out.println("condition 2 mon orientation avant"+detectiveOrientation);
                    detectiveOrientation=(Orientation.WEST);
                   detectivePositionX=(0);
                    detectivePositionY=(2);

                    System.out.println("condition 2 mon orientation avant"+detectiveOrientation);
                }
            }
            if (detectiveOrientation.equals(Orientation.WEST)){
                if (detectivePositionY>0){
                    detectivePositionY=(detectivePositionY-1);
                }
                else{
                    detectiveOrientation=(Orientation.NORTH);
                    detectivePositionX=(0);
                     detectivePositionY=(0);
                }
            }

        }

        detectives.get(index).setPositionX(detectivePositionX);
        detectives.get(index).setPositionY(detectivePositionY);
        detectives.get(index).setOrientationDetective(detectiveOrientation);
        System.out.println( "podition x final = " +detectives.get(index).getPositionX());
        System.out.println( "podition y final  = "+ detectives.get(index).getPositionY());
        System.out.println("mon orientation final"+detectives.get(index).getOrientationDetective());
    }
        public void moveDetectiveTokenTwo (DetectiveName name,ArrayList<Detective> detectives){
            int index=0;
            int detectivePositionX=0;
            int detectivePositionY=0;
            Orientation detectiveOrientation;
            for (int i=0;i<detectives.size();i++){
                if (detectives.get(i).getName().equals(name)){
                    index=i;
                    detectivePositionX=detectives.get(i).getPositionX();
                    detectivePositionY=detectives.get(i).getPositionY();
                    detectiveOrientation=detectives.get(i).getOrientationDetective();
                }

               // if ((detectivePositionX==0 && detectivePositionY==0)||(detectivePositionX==2 && detectivePositionY==2)||(detectivePositionX==0 && detectivePositionY==2)||(detectivePositionX==2 && detectivePositionY==0))
                //{
                  //  moveDetectiveTokenOne(name, 1,detectives);
                //}
              //  else
                //{
                    moveDetectiveTokenOne(name, 1,detectives);
                    moveDetectiveTokenOne(name, 1,detectives);
                    break;
                //}


        }
        }

    public List<AlibiName> getVisibleCharacters(ArrayList<Detective> detectives,Cell[][] board)
    {
       // board[2][1].district.setOrientation(Orientation.WEST);
       // board[1][1].district.setOrientation(Orientation.NORTH);
        ArrayList<AlibiName> listCharactersVisible = new ArrayList<>();
        //int tobbyPositionX= detectives.get(2).getPositionX();
        //int tobbyPositionY= detectives.get(2).getPositionY();
       //int tobbyPositionX=0;
        //int tobbyPositionY=0;
       // detectives.get(2).setOrientationDetective(Orientation.NORTH);
        int Position=0;
        //Orientation tobbyOrientation=detectives.get(2).getOrientationDetective();
       // System.out.println("tobby orientation ="+detectives.get(2).getOrientationDetective());
        //System.out.println(board[2][1].district.getOrientation());
        for (int p=0;p<detectives.size();p++){
        if ((detectives.get(p).getOrientationDetective().equals(Orientation.EAST)))
        {
            Position=detectives.get(p).getPositionY();
           // System.out.println("tobby posi"+ Position);
            for (int i =0; i <3; i++)
            {System.out.println("i" +i);

                if (!board [Position][2-i].district.getOrientation().equals(detectives.get(p).getOrientationDetective()))
                {

                    listCharactersVisible.add(board[Position][2-i].district.getAlibiCards().getName());
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
           // System.out.println("tobby posi"+ Position);
            for (int i =0; i <3; i++)
            {System.out.println("i" +i);

                if (!board [Position][i].district.getOrientation().equals(detectives.get(p).getOrientationDetective()))
                {

                    listCharactersVisible.add(board[Position][i].district.getAlibiCards().getName());
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
            {System.out.println("i" +i);
            if (!board [2-i][Position].district.getOrientation().equals(detectives.get(p).getOrientationDetective()))
            {

                listCharactersVisible.add(board[2-i][Position].district.getAlibiCards().getName());
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
           System.out.println("sherlock");
            for (int i =0; i <3; i++)
            {System.out.println("i" +i);
                if (!board [i][Position].district.getOrientation().equals(detectives.get(p).getOrientationDetective()))
                {

                    listCharactersVisible.add(board[i][Position].district.getAlibiCards().getName());
                }
                else
                {
                    break;
                }


            }
        // a implementer occurence

        }
            System.out.println(listCharactersVisible);
        }
        for(int m=0; m<detectives.size();m++)
        {

        }

        return listCharactersVisible;
    }

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




