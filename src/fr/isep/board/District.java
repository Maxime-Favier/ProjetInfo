package fr.isep.board;

public class District {
    Orientation orientation;
    AlibiName character;
    boolean isRecto;
    boolean cross;

    public District(Orientation orientation, AlibiName character) {
        this.orientation = orientation;
        this.character = character;
        System.out.println(character==AlibiName.JOSEPH_LANG);
        isRecto = true;
        cross = false;
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
        if(character == AlibiName.JOSEPH_LANG){
            cross = true;
        }
    }

    public boolean isCross() {
        return cross;
    }

    public void setCross(boolean cross) {
        this.cross = cross;
    }
}
