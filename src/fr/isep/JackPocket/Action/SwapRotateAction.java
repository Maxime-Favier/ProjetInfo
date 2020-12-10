package fr.isep.JackPocket.Action;

import fr.isep.JackPocket.District;
import fr.isep.JackPocket.Orientation;

public class SwapRotateAction extends ActionToken{
    /*
    + rotate(District district, orientation Orientation
    ) : void
    + swap(District d1, District d2) : void
     */

    public void rotate(District district, Orientation orientation){
        district.setOrientation(orientation);
    }
    public void swap(District d1, District d2){

    }
}
