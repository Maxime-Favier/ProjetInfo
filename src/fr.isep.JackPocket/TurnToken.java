package fr.isep.JackPocket;

public class TurnToken {
    private int turn;
    private int hourglass;

    public TurnToken(int turn, int hourglass){
        this.turn=turn;
        this.hourglass= hourglass;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getHourglass (){
        return hourglass;
    }

    public void setHourglass(int hourglass){
        this.hourglass= hourglass;
    }
}
