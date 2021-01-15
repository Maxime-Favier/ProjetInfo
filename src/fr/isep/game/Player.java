package fr.isep.game;

public class Player {
    protected String name;
    protected boolean whoPlay;

    public Player(String name, boolean whoPlay) {
        this.name = name;
        this.whoPlay = whoPlay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWhoPlay() {
        return whoPlay;
    }

    public void setWhoPlay(boolean whoPlay) {
        this.whoPlay = whoPlay;
    }
}
