package fr.isep.game;

import fr.isep.board.AlibiName;

public class AlibiCard { // classe alibicard qui a comme attribut un alibi name et un nombre de sabliers
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
