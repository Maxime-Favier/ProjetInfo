package fr.isep.game;

import fr.isep.board.AlibiName;

public class MrJackPlayer extends Player {
    private AlibiName JackAlibiName;
    private int hourglass;


    public MrJackPlayer(String name, boolean whoPlay) {
        super(name, whoPlay);
    }

    public MrJackPlayer(String name, boolean whoPlay, AlibiName jackAlibiName, int hourglass) {
        super(name, whoPlay);
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