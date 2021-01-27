package fr.isep.board;

public class District {
    // classe qui prend en argument : l'orientation de chaque district et le nom de chaque personnage
    private Orientation orientation;
    private AlibiName character;
    private boolean isRecto;
    private boolean cross;

    public District(Orientation orientation, AlibiName character) {
        this.orientation = orientation;
        this.character = character;
        //System.out.println(character==AlibiName.JOSEPH_LANG);
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
