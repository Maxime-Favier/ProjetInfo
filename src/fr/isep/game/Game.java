package fr.isep.game;

import fr.isep.board.AlibiName;
import fr.isep.board.Board;
import fr.isep.ui.MainUI;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public ArrayList<AlibiCard> alibiCards;
    private ArrayList<ActionCard> actionCards;
    public ArrayList<AlibiName> suspectsRestants ;
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
        MrJackPlayer mrJackPlayer =new MrJackPlayer (null,0);
        mrJackPlayer.setJackAlibiName(pickIdentityJack());
        initActionCard();
        initAlibiCard();
        initSuspects();
        Actions actions = new Actions(board);
        mainUI = new MainUI(actions, board);
        System.out.println(board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard()));

        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.updateUIDetective(board.getDetectiveBoard());

        mainUI.showMrJackName(AlibiName.JOHN_PIZER);

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

    public void initSuspects(){
        suspectsRestants  = new ArrayList<>();
        suspectsRestants.add(AlibiName.MADAME);
        suspectsRestants.add(AlibiName.SERGENT_GOODLEY);
        suspectsRestants.add(AlibiName.JEREMY_BART);
        suspectsRestants.add(AlibiName.WILLIAM_GULL);
        suspectsRestants.add(AlibiName.MISS_STEALTHY);
        suspectsRestants.add(AlibiName.JOHN_SMITH);
        suspectsRestants.add(AlibiName.LESTRADE);
        suspectsRestants.add(AlibiName.JOHN_PIZER);
        suspectsRestants.add(AlibiName.JOSEPH_LANG);
    }


    public void suspectsRestants(Board board){
      ArrayList<AlibiName>  visiblecharacters ;

       visiblecharacters= board.getVisibleCharacters(board.getDistrictBoard(), board.getDetectiveBoard());
      for (int i=0;i<visiblecharacters.size();i++){
          if (suspectsRestants.contains(visiblecharacters.get(i))){
              suspectsRestants.remove((visiblecharacters.get(i)));
          }
       }


   }

   public AlibiName pickIdentityJack(){
        Random rand=new Random();
       AlibiName Jack =AlibiName.values()[(int) rand.nextInt(AlibiName.values().length+1)];

       return Jack;
   }



}
