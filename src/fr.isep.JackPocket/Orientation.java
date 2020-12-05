package fr.isep.JackPocket;

public enum Orientation {

    EAST,
    NORTH,
    WEST,
    SOUTH;

    public int toInt(Orientation orientation){
        int orientationDecimal=0;
        switch (orientation){
            case EAST: orientationDecimal= 1;
            break;
            case WEST:orientationDecimal= 2;
            break;
            case NORTH: orientationDecimal= 3;
            break;
            case SOUTH:orientationDecimal= 4;
            break;
        }
        return orientationDecimal;

    }
}
