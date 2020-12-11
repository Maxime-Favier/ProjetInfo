package fr.isep.JackPocket;

public class MrJackPlayer {
    AlibiCard jackCharacter;
    int hourglassMrJack;
    boolean mrJackvisible;
    public MrJackPlayer(AlibiCard jackCharacter, int hourglassMrJack, boolean mrJackvisible) {
        this.jackCharacter = jackCharacter;
        this.hourglassMrJack = hourglassMrJack;
        this.mrJackvisible=mrJackvisible;
    }

    public AlibiCard getJackCharacter() {
        return jackCharacter;
    }

    public void setJackCharacter(AlibiCard jackCharacter) {
        this.jackCharacter = jackCharacter;
    }

    public int getHourglassMrJack() {
        return hourglassMrJack;
    }

    public void setHourglassMrJack(int hourglassMrJack) {
        this.hourglassMrJack = hourglassMrJack;
    }

    public boolean isMrJackvisible() {
        return mrJackvisible;
    }

    public void setMrJackvisible(boolean mrJackvisible) {
        this.mrJackvisible = mrJackvisible;
    }

}
