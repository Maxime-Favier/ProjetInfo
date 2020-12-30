package fr.isep.board;

public class District {
    Orientation orientation;
    AlibiName character;
    boolean isRecto;

    public District(Orientation orientation, AlibiName character) {
        this.orientation = orientation;
        this.character = character;
        isRecto = true;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public AlibiName getCharacter() {
        return character;
    }

    public void setCharacter(AlibiName character) {
        this.character = character;
    }

    public boolean isRecto() {
        return isRecto;
    }

    public void setRecto(boolean recto) {
        isRecto = recto;
    }
}
