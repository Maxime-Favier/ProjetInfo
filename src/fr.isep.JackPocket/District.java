package fr.isep.JackPocket;

public class District {
    private AlibiName alibiNames;
    private Orientation orientation;
    private boolean isRecto;
    private boolean isVisible;

    public District(AlibiName alibiNames, Orientation orientation, boolean isRecto, boolean isVisible) {
        this.alibiNames = alibiNames;
        this.orientation = orientation;
        this.isRecto = isRecto;
        this.isVisible=isVisible;
    }

    public AlibiName getAlibiNames() {
        return alibiNames;
    }

    public void setAlibiNames(AlibiName alibiNames) {
        this.alibiNames = alibiNames;
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
}
