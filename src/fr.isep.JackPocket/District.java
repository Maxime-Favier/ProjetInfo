package fr.isep.JackPocket;

public class District {
    private AlibiCard alibiCards;
    private Orientation orientation;
    private boolean isRecto;
    private boolean isVisible;

    public District(AlibiCard alibiCards, Orientation orientation, boolean isRecto, boolean isVisible) {
        this.alibiCards = alibiCards;
        this.orientation = orientation;
        this.isRecto = isRecto;
        this.isVisible=isVisible;
    }


    public AlibiCard getAlibiCards() {
        return alibiCards;
    }

    public void setAlibiCards(AlibiCard alibiCards) {
        this.alibiCards = alibiCards;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public boolean isRecto() {
        return isRecto;
    }

    public void setRecto(boolean recto) {
        isRecto = recto;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }


public void rotate (Orientation orientation,District district){

district.setOrientation(orientation);


}
}