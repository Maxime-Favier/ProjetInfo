package fr.isep.JackPocket;
import java.util.ArrayList;


public class DetectiveToken{
    private DetectiveName nomDetective1;
    private DetectiveName nomDetective2;
    private DetectiveName nomDetective3;
    private Orientation orientationDetective;
    public DetectiveToken(DetectiveName nomDetective1, DetectiveName nomDetective2, DetectiveName nomDetective3, Orientation orientationDetective) {
        this.nomDetective1 = nomDetective1;
        this.nomDetective2 = nomDetective2;
        this.nomDetective3 = nomDetective3;
        this.orientationDetective = orientationDetective;
    }


    public DetectiveName getNomDetective1() {
        return nomDetective1;
    }

    public void setNomDetective1(DetectiveName nomDetective1) {
        this.nomDetective1 = nomDetective1;
    }

    public DetectiveName getNomDetective2() {
        return nomDetective2;
    }

    public void setNomDetective2(DetectiveName nomDetective2) {
        this.nomDetective2 = nomDetective2;
    }

    public DetectiveName getNomDetective3() {
        return nomDetective3;
    }

    public void setNomDetective3(DetectiveName nomDetective3) {
        this.nomDetective3 = nomDetective3;
    }

    public Orientation getOrientationDetective() {
        return orientationDetective;
    }

    public void setOrientationDetective(Orientation orientationDetective) {
        this.orientationDetective = orientationDetective;
    }
}