package fr.isep.JackPocket;

public class Board {
    Cell [][] cells;
    public Board(Cell[][] cells){
        this.cells= cells;
    }
    public Cell[][] getCells(){
        return cells;
    }

    public void setCells(Cell[][] cells){
        this.cells = cells;
    }


}
