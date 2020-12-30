package fr.isep.board;

public class DetectiveToken {
    private final DetectiveName detectiveName;

    public DetectiveToken(DetectiveName detectiveName) {
        this.detectiveName = detectiveName;
    }

    public DetectiveName getDetectiveName() {
        return detectiveName;
    }
}
