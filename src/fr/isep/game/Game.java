package fr.isep.game;

import fr.isep.board.Board;
import fr.isep.board.DetectiveName;
import fr.isep.ui.MainUI;

public class Game {
    public Game() {
        play();
    }

    public void play() {
        System.out.println("Game Playing");

        Board board = new Board();
        MainUI mainUI = new MainUI();
        Actions actions = new Actions(board);
        actions.moveDetective(DetectiveName.SHERLOCK, 0);
        mainUI.updateUIDistrict(board.getDistrictBoard());
        mainUI.updateUIDetective(board.getDetectiveBoard());
    }
}
