package fr.isep.JackPocket;

public class AlibiCard {
    private AlibiName name;
    private int hourglass;

    public AlibiCard(AlibiName name, int hourglass) {
        this.name = name;
        this.hourglass = hourglass;
    }

    public AlibiName getName() {
        return name;
    }

    public void setName(AlibiName name) {
        this.name = name;
    }

    public int getHourglass() {
        return hourglass;
    }

    public void setHourglass(int hourglass) {
        this.hourglass = hourglass;
    }
}
