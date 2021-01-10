package fr.isep.game;

public class ActionCard {

    public ActionToken recto;
    public ActionToken verso;

    public ActionCard(ActionToken recto, ActionToken verso) {
        this.recto = recto;
        this.verso = verso;
    }

    public ActionToken getRecto() {
        return recto;
    }

    public ActionToken getVerso() {
        return verso;
    }
}
