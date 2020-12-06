package fr.isep.JackPocket;
import java.util.ArrayList;

public class DetectiveToken {
    private ArrayList<DetectiveName> DetectiveList = new ArrayList<DetectiveName>();

    public ArrayList<DetectiveName> getDetectiveList(){
        return DetectiveList;

    }
    public void setDetectiveList(ArrayList<DetectiveName> DetectiveList){
        this.DetectiveList = DetectiveList;

    }

    public void addDetective (DetectiveName detectiveName){
        if(!(DetectiveList.contains(detectiveName))){
            DetectiveList.add(detectiveName);
        }
    }

    public void abstractDetective (DetectiveName detectiveName){
        if(DetectiveList.contains(detectiveName)){
            DetectiveList.remove(detectiveName);

        }
    }


}