package fr.isep.ui;

import fr.isep.board.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MainUI {
    JFrame f;
    JButton[][] districtBtn;
    JButton[] detectivesBtn;

    public MainUI() {
        initUI();
        initDistrictBtn();
        initDetectivesBtn();
        endUIInit();
    }

    private void initUI() {
        System.out.println("init ui");
        f = new JFrame("Projet Mr Jack");
        //System.out.println(AlibiName.JEREMY_BART.toString());

    }

    private void initDistrictBtn() {
        districtBtn = new JButton[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                districtBtn[x][y] = new JButton("district is loading");

                districtBtn[x][y].setBounds(210 + x * 200, 210 + y * 200, 200, 200);
                f.add(districtBtn[x][y]);
            }
        }
    }

    private void initDetectivesBtn() {
        detectivesBtn = new JButton[12];

        for (int i = 0; i < 12; i++) {
            detectivesBtn[i] = new JButton("Loading");

            if (i <= 2) {
                detectivesBtn[i].setBounds(210 + i * 200, 10, 200, 200);
            } else if (i <= 5) {
                detectivesBtn[i].setBounds(810, 210 + (i - 3) * 200, 200, 200);
            } else if (i <= 8) {
                detectivesBtn[i].setBounds(610 - ((i - 6) * 200), 810, 200, 200);
            } else {
                detectivesBtn[i].setBounds(10, 610 - ((i - 9) * 200), 200, 200);
            }

            f.add(detectivesBtn[i]);
        }

    }

    public void updateUIDistrict(District[][] districtBoard) {
        System.out.println("refresh district UI...");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Orientation orientation = districtBoard[i][j].getOrientation();
                AlibiName detectiveName = districtBoard[i][j].getCharacter();
                boolean isRecto = districtBoard[i][j].isRecto();

                String fileName;
                if (isRecto) {
                    fileName = "/" + detectiveName.toString() + "-district.png";
                } else {
                    fileName = "/empty-district.png";
                }

                try {
                    BufferedImage image = ImageIO.read(getClass().getResource(fileName));

                    if (orientation != Orientation.NORTH) {
                        //BufferedImage image2 = image;
                        switch (orientation) {
                            case EAST:
                                //System.out.println("east");
                                image = rotate(image, 90);
                                break;
                            case SOUTH:
                                image = rotate(image, 180);
                                break;
                            case WEST:
                                image = rotate(image, 270);
                                break;
                        }
                    }
                    districtBtn[i][j].setIcon(new ImageIcon(image.getScaledInstance(255, 255, 1)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateUIDetective(ArrayList<DetectiveToken>[] detectiveBoard) {
        System.out.println("refresh detectives UI");
        for (int i = 0; i < 12; i++) {
            ArrayList<DetectiveToken> detectivePlace = detectiveBoard[i];

            if(!detectivePlace.isEmpty()){
                //System.out.println(i);
                boolean sherlok, watson, tobby;
                sherlok = watson = tobby = false;

                for (DetectiveToken detectiveToken: detectivePlace) {
                    if(detectiveToken.getDetectiveName() == DetectiveName.SHERLOCK){
                        sherlok = true;
                    }else if(detectiveToken.getDetectiveName() == DetectiveName.WATSON){
                        watson = true;
                    }else if(detectiveToken.getDetectiveName() == DetectiveName.TOBBY){
                        tobby = true;
                    }
                }
                StringBuilder fileName = new StringBuilder();
                fileName.append("/");
                if(sherlok){
                    fileName.append("-SHERLOCK");
                }if(watson){
                    fileName.append("-WATSON");
                }if(tobby){
                    fileName.append("-TOBBY");
                }
                fileName.append(".png");
                //System.out.println(fileName.toString());
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource(fileName.toString()));
                    detectivesBtn[i].setIcon(new ImageIcon(image.getScaledInstance(205, 200, 1)));
                }catch (IOException e){
                    e.printStackTrace();
                }

            }else{
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource("/BLANK.png"));
                    detectivesBtn[i].setIcon(new ImageIcon(image.getScaledInstance(255, 255, 1)));
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }

    public static BufferedImage rotate(BufferedImage bimg, double angle) {

        int w = bimg.getWidth();
        int h = bimg.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w / 2, h / 2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

    private void endUIInit() {
        f.setSize(1000, 1100);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
