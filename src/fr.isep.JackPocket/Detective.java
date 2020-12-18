package fr.isep.JackPocket;

public class Detective {
    private DetectiveName name;
    private int positionX;
    private int positionY;
    private Orientation orientationDetective;

    public Detective(DetectiveName name, int positionX, int positionY, Orientation orientationDetective) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientationDetective = orientationDetective;
    }

    public DetectiveName getName() {
        return name;
    }

    public void setName(DetectiveName name) {
        this.name = name;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Orientation getOrientationDetective() {
        return orientationDetective;
    }

    public void setOrientationDetective(Orientation orientationDetective) {
        this.orientationDetective = orientationDetective;
    }
}
