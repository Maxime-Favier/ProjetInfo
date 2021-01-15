package fr.isep.game;

import fr.isep.board.AlibiName;
import fr.isep.board.Board;
import fr.isep.board.District;
import fr.isep.ui.MainUI;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public ArrayList<AlibiCard> alibiCards;
    private ArrayList<ActionCard> actionCards;
    private MainUI mainUI;
    public int turnCount;
    public int whoPlay;
    public ActionToken actionChoose;
    public Game() {
        play();

    }

    public void play() {
        System.out.println("Game Playing");

        Board board = new Board();

        initActionCard();
        initAlibiCard();

        MrJackPlayer mrJackPlayer =new MrJackPlayer (null,0,false);
        mrJackPlayer.setJackAlibiName(pickIdentityJack());
        DetectivePlayer detectivePlayer =new DetectivePlayer();
        Actions actions = new Actions(board);
        mainUI = new MainUI(actions, board);
        System.out.println(board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard()));

        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.updateUIDetective(board.getDetectiveBoard());

        mainUI.showMrJackName(mrJackPlayer.getJackAlibiName());
        turnCount=1;
        whoPlay=0;

        turn();


    }

    public void turn(){
        ArrayList<ActionToken> actionTokensPair= new ArrayList<>();
        ArrayList<ActionToken> actionTokensImpair= new ArrayList<>();

        if(turnCount%2==0){
            mainUI.setActionsEnabled(actionTokensPair);
        }

       else{
            for (int i=0;i<actionCards.size();i++){
                Random random= new Random();
                int a = random.nextInt(2);
                //System.out.println(a);
                if (a==0) {
                    actionTokensPair.add(actionCards.get(i).getRecto());
                    actionTokensImpair.add(actionCards.get(i).getVerso());
                }
                else{
                    actionTokensImpair.add(actionCards.get(i).getRecto());
                    actionTokensPair.add(actionCards.get(i).getVerso());
                }
            }
            mainUI.setActionsEnabled(actionTokensImpair);



        }
        mainUI.setTurn(whoPlay);
    }

//    public void gameEnded(){
  //      if (turnCount>8)
    //}





    public void initActionCard(){
        actionCards = new ArrayList<>();
        actionCards.add(new ActionCard(ActionToken.ALIBI,ActionToken.HOLMES));
        actionCards.add(new ActionCard(ActionToken.LE_CHIEN,ActionToken.WATSON));
        actionCards.add(new ActionCard(ActionToken.ROTATION,ActionToken.ECHANGE));
        actionCards.add(new ActionCard(ActionToken.ROTATION,ActionToken.JOKER));
    }
    public void initAlibiCard(){
        alibiCards = new ArrayList<>();
        alibiCards.add(new AlibiCard(AlibiName.MADAME,2));
        alibiCards.add(new AlibiCard(AlibiName.SERGENT_GOODLEY,0));
        alibiCards.add(new AlibiCard(AlibiName.JEREMY_BART,1));
        alibiCards.add(new AlibiCard(AlibiName.WILLIAM_GULL,1));
        alibiCards.add(new AlibiCard(AlibiName.MISS_STEALTHY,1));
        alibiCards.add(new AlibiCard(AlibiName.JOHN_SMITH,1));
        alibiCards.add(new AlibiCard(AlibiName.LESTRADE,0));
        alibiCards.add(new AlibiCard(AlibiName.JOHN_PIZER,1));
        alibiCards.add(new AlibiCard(AlibiName.JOSEPH_LANG,1));

    }



   public AlibiName pickIdentityJack(){
        Random rand=new Random();
       AlibiName Jack =AlibiName.values()[(int) rand.nextInt(AlibiName.values().length)];

       return Jack;
   }

    public boolean DetectiveConditionToWin(Board board){
        District[][] districtBoard = board.getDistrictBoard();
       int somme=0;
       for (int i =0;i<3;i++){
           for (int j =0;j<3;j++){
               if (districtBoard[i][j].isRecto()==false){
                   somme=somme+1;
               }
           }

       }
        if(somme==1){
            return true;
        }
        else {
            return false;
        }

    }



    public boolean MrJackConditionToWin(MrJackPlayer mrJackPlayer) {
        int sablierJack = mrJackPlayer.getHourglass();
        if (sablierJack >= 6) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void appelATemoins (MrJackPlayer mrJackPlayer,Board board){
        ArrayList<AlibiName>  visiblecharacters ;
        visiblecharacters= board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard());
        District[][] districtBoard = board.getDistrictBoard();
        if (mrJackPlayer.isVisible()==false){
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (visiblecharacters.contains(districtBoard[i][j].getCharacter()))
                    {
                       districtBoard[i][j].setRecto(false);
                       mrJackPlayer.setHourglass(mrJackPlayer.getHourglass()+1);
                    }
                }
            }

        }
        else{
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (!visiblecharacters.contains(districtBoard[i][j].getCharacter()))
                    {
                        districtBoard[i][j].setRecto(false);
                    }
                }
            }
        }
        mainUI.updateUIDistrict(board.getDistrictBoard());
    }




}
