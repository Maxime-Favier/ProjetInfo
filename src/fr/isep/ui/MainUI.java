package fr.isep.ui;

import fr.isep.board.*;
import fr.isep.game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MainUI {
    // facteurs de dimensionnement de l'UI
    private static final float X_SCALE = 0.8f;
    private static final float Y_SCALE = 0.8f;
    // objets du game
    private Actions actions;
    private Board board;
    private Game game;
    private MrJackPlayer mrJackPlayer;

    private String actionMode; // type d'action
    private Orientation rotateOrientation;
    private int tmpx, tmpy; // memoire swap
    // widjet UI
    private JFrame f;
    private JButton[][] districtBtn;
    private JButton[] detectivesBtn;
    private JLabel hourGlassLabel;
    private JLabel tourRoleLabel;
    private JButton alibiBtn, rotateBtn, rotateBtn2, swapBtn, jockerBtn, watsonBtn, sherlockBtn, tobbyBtn;
    // liste alibi
    private DefaultListModel<String> alibiListModel = new DefaultListModel<>();
    private int turn = 0; // tour nb
    private int lastrotateX, lastrotateY; // dernier rotate coordonée

    public MainUI(Actions actions, Board board, Game game, MrJackPlayer mrJackPlayer) {
        this.actions = actions;
        this.board = board;
        this.game = game;
        this.mrJackPlayer = mrJackPlayer;

        actionMode = "NONE";
        lastrotateX = -1;
        lastrotateY = -1;

        initUI();
        initDistrictBtn();
        initDetectivesBtn();
        initotherLbl();
        initActions();
        endUIInit();
    }

    private void initUI() {
        // creation de la fenetre
        System.out.println("init ui");
        f = new JFrame("Projet Mr Jack");
    }

    private void initDistrictBtn() {
        // création des boutton du district
        districtBtn = new JButton[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                districtBtn[x][y] = new JButton("district is loading");

                districtBtn[x][y].setBounds((int) ((210 + x * 200) * X_SCALE), (int) ((210 + y * 200) * Y_SCALE), (int) (200 * X_SCALE), (int) (200 * Y_SCALE));
                int finalX = x;
                int finalY = y;
                districtBtn[x][y].addActionListener(event -> districtClick(event, finalX, finalY));
                f.add(districtBtn[x][y]);
            }
        }
    }

    private void initDetectivesBtn() {
        // definition et ajout des bouttons pour les detectives
        detectivesBtn = new JButton[12];

        for (int i = 0; i < 12; i++) {
            detectivesBtn[i] = new JButton("Loading");

            if (i <= 2) {
                detectivesBtn[i].setBounds((int) ((210 * X_SCALE + i * 200 * X_SCALE)), (int) (10 * Y_SCALE), (int) (200 * X_SCALE), (int) (200 * Y_SCALE));
            } else if (i <= 5) {
                detectivesBtn[i].setBounds((int) (810 * X_SCALE), (int) ((210 * Y_SCALE + (i - 3) * 200 * Y_SCALE)), (int) (200 * X_SCALE), (int) (200 * Y_SCALE));
            } else if (i <= 8) {
                detectivesBtn[i].setBounds((int) (610 * X_SCALE - ((i - 6) * 200 * X_SCALE)), (int) (810 * Y_SCALE), (int) (200 * X_SCALE), (int) (200 * Y_SCALE));
            } else {
                detectivesBtn[i].setBounds((int) (10 * X_SCALE), (int) (610 * Y_SCALE - ((i - 9) * 200 * Y_SCALE)), (int) (200 * X_SCALE), (int) (200 * Y_SCALE));
            }

            f.add(detectivesBtn[i]);
        }

    }

    private void initotherLbl() {
        // divers widjets UI
        Image hourGlassImage = null;
        try {
            // affichage nombre de sablier
            hourGlassImage = ImageIO.read(getClass().getResource("/hourglass.png"));
            ImageIcon hourGlassIcon = new ImageIcon(hourGlassImage.getScaledInstance((int) (50 * X_SCALE), (int) (50 * Y_SCALE), 1));
            hourGlassLabel = new JLabel("Mr Jack a 0 sablier", hourGlassIcon, SwingConstants.RIGHT);
            hourGlassLabel.setBounds((int) (850 * X_SCALE), (int) (10 * Y_SCALE), (int) (500 * X_SCALE), (int) (100 * Y_SCALE));
            f.add(hourGlassLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // affiche le tour
        tourRoleLabel = new JLabel("C'est au tour du détective de jouer");
        tourRoleLabel.setBounds((int) (1100 * X_SCALE), (int) (300 * Y_SCALE), (int) (500 * X_SCALE), (int) (100 * Y_SCALE));
        f.add(tourRoleLabel);

        // affiche liste de carte alibi
        JList alibiJList = new JList(alibiListModel);
        alibiJList.setBounds((int) (1100 * X_SCALE), (int) (400 * Y_SCALE), (int) (400 * X_SCALE), (int) (400 * Y_SCALE));
        f.add(alibiJList);
    }

    private void initActions() {
        // definition des cartes actions

        // action alibi carte
        alibiBtn = new JButton("loading");
        // definition position
        alibiBtn.setBounds((int) (1100 * X_SCALE), (int) (120 * Y_SCALE), (int) (100 * X_SCALE), (int) (100 * Y_SCALE));
        try {
            // ajout de l'image sur le boutton
            Image alibimg = ImageIO.read(getClass().getResource("/ALIBI.png"));
            alibiBtn.setIcon(new ImageIcon(alibimg.getScaledInstance((int) (110 * X_SCALE), (int) (100 * Y_SCALE), 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ajout evenement clic
        alibiBtn.addActionListener(e -> {
            // appel controleur
            actions.alibi(turn, game, mrJackPlayer, board, this);
            actions.setLastActionPlayed(ActionToken.ALIBI);
            // suppression de l'action
            alibiBtn.setVisible(false);
            updateActionsTodoNumber();
        });
        f.add(alibiBtn);

        // action rotation 1
        rotateBtn = new JButton("loading");
        rotateBtn.setBounds((int) (1200 * X_SCALE), (int) (120 * Y_SCALE), (int) (100 * X_SCALE), (int) (100 * Y_SCALE));
        try {
            Image rotateImg = ImageIO.read(getClass().getResource("/ROTATE.png"));
            rotateBtn.setIcon(new ImageIcon(rotateImg.getScaledInstance((int) (110 * X_SCALE), (int) (100 * Y_SCALE), 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rotateBtn.addActionListener(e -> {
            // boite de dialogue pour la rotation
            Object[] options = {Orientation.NORTH.toString(), Orientation.EAST.toString(), Orientation.SOUTH.toString(), Orientation.WEST.toString()};
            int n = JOptionPane.showOptionDialog(
                    f,
                    "Selectionner une direction et cliquer sur un district",
                    "Pivoter un district",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            //System.out.println(n);
            if (n != -1) {
                // enregistement des parametres pour le clic sur district
                actionMode = "ROTATE";
                switch (n) {
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

        // seconde action de rotation
        rotateBtn2 = new JButton("loading");
        rotateBtn2.setBounds((int) (1400 * X_SCALE), (int) (220 * Y_SCALE), (int) (100 * X_SCALE), (int) (100 * Y_SCALE));
        try {
            Image rotateImg = ImageIO.read(getClass().getResource("/ROTATE.png"));
            rotateBtn2.setIcon(new ImageIcon(rotateImg.getScaledInstance((int) (110 * X_SCALE), (int) (100 * Y_SCALE), 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rotateBtn2.addActionListener(e -> {

            Object[] options = {Orientation.NORTH.toString(), Orientation.EAST.toString(), Orientation.SOUTH.toString(), Orientation.WEST.toString()};

            int n = JOptionPane.showOptionDialog(
                    f,
                    "Selectionner une direction et cliquer sur un district",
                    "Pivoter un district",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (n != -1) {
                actionMode = "ROTATE2";
                switch (n) {
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
        f.add(rotateBtn2);

        // action echange de district
        swapBtn = new JButton("loading");
        swapBtn.setBounds((int) (1300 * X_SCALE), (int) (120 * Y_SCALE), (int) (100 * X_SCALE), (int) (100 * Y_SCALE));
        try {
            Image swapImg = ImageIO.read(getClass().getResource("/SWAP_DISTRICT.png"));
            swapBtn.setIcon(new ImageIcon(swapImg.getScaledInstance((int) (110 * X_SCALE), (int) (100 * Y_SCALE), 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        swapBtn.addActionListener(e -> {
            int n = JOptionPane.showConfirmDialog(
                    f,
                    "Cliquer sur deux districts pour les échanger",
                    "Echanger des districts",
                    JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                // mode echange en vue d'un clic sur un district
                actionMode = "SWAP";
            }
        });
        f.add(swapBtn);

        // action jocker
        jockerBtn = new JButton("loading");
        jockerBtn.setBounds((int) (1400 * X_SCALE), (int) (120 * Y_SCALE), (int) (100 * X_SCALE), (int) (100 * Y_SCALE));
        try {
            Image jockerImg = ImageIO.read(getClass().getResource("/JOKER.png"));
            jockerBtn.setIcon(new ImageIcon(jockerImg.getScaledInstance((int) (110 * X_SCALE), (int) (100 * Y_SCALE), 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jockerBtn.addActionListener(e -> {
            if (turn == 0) {
                // tour detective
                Object[] options = {DetectiveName.SHERLOCK.toString(), DetectiveName.WATSON.toString(), DetectiveName.TOBBY.toString()};
                // selection de l'enqueteur
                int n = JOptionPane.showOptionDialog(f,
                        "Quel détective voulez-vous déplacer",
                        "Que voulez-vous faire?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                switch (n) {
                    case 0:
                        // bouge sherlock
                        actions.moveDetective(DetectiveName.SHERLOCK, 1);
                        actions.setLastActionPlayed(ActionToken.JOKER);
                        // suppression de l'action
                        jockerBtn.setVisible(false);
                        updateActionsTodoNumber();
                        break;
                    case 1:
                        actions.moveDetective(DetectiveName.WATSON, 1);
                        actions.setLastActionPlayed(ActionToken.JOKER);
                        jockerBtn.setVisible(false);
                        updateActionsTodoNumber();
                        break;
                    case 2:
                        actions.moveDetective(DetectiveName.TOBBY, 1);
                        actions.setLastActionPlayed(ActionToken.JOKER);
                        jockerBtn.setVisible(false);
                        updateActionsTodoNumber();
                        break;
                }

            } else {
                // tour mr Jack
                Object[] options = {DetectiveName.SHERLOCK.toString(), DetectiveName.WATSON.toString(), DetectiveName.TOBBY.toString(), "Do nothing"};
                int n = JOptionPane.showOptionDialog(f,
                        "Quel détective voulez-vous déplacer",
                        "Que voulez-vous faire ?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                switch (n) {
                    case 0:
                        actions.moveDetective(DetectiveName.SHERLOCK, 1);
                        actions.setLastActionPlayed(ActionToken.JOKER);
                        jockerBtn.setVisible(false);
                        updateActionsTodoNumber();
                        break;
                    case 1:
                        actions.moveDetective(DetectiveName.WATSON, 1);
                        actions.setLastActionPlayed(ActionToken.JOKER);
                        jockerBtn.setVisible(false);
                        updateActionsTodoNumber();
                        break;
                    case 2:
                        actions.moveDetective(DetectiveName.TOBBY, 1);
                        actions.setLastActionPlayed(ActionToken.JOKER);
                        jockerBtn.setVisible(false);
                        updateActionsTodoNumber();
                        break;
                    case 3:
                        actions.setLastActionPlayed(ActionToken.JOKER);
                        jockerBtn.setVisible(false);
                        updateActionsTodoNumber();
                        break;
                }
            }
            // maj du plateau de jeu
            updateUIDetective(board.getDetectiveBoard());
        });
        f.add(jockerBtn);

        // action bouger sherlock
        sherlockBtn = new JButton("loading");
        sherlockBtn.setBounds((int) (1100 * X_SCALE), (int) (220 * Y_SCALE), (int) (100 * X_SCALE), (int) (100 * Y_SCALE));
        try {
            Image sherlockImg = ImageIO.read(getClass().getResource("/SHERLOCK.png"));
            sherlockBtn.setIcon(new ImageIcon(sherlockImg.getScaledInstance((int) (110 * X_SCALE), (int) (100 * Y_SCALE), 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sherlockBtn.addActionListener(e -> {
            // dialogue nombre de case à bouger
            Object[] options = {"1", "2"};
            int n = JOptionPane.showOptionDialog(f,
                    "De combien de cases voulez-vous faire avancer Sherlock",
                    "Que voulez-vous faire ?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (n == 1) {
                // bouge detective
                actions.moveDetective(DetectiveName.SHERLOCK, 2);
                updateUIDetective(board.getDetectiveBoard());
                actions.setLastActionPlayed(ActionToken.HOLMES);
                sherlockBtn.setVisible(false);
                updateActionsTodoNumber();
            } else if (n == 0) {
                actions.moveDetective(DetectiveName.SHERLOCK, 1);
                updateUIDetective(board.getDetectiveBoard());
                actions.setLastActionPlayed(ActionToken.HOLMES);
                sherlockBtn.setVisible(false);
                updateActionsTodoNumber();
            }
        });
        f.add(sherlockBtn);

        // action bouge watson
        watsonBtn = new JButton("loading");
        watsonBtn.setBounds((int) (1200 * X_SCALE), (int) (220 * Y_SCALE), (int) (100 * X_SCALE), (int) (100 * Y_SCALE));
        try {
            Image watsonImg = ImageIO.read(getClass().getResource("/WATSON.png"));
            watsonBtn.setIcon(new ImageIcon(watsonImg.getScaledInstance((int) (110 * X_SCALE), (int) (100 * Y_SCALE), 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        watsonBtn.addActionListener(e -> {
            Object[] options = {"1", "2"};
            int n = JOptionPane.showOptionDialog(f,
                    "De combien de cases voulez-vous faire avancer Watson",
                    "Que voulez-vous faire ?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            //System.out.println(n);
            if (n == 1) {
                actions.moveDetective(DetectiveName.WATSON, 2);
                updateUIDetective(board.getDetectiveBoard());
                actions.setLastActionPlayed(ActionToken.WATSON);
                watsonBtn.setVisible(false);
                updateActionsTodoNumber();
            } else if (n == 0) {
                actions.moveDetective(DetectiveName.WATSON, 1);
                updateUIDetective(board.getDetectiveBoard());
                actions.setLastActionPlayed(ActionToken.WATSON);
                watsonBtn.setVisible(false);
                updateActionsTodoNumber();
            }
            ;
        });
        f.add(watsonBtn);

        // action bouge Tobby
        tobbyBtn = new JButton("loading");
        tobbyBtn.setBounds((int) (1300 * X_SCALE), (int) (220 * Y_SCALE), (int) (100 * X_SCALE), (int) (100 * Y_SCALE));
        try {
            Image tobbyImg = ImageIO.read(getClass().getResource("/TOBBY.png"));
            tobbyBtn.setIcon(new ImageIcon(tobbyImg.getScaledInstance((int) (110 * X_SCALE), (int) (100 * Y_SCALE), 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tobbyBtn.addActionListener(e -> {
            Object[] options = {"1", "2"};
            int n = JOptionPane.showOptionDialog(f,
                    "De combien de cases voulez-vous faire avancer Tobby",
                    "Que voulez-vous faire ?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            //System.out.println(n);
            if (n == 1) {
                actions.moveDetective(DetectiveName.TOBBY, 2);
                updateUIDetective(board.getDetectiveBoard());
                actions.setLastActionPlayed(ActionToken.LE_CHIEN);
                tobbyBtn.setVisible(false);
                updateActionsTodoNumber();
            } else if (n == 0) {
                actions.moveDetective(DetectiveName.TOBBY, 1);
                updateUIDetective(board.getDetectiveBoard());
                actions.setLastActionPlayed(ActionToken.LE_CHIEN);
                tobbyBtn.setVisible(false);
                updateActionsTodoNumber();
            }

        });
        f.add(tobbyBtn);
    }

    private void endUIInit() {
        // fin de l'initialisation de la fenetre
        f.setSize((int) (1800 * X_SCALE), (int) (1100 * Y_SCALE));
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void districtClick(ActionEvent ae, int x, int y) {
        // évenement click boutton district
        if (actionMode.equals("ROTATE")) {
            // action rotation
            if (lastrotateX == x && lastrotateY == y) {
                // cas de la rotation 2x même tour
                showpopup("Vous ne pouvez pas pivoter deux fois le même district lors d'un même tour", "Message");
                actionMode = "NONE";
            } else {
                actions.rotateDistrict(x, y, rotateOrientation);
                actions.setLastActionPlayed(ActionToken.ROTATION);
                // suppression de l'action
                rotateBtn.setVisible(false);
                updateActionsTodoNumber();
                // maj ui
                actionMode = "NONE";
                updateUIDistrict(board.getDistrictBoard());
                lastrotateX = x;
                lastrotateY = y;
            }


        } else if (actionMode.equals("ROTATE2")) {
            //action 2 eme carte rotation
            if (lastrotateX == x && lastrotateY == y) {
                // aloready rotated
                showpopup("Vous ne pouvez pas pivoter deux fois le même district lors d'un même tour", "Message");
                actionMode = "NONE";
            } else {
                actions.rotateDistrict(x, y, rotateOrientation);
                actions.setLastActionPlayed(ActionToken.ROTATION2);
                rotateBtn2.setVisible(false);
                updateActionsTodoNumber();
                actionMode = "NONE";
                updateUIDistrict(board.getDistrictBoard());
                lastrotateX = x;
                lastrotateY = y;
            }
        } else if (actionMode.equals("SWAP")) {
            // action swap 2 district
            tmpx = x;
            tmpy = y;
            actionMode = "SWAP2";
        } else if (actionMode.equals("SWAP2")) {
            actions.swapDistrict(tmpx, tmpy, x, y);
            actions.setLastActionPlayed(ActionToken.ECHANGE);
            swapBtn.setVisible(false);
            updateActionsTodoNumber();
            actionMode = "NONE";
            updateUIDistrict(board.getDistrictBoard());
        }
        //System.out.println(x + " - " + y);
    }

    public void updateUIDistrict(District[][] districtBoard) {
        // maj des districts
        System.out.println("refresh district UI...");
        // parcour du tableau des district
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // recupération des infos sur le district
                Orientation orientation = districtBoard[i][j].getOrientation();
                AlibiName detectiveName = districtBoard[i][j].getCharacter();
                boolean isRecto = districtBoard[i][j].isRecto();
                boolean isCross = districtBoard[i][j].isCross();

                // choix de la bonne image texture
                String fileName;
                if (isRecto) {
                    fileName = "/" + detectiveName.toString() + "-district.png";
                } else {
                    fileName = "/empty-district.png";
                    if (isCross) {
                        fileName = "/cross-district.png";
                    }

                }

                // lecture de l'image
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource(fileName));

                    // rotation de l'image
                    if (orientation != Orientation.NORTH) {
                        //BufferedImage image2 = image;
                        switch (orientation) {
                            case EAST:
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
                    // met l'image sur le boutton
                    districtBtn[i][j].setIcon(new ImageIcon(image.getScaledInstance((int) (255 * X_SCALE), (int) (255 * Y_SCALE), 1)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateUIDetective(ArrayList<DetectiveToken>[] detectiveBoard) {
        // maj des detectives sur l'UI
        System.out.println("refresh detectives UI");
        // parcours de la liste des detectives
        for (int i = 0; i < 12; i++) {
            ArrayList<DetectiveToken> detectivePlace = detectiveBoard[i];

            if (!detectivePlace.isEmpty()) {
                boolean sherlok, watson, tobby;
                sherlok = watson = tobby = false;

                // recupération des nom des detectives sur la case
                for (DetectiveToken detectiveToken : detectivePlace) {
                    if (detectiveToken.getDetectiveName() == DetectiveName.SHERLOCK) {
                        sherlok = true;
                    } else if (detectiveToken.getDetectiveName() == DetectiveName.WATSON) {
                        watson = true;
                    } else if (detectiveToken.getDetectiveName() == DetectiveName.TOBBY) {
                        tobby = true;
                    }
                }
                // choix de la bonne image
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
                    // ajout de l'image sur la boutton
                    detectivesBtn[i].setIcon(new ImageIcon(image.getScaledInstance((int) (205 * X_SCALE), (int) (200 * Y_SCALE), 1)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                // pas de detective sur la case : texture blanche
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource("/BLANK.png"));
                    detectivesBtn[i].setIcon(new ImageIcon(image.getScaledInstance((int) (255 * X_SCALE), (int) (255 * Y_SCALE), 1)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static BufferedImage rotate(BufferedImage bimg, double angle) {
        // rotation d'une image
        int w = bimg.getWidth();
        int h = bimg.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w / 2, h / 2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

    private void updateActionsTodoNumber() {
        // nombre d'action restante à jouer
        int i = 0;
        if (alibiBtn.isVisible()) {
            i++;
        }
        if (rotateBtn.isVisible()) {
            i++;
        }
        if (rotateBtn2.isVisible()) {
            i++;
        }
        if (swapBtn.isVisible()) {
            i++;
        }
        if (jockerBtn.isVisible()) {
            i++;
        }
        if (watsonBtn.isVisible()) {
            i++;
        }
        if (sherlockBtn.isVisible()) {
            i++;
        }
        if (tobbyBtn.isVisible()) {
            i++;
        }
        actions.setActionTodo(i);

        if (i == 0) {
            // fin du tour => autoriser toute rotation
            lastrotateX = -1;
            lastrotateY = -1;
        }
    }

    public void setTurn(int turn) {
        // maj du tour
        this.turn = turn;
        if (turn == 0) {
            setTurnLabel("C'est au tour du détective de jouer");
        } else {
            setTurnLabel("C'est au tour de Mr Jack de jouer");
        }
    }

    private void setTurnLabel(String txt) {
        // mise à jour du label de tour
        tourRoleLabel.setText(txt);
    }

    public void addAlibi(AlibiCard alibiCard) {
        // ajoute carte alibi à la liste ui
        alibiListModel.addElement(alibiCard.getName().toString().replace("_", " ") + " - " + alibiCard.getHourGlassCount() + " hourglasse(s)");
    }

    public void setActionsEnabled(ArrayList<ActionToken> actionTokens) {
        // active les actions disponibles
        alibiBtn.setVisible(false);
        rotateBtn.setVisible(false);
        rotateBtn2.setVisible(false);
        swapBtn.setVisible(false);
        jockerBtn.setVisible(false);
        watsonBtn.setVisible(false);
        sherlockBtn.setVisible(false);
        tobbyBtn.setVisible(false);
        for (ActionToken actionToken : actionTokens) {
            switch (actionToken) {
                case ALIBI:
                    alibiBtn.setVisible(true);
                    break;
                case JOKER:
                    jockerBtn.setVisible(true);
                    break;
                case HOLMES:
                    sherlockBtn.setVisible(true);
                    break;
                case WATSON:
                    watsonBtn.setVisible(true);
                    break;
                case LE_CHIEN:
                    tobbyBtn.setVisible(true);
                    break;
                case ECHANGE:
                    swapBtn.setVisible(true);
                    break;
                case ROTATION:
                    rotateBtn.setVisible(true);
                    break;
                case ROTATION2:
                    rotateBtn2.setVisible(true);
                    break;
            }
        }
    }

    public void updateHourglass(int hourglasses) {
        // maj du nombre de sablier
        hourGlassLabel.setText("Mr Jack a " + String.valueOf(hourglasses) + " sablier(s)");
    }

    public void showMrJackName(AlibiName alibiName) {
        // fenetre de dialogue avec le nom de mr jack
        try {
            Image icon = ImageIO.read(getClass().getResource("/" + alibiName + "-district.png")).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JOptionPane.showMessageDialog(
                    f,
                    "Mr Jack est " + alibiName,
                    "Mr Jack est " + alibiName, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(icon));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMrJackAlibiCard(AlibiCard alibiCard) {
        // fenetre de dialogue avec la carte alibi piochée par mr Jack
        JOptionPane.showMessageDialog(
                f,
                "AlibiCard :" + alibiCard.getName().toString().replace("_", " ") + " (" + alibiCard.getHourGlassCount() + ")",
                "Alibi card", JOptionPane.INFORMATION_MESSAGE,
                null);
    }

    public void showpopup(String msg, String windowTitle) {
        // fenetre popup pour divers information à l'utilisateur
        JOptionPane.showMessageDialog(
                f,
                msg,
                windowTitle, JOptionPane.INFORMATION_MESSAGE,
                null);
    }

}
