package fr.isep.game;

public class ActionCard { // classe Action carte qui a une face recto et verso. Les faces recto et verso sont des actions tokens

    public ActionToken recto;
    public ActionToken verso;

    public ActionCard(ActionToken recto, ActionToken verso) {
        this.recto = recto;
        this.verso = verso;
    }

    public ActionToken getRecto() { // permet de recuperer l'action token de la face recto d'une action card
        return recto;
    }

    public ActionToken getVerso() {
        return verso;
    } // permet de recuperer l'action token de la face verso d'une action card
}
