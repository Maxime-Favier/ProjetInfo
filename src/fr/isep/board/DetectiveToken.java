package fr.isep.board;

public class DetectiveToken {
    // classe detectiveToken qui prend en argument le nom du détective
    private final DetectiveName detectiveName;

    public DetectiveToken(DetectiveName detectiveName) {
        this.detectiveName = detectiveName;
    }

    public DetectiveName getDetectiveName() {
        return detectiveName;
    }
}
