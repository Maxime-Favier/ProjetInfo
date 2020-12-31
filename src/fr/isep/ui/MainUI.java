package fr.isep.ui;

import fr.isep.board.*;
import fr.isep.game.Actions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MainUI {
    Actions actions;
    Board board;
    String actionMode;
    Orientation rotateOrientation;
    int tmpx;
    int tmpy;
    JFrame f;
    JButton[][] districtBtn;
    JButton[] detectivesBtn;
    JLabel hourGlassLabel;
    JLabel tourRoleLabel;

    public MainUI(Actions actions, Board board) {
        this.actions = actions;
        this.board = board;
        actionMode = "NONE";

        initUI();
        initDistrictBtn();
        initDetectivesBtn();
        initotherLbl();
        initActions();
        endUIInit();
        //updateHourglass(50);
    }

    private void initUI() {
        System.out.println("init ui");
        f = new JFrame("Projet Mr Jack");
    }

    private void initDistrictBtn() {
        districtBtn = new JButton[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                districtBtn[x][y] = new JButton("district is loading");

                districtBtn[x][y].setBounds(210 + x * 200, 210 + y * 200, 200, 200);
                int finalX = x;
                int finalY = y;
                districtBtn[x][y].addActionListener(event -> districtClick(event, finalX, finalY));
                f.add(districtBtn[x][y]);
            }
        }
    }

    private void districtClick(ActionEvent ae, int x, int y) {
        if(actionMode.equals("ROTATE")){
            actions.rotateDistrict(x, y, rotateOrientation);
            actionMode = "NONE";
            updateUIDistrict(board.getDistrictBoard());
        }else if(actionMode.equals("SWAP")){
            tmpx = x;
            tmpy = y;
            actionMode = "SWAP2";
        }else if(actionMode.equals("SWAP2")){
            actions.swapDistrict(tmpx, tmpy, x, y);
            actionMode = "NONE";
            updateUIDistrict(board.getDistrictBoard());
        }
        System.out.println(x + " - " + y);
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

    private void initotherLbl() {
        Image hourGlassImage = null;
        try {
            hourGlassImage = ImageIO.read(getClass().getResource("/hourglass.png"));
            ImageIcon hourGlassIcon = new ImageIcon(hourGlassImage.getScaledInstance(50, 50, 1));
            hourGlassLabel = new JLabel("Mr Jack has 0 hourGlass", hourGlassIcon, SwingConstants.RIGHT);
            hourGlassLabel.setBounds(850, 10, 500, 100);
            f.add(hourGlassLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tourRoleLabel = new JLabel("It's the turn of the investigator");
        tourRoleLabel.setBounds(1100, 200, 500, 100);
        f.add(tourRoleLabel);

    }

    private void initActions() {
        JButton alibiBtn = new JButton("loading");
        alibiBtn.setBounds(1100, 120, 100, 100);
        try {
            Image alibimg = ImageIO.read(getClass().getResource("/ALIBI.png"));
            alibiBtn.setIcon(new ImageIcon(alibimg.getScaledInstance(110, 100, 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.add(alibiBtn);

        JButton rotateBtn = new JButton("loading");
        rotateBtn.setBounds(1200, 120, 100, 100);
        try {
            Image rotateImg = ImageIO.read(getClass().getResource("/ROTATE.png"));
            rotateBtn.setIcon(new ImageIcon(rotateImg.getScaledInstance(110, 100, 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rotateBtn.addActionListener(e -> {
            Object[] options = {Orientation.NORTH.toString(), Orientation.EAST.toString(), Orientation.SOUTH.toString(), Orientation.WEST.toString()};
            int n = JOptionPane.showOptionDialog(
                    f,
                    "Please select the direction and click on the district",
                    "Rotate a district",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title
            System.out.println(n);
            if(n != -1){
                actionMode = "ROTATE";
                switch (n){
                    case 0:
                        rotateOrientation = Orientation.NORTH;
                        break;
                    case 1:
                        rotateOrientation = Orientation.EAST;
                        break;
                    case 2:
                        rotateOrientation = Orientation.SOUTH;
                        break;
                    case 3:
                        rotateOrientation = Orientation.WEST;
                        break;
                }
            }
        });
        f.add(rotateBtn);

        JButton swapBtn = new JButton("loading");
        swapBtn.setBounds(1300, 120, 100, 100);
        try {
            Image swapImg = ImageIO.read(getClass().getResource("/SWAP_DISTRICT.png"));
            swapBtn.setIcon(new ImageIcon(swapImg.getScaledInstance(110, 100, 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        swapBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(
                        f,
                        "Click on the two district to swap",
                        "swap districts",
                        JOptionPane.YES_NO_OPTION);
                System.out.println(n);
                if(n == 0){
                    actionMode = "SWAP";
                }
            }
        });
        f.add(swapBtn);

        JButton jockerBtn = new JButton("loading");
        jockerBtn.setBounds(1400, 120, 100, 100);
        try {
            Image jockerImg = ImageIO.read(getClass().getResource("/JOKER.png"));
            jockerBtn.setIcon(new ImageIcon(jockerImg.getScaledInstance(110, 100, 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jockerBtn.addActionListener(e -> {

        });
        f.add(jockerBtn);

        JButton sherlockBtn = new JButton("loading");
        sherlockBtn.setBounds(1500, 120, 100, 100);
        try {
            Image sherlockImg = ImageIO.read(getClass().getResource("/SHERLOCK.png"));
            sherlockBtn.setIcon(new ImageIcon(sherlockImg.getScaledInstance(110, 100, 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sherlockBtn.addActionListener(e -> {
            Object[] options = {"1", "2"};
            int n = JOptionPane.showOptionDialog(f,
                    "How many spaces do you want to move sherlock",
                    "What do you want ?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (n == 1) {
                actions.moveDetective(DetectiveName.SHERLOCK, 2);
                updateUIDetective(board.getDetectiveBoard());
            } else if (n == 0) {
                actions.moveDetective(DetectiveName.SHERLOCK, 1);
                updateUIDetective(board.getDetectiveBoard());
            }

        });
        f.add(sherlockBtn);

        JButton watsonBtn = new JButton("loading");
        watsonBtn.setBounds(1600, 120, 100, 100);
        try {
            Image watsonImg = ImageIO.read(getClass().getResource("/WATSON.png"));
            watsonBtn.setIcon(new ImageIcon(watsonImg.getScaledInstance(110, 100, 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        watsonBtn.addActionListener(e -> {
            Object[] options = {"1", "2"};
            int n = JOptionPane.showOptionDialog(f,
                    "How many spaces do you want to move watson",
                    "What do you want ?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            //System.out.println(n);
            if (n == 1) {
                actions.moveDetective(DetectiveName.WATSON, 2);
                updateUIDetective(board.getDetectiveBoard());
            } else if (n == 0) {
                actions.moveDetective(DetectiveName.WATSON, 1);
                updateUIDetective(board.getDetectiveBoard());
            }
        });
        f.add(watsonBtn);

        JButton tobbyBtn = new JButton("loading");
        tobbyBtn.setBounds(1700, 120, 100, 100);
        try {
            Image tobbyImg = ImageIO.read(getClass().getResource("/TOBBY.png"));
            tobbyBtn.setIcon(new ImageIcon(tobbyImg.getScaledInstance(110, 100, 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tobbyBtn.addActionListener(e -> {
            Object[] options = {"1", "2"};
            int n = JOptionPane.showOptionDialog(f,
                    "How many spaces do you want to move tobby",
                    "What do you want ?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            //System.out.println(n);
            if (n == 1) {
                actions.moveDetective(DetectiveName.TOBBY, 2);
                updateUIDetective(board.getDetectiveBoard());
            } else if (n == 0) {
                actions.moveDetective(DetectiveName.TOBBY, 1);
                updateUIDetective(board.getDetectiveBoard());
            }
        });
        f.add(tobbyBtn);
    }

    public void updateHourglass(int hourglasses) {
        hourGlassLabel.setText("Mr Jack has " + String.valueOf(hourglasses) + " hourGlass(es)");
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

            if (!detectivePlace.isEmpty()) {
                //System.out.println(i);
                boolean sherlok, watson, tobby;
                sherlok = watson = tobby = false;

                for (DetectiveToken detectiveToken : detectivePlace) {
                    if (detectiveToken.getDetectiveName() == DetectiveName.SHERLOCK) {
                        sherlok = true;
                    } else if (detectiveToken.getDetectiveName() == DetectiveName.WATSON) {
                        watson = true;
                    } else if (detectiveToken.getDetectiveName() == DetectiveName.TOBBY) {
                        tobby = true;
                    }
                }
                StringBuilder fileName = new StringBuilder();
                fileName.append("/");
                if (sherlok) {
                    fileName.append("-SHERLOCK");
                }
                if (watson) {
                    fileName.append("-WATSON");
                }
                if (tobby) {
                    fileName.append("-TOBBY");
                }
                fileName.append(".png");
                //System.out.println(fileName.toString());
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource(fileName.toString()));
                    detectivesBtn[i].setIcon(new ImageIcon(image.getScaledInstance(205, 200, 1)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource("/BLANK.png"));
                    detectivesBtn[i].setIcon(new ImageIcon(image.getScaledInstance(255, 255, 1)));
                } catch (IOException e) {
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
        f.setSize(1800, 1100);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
