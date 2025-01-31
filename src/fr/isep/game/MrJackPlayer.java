package fr.isep.game;

import fr.isep.board.AlibiName;

import java.util.Random;

public class MrJackPlayer  { // classe mrjackplayer qui a comme attribut un alibi name et un nombre de sabliers
    private AlibiName JackAlibiName;
    private int hourglass;



    public MrJackPlayer(AlibiName jackAlibiName, int hourglass) {
        JackAlibiName = jackAlibiName;
        this.hourglass = hourglass;
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


}