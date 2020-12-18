

package fr.isep.JackPocket;
import java.util.*;
public class Cell {
   DetectiveToken  detectiveTokens;
   District district;

    public Cell(DetectiveToken detectiveTokens, District district) {
        this.detectiveTokens = detectiveTokens;
        this.district = district;
    }

    public DetectiveToken getDetectiveTokens() {
        return detectiveTokens;
    }

    public void setDetectiveTokens(DetectiveToken detectiveTokens) {
        this.detectiveTokens = detectiveTokens;
    }

    public District getDistrict() {
        return district;
    }



    public void setDistrict(District district) {
        this.district = district;
    }
}
