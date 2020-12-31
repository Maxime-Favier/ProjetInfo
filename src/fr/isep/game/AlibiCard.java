package fr.isep.game;

import fr.isep.board.AlibiName;

public class AlibiCard {
    private AlibiName name;
    private int hourGlassCount;

    public AlibiCard(AlibiName name, int hourGlassCount) {
        this.name = name;
        this.hourGlassCount = hourGlassCount;
    }

    public AlibiName getName() {
        return name;
    }

    public int getHourGlassCount() {
        return hourGlassCount;
    }
}
