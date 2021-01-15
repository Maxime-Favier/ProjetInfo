package fr.isep.game;

import fr.isep.board.AlibiName;

import java.util.Random;

public class MrJackPlayer  {
    private AlibiName JackAlibiName;
    private int hourglass;
    private boolean isVisible;


    public MrJackPlayer(AlibiName jackAlibiName, int hourglass, boolean isVisible) {
        JackAlibiName = jackAlibiName;
        this.hourglass = hourglass;
        this.isVisible = isVisible;
    }

    public AlibiName getJackAlibiName() {
        return JackAlibiName;
    }

    public void setJackAlibiName(AlibiName jackAlibiName) {
        JackAlibiName = jackAlibiName;
    }

    public int getHourglass() {
        return hourglass;
    }

    public void setHourglass(int hourglass) {
        this.hourglass = hourglass;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}