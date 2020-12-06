package fr.isep.JackPocket;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    District [][] board;
    public Board(){
        initBoard();
    }

    public District[][] getBoard() {
        return board;
    }

    public void setBoard(District[][] board) {
        this.board = board;
    }

    public void initBoard() {
        ArrayList<District> districts = new ArrayList<>();

        // ArrayDeque<Alibi> alibis = new ArrayDeque<>();
        // district.add(New District(New Alibi()));
        districts.add(new District(new AlibiCard(AlibiName.Inspecteur_Lestrade,0),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false,false));
        districts.add(new District(new AlibiCard(AlibiName.Miss_Stealthy, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)],false,false));
        districts.add(new District(new AlibiCard(AlibiName.Jeremy_Bert, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)],false, false));
        districts.add(new District(new AlibiCard(AlibiName.John_Pizer, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false,false));
        districts.add(new District(new AlibiCard(AlibiName.John_Smith, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false,false));
        districts.add(new District(new AlibiCard(AlibiName.Joseph_Lane, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false,false));
        districts.add(new District(new AlibiCard(AlibiName.Madame, 2),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false,false));
        districts.add(new District(new AlibiCard(AlibiName.Sgt_Goodley, 0),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false,false));
        districts.add(new District(new AlibiCard(AlibiName.William_Gull, 1),
                Orientation.values()[(int) (Math.random() * Orientation.values().length)], false,false));

        District[][] board = new District[3][3];
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                Random rand = new Random();
                int randomIndex = rand.nextInt(districts.size());
                board[i][j] = districts.get(randomIndex);
                districts.remove(randomIndex);
            }

        }

        board[0][0].setOrientation(Orientation.WEST);
        board[0][2].setOrientation(Orientation.EAST);
        board[2][1].setOrientation(Orientation.SOUTH);
    }
        }




