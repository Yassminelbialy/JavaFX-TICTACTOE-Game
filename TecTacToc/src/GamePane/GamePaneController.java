/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import Scene.AllScenes;
import Socket.MySocket;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Yasmeen
 */
public class GamePaneController implements Initializable {

    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;
    @FXML
    private Label playerTurn;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;
    @FXML
    private Button backBtn;
    @FXML
    private Button newGameBtn;
    private AllScenes scenes;
    private Integer score1 = 0;
    private Integer score2 = 0;
    private Integer counter = 0;
    private String checkMode;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    private Button[] buttons;
    private Random randomButtons;
    private Integer randomNumber;
    private String selectedXO;
    private String winner = "";
    @FXML
    private VBox xoBox;
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private Media media;
    private Media media2;
    private String myMap;
    private LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
    private String mySavingMap;
    private Integer numberOfRecord;
    private Socket mySocket = null;
    private DataInputStream dis = null;
    private PrintStream ps = null;
    private Button btn;
    public Integer counts = 4;
    private boolean setTurn;
    private MySocket socket;
    private boolean checkSocket;
    @FXML
    private Label serverError;
    private String IPServer;
    @FXML
    private Label playerOut;

    @FXML
    private void backToSingleMultiOnline(ActionEvent event) throws Exception {
        if (counter == 9 && !checkMode.equals("record")) {
            Alert record;
            record = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save this game", ButtonType.YES, ButtonType.NO);
            record.showAndWait().ifPresent((ButtonType type) -> {
                if (type == ButtonType.YES) {
                    recording();
                }
            });
        }
        mediaView.setVisible(false);
        xoBox.setVisible(true);
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.stop();
        if (checkMode.equals("online")) {
            mediaView.setVisible(false);
            xoBox.setVisible(true);
            mediaPlayer2.seek(mediaPlayer2.getStartTime());
            mediaPlayer2.stop();
        }
        if (checkMode.equals("online")) {
            socket.printStream(player1Name.getText() + "," + "playerOut" + "," + player2Name.getText());
        }
        scenes.getSceneSingleMultiOnline(event, IPServer);

    }

    @FXML
    private void playNewGame(ActionEvent event) {

        if (counter == 9 && !checkMode.equals("record")) {
            Alert record;
            record = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save this game?", ButtonType.YES, ButtonType.NO);
            record.showAndWait().ifPresent((ButtonType type) -> {
                if (type == ButtonType.YES) {
                    recording();
                }
            });
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setText("");
                buttons[i].setBackground(null);
            }
            counter = 0;
            if (!winner.equals("")) {
                if (winner.equals("x")) {
                    playerTurn.setTextFill(Color.rgb(127, 188, 241));
                    playerTurn.setText("x");
                } else if (winner.equals("o")) {
                    playerTurn.setTextFill(Color.rgb(229, 87, 255));
                    playerTurn.setText("o");
                } else {
                    playerTurn.setTextFill(Color.rgb(127, 188, 241));
                    playerTurn.setText("x"); 
                }
            }
        }
        if (checkMode.equals("single")) {
            if (!selectedXO.equals(String.valueOf(playerTurn.getText()))) {
                computerPlayer();
            }
        }
        if (checkMode.equals("online")) {
            setTurn = !setTurn;
        }
        mediaView.setVisible(false);
        xoBox.setVisible(true);
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.stop();
        if (checkMode.equals("online")) {
            mediaView.setVisible(false);
            xoBox.setVisible(true);
            mediaPlayer2.seek(mediaPlayer2.getStartTime());
            mediaPlayer2.stop();
        }

    }

    public void setPlayerName(String Player1Name, String Player2Name, String Selected) {
        if (checkMode.equals("single")) {
            if (Selected.equals("x")) {
                this.player1Name.setText(Player2Name);
                this.player2Name.setText(Player1Name);
            } else if (Selected.equals("o")) {
                this.player1Name.setText(Player1Name);
                this.player2Name.setText(Player2Name);
            }
            return;
        }
        this.player1Name.setText(Player1Name);
        this.player2Name.setText(Player2Name);
    }

    public void setPlayerTurn(boolean turn) {
        setTurn = turn;
    }

    public void changeMode(String Mode, String Selected) {
        checkMode = Mode;
        selectedXO = Selected;
        if (Selected.equals("x")) {
            playerTurn.setTextFill(Color.rgb(127, 188, 241));
        } else {
            playerTurn.setTextFill(Color.rgb(229, 87, 255));
        }
        playerTurn.setText(Selected);
    }

    @FXML
    private void eventMouseEnter(MouseEvent event) {
        btn = (Button) event.getSource();
        btn.setCursor(Cursor.HAND);
        if (counter < 9) {
            if (btn.getText().equals("")) {
                if (playerTurn.getText().equals("x")) {
                    btn.setBackground(new Background(new BackgroundFill(Color.rgb(127, 188, 241), CornerRadii.EMPTY, Insets.EMPTY)));
                } else if (playerTurn.getText().equals("o")) {
                    btn.setBackground(new Background(new BackgroundFill(Color.rgb(229, 87, 255), CornerRadii.EMPTY, Insets.EMPTY)));
                }
                btn.setTextFill(Color.WHITE);
            }
        }
    }

    @FXML
    private void eventMouseExit(MouseEvent event) {
        btn = (Button) event.getSource();
        btn.setCursor(Cursor.HAND);
        if (counter < 9) {
            btn.setBackground(null);
            if (btn.getText().equals("")) {
                if (playerTurn.getText().equals("x")) {
                    btn.setTextFill(Color.rgb(127, 188, 241));
                } else if (playerTurn.getText().equals("o")) {
                    btn.setTextFill(Color.rgb(229, 87, 255));
                }
            }
        }
    }

    @FXML
    private void buttonHandle(MouseEvent event) {
        if (counter < 9) {
            btn = (Button) event.getSource();
            String value = btn.getText();
            if (checkMode.equals("single")) {
                if (value.equals("")) {
                    btn.setText(playerTurn.getText());
                    changeOX(btn);
                    counter++;
                    lhm.put(btn.getId(), btn.getText());
                    checkButtons();
                    computerPlayer();
                }
            } else if (checkMode.equals("multi")) {
                if (value.equals("")) {
                    btn.setText(playerTurn.getText());
                    changeOX(btn);
                    counter++;
                    checkButtons();
                    lhm.put(btn.getId(), btn.getText());
                }
            } else if (checkMode.equals("online")) {
                if (setTurn == true) {
                    if (value.equals("")) {
                        socket.printStream(btn.getId() + "," + "online" + "," + player1Name.getText() + "," + player2Name.getText());
//                        ps.println(btn.getId()+","+"online"+","+player1Name.getText()+","+player2Name.getText());
                    }
                }
            }
        }
    }

    private void changeOX(Button btn) {
        if (btn.getText().equals("x")) {
            playerTurn.setTextFill(Color.rgb(229, 87, 255));
            playerTurn.setText("o");
            btn.setTextFill(Color.rgb(127, 188, 241));
            btn.setBackground(null);
        } else if (btn.getText().equals("o")) {
            playerTurn.setTextFill(Color.rgb(127, 188, 241));
            playerTurn.setText("x");
            btn.setTextFill(Color.rgb(229, 87, 255));
            btn.setBackground(null);
        }
    }

    private void computerPlayer() {
        if (counter < 9) {
            while (true) {
                randomButtons = new Random();
                randomNumber = randomButtons.nextInt(9);
                if (buttons[randomNumber].getText().equals("")) {
                    buttons[randomNumber].setText(playerTurn.getText());
                    if (playerTurn.getText().equals("x")) {
                        playerTurn.setTextFill(Color.rgb(229, 87, 255));
                        playerTurn.setText("o");
                        buttons[randomNumber].setTextFill(Color.rgb(127, 188, 241));
                        buttons[randomNumber].setBackground(null);
                    } else if (playerTurn.getText().equals("o")) {
                        playerTurn.setTextFill(Color.rgb(127, 188, 241));
                        playerTurn.setText("x");
                        buttons[randomNumber].setTextFill(Color.rgb(229, 87, 255));
                        buttons[randomNumber].setBackground(null);
                    }
                    lhm.put(buttons[randomNumber].getId(), buttons[randomNumber].getText());
                    break;
                }
            }
            counter++;
            checkButtons();
        }
    }

    private void winner() {
        if (winner.equals("x")) {
            playerTurn.setTextFill(Color.rgb(127, 188, 241));
            playerTurn.setText(player2Name.getText() + " won");
        } else if (winner.equals("o")) {

            playerTurn.setTextFill(Color.rgb(229, 87, 255));
            playerTurn.setText(player1Name.getText() + " won");
        }
        video();

    }

    public void video() {
        if (!"online".equalsIgnoreCase(checkMode) | (checkMode.equals("online") && setTurn == true)) {
            PauseTransition pauseVedio = new PauseTransition(Duration.seconds(2));
            pauseVedio.setOnFinished((event) -> {
                mediaView.setVisible(true);
                xoBox.setVisible(false);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.play();
            });
            pauseVedio.play();
        } else if (checkMode.equals("online") && setTurn == false) {
            PauseTransition pauseVedio = new PauseTransition(Duration.seconds(2));
            pauseVedio.setOnFinished((event) -> {
                playerTurn.setText("You are Loser");
                mediaView.setVisible(true);
                xoBox.setVisible(false);
                mediaView.setMediaPlayer(mediaPlayer2);
                mediaPlayer2.setAutoPlay(true);
                mediaPlayer2.play();
            });
            pauseVedio.play();

        }

    }

    public void recording() {
        FileOutputStream fos;
        Path records = Paths.get("C:\\Records");
        try {
            Files.createDirectories(records);
            numberOfRecord = new File("C:\\Records").list().length + 1;
            String selectFile = "C:\\Records\\game" + numberOfRecord + ".txt";
            mySavingMap = "";
            mySavingMap += player1Name.getText() + "," + player1Score.getText() + ",";
            mySavingMap += player2Name.getText() + "," + player2Score.getText() + ",";
            lhm.entrySet().forEach((entry) -> {
                mySavingMap += entry.getKey() + "," + entry.getValue() + ",";
            });
            byte[] b = mySavingMap.getBytes();
            fos = new FileOutputStream(selectFile);
            fos.write(b);
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(GamePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void replayGame(String path, String mode) throws IOException, Exception {
        checkMode = mode;
        String data = new String(Files.readAllBytes(Paths.get(path)));
        String sp[] = data.split(",");
        player1Name.setText(sp[0]);
        player1Score.setText(sp[1]);
        player2Name.setText(sp[2]);
        player2Score.setText(sp[3]);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished((e) -> {
            for (int i = counts; i < sp.length; i += 2) {
                for (int j = 0; j < 9; j++) {
                    if (buttons[j].getId().compareTo(sp[i]) == 0) {
                        buttons[j].setText(sp[i + 1]);
                        changeOX(buttons[j]);
                        checkButtons();
                        break;
                    }
                }
                counts += 2;
                break;
            }
            if (counts < sp.length) {
                pause.playFromStart();
            }
        });
        pause.playFromStart();

    }

    public void style(Button btn1, Button btn2, Button btn3, Color color) {
        btn1.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        btn1.setTextFill(Color.WHITE);
        btn2.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        btn2.setTextFill(Color.WHITE);
        btn3.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        btn3.setTextFill(Color.WHITE);
    }

    public void checkEnd(String button1, String button2, String button3, Button btn1, Button btn2, Button btn3) {
        if (button1.equals("x") && button2.equals("x") && button3.equals("x")) {
            style(btn1, btn2, btn3, Color.rgb(127, 188, 241));
            player2Score.setText(String.valueOf(++score2));
            winner = "x";
        } else {
            style(btn1, btn2, btn3, Color.rgb(229, 87, 255));
            player1Score.setText(String.valueOf(++score1));
            winner = "o";
        }
        winner();
        counter = 9;

    }

    private void checkButtons() {
        String button1 = btn1.getText();
        String button2 = btn2.getText();
        String button3 = btn3.getText();
        String button4 = btn4.getText();
        String button5 = btn5.getText();
        String button6 = btn6.getText();
        String button7 = btn7.getText();
        String button8 = btn8.getText();
        String button9 = btn9.getText();
        if (button1.equals(button2) && button1.equals(button3) && button2.equals(button3) && !button1.equals("")) {
            checkEnd(button1, button2, button3, btn1, btn2, btn3);
        } else if (button1.equals(button4) && button1.equals(button7) && button4.equals(button7) && !button1.equals("")) {
            checkEnd(button1, button4, button7, btn1, btn4, btn7);
        } else if (button1.equals(button5) && button1.equals(button9) && button5.equals(button9) && !button1.equals("")) {
            checkEnd(button1, button5, button9, btn1, btn5, btn9);
        } else if (button3.equals(button5) && button3.equals(button7) && button5.equals(button7) && !button3.equals("")) {
            checkEnd(button3, button5, button7, btn3, btn5, btn7);
        } else if (button3.equals(button6) && button3.equals(button9) && button6.equals(button9) && !button3.equals("")) {
            checkEnd(button3, button6, button9, btn3, btn6, btn9);
        } else if (button4.equals(button5) && button4.equals(button6) && button5.equals(button6) && !button4.equals("")) {
            checkEnd(button4, button5, button6, btn4, btn5, btn6);
        } else if (button7.equals(button8) && button7.equals(button9) && button8.equals(button9) && !button7.equals("")) {
            checkEnd(button7, button8, button9, btn7, btn8, btn9);
        } else if (button2.equals(button5) && button2.equals(button8) && button5.equals(button8) && !button2.equals("")) {
            checkEnd(button2, button5, button8, btn2, btn5, btn8);
        } else if (counter == 9) {
            winner="f";
            playerTurn.setText("Draw");

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scenes = new AllScenes();

        buttons = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        String path = new File("src/Video/v1.mp4").getAbsolutePath();
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        String path2 = new File("src/Video/v2.mp4").getAbsolutePath();
        media2 = new Media(new File(path2).toURI().toString());
        mediaPlayer2 = new MediaPlayer(media2);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(null);
        }
        try {
            GamePaneController();
        } catch (IOException ex) {
            Logger.getLogger(GamePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setIPServer(String ip) {
        IPServer = ip;
    }

    public void GamePaneController() throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (checkMode.equals("online")) {
                    try {
                        socket = new MySocket(IPServer);
                    } catch (IOException ex) {
                        Logger.getLogger(GamePaneController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    checkSocket = socket.getCheckSocket();
                    if (checkSocket == true) {
                        Thread d = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (true) {
                                    String s;
                                    String str[];
                                    try {
                                        if (!socket.getMySocket().isClosed()) {
                                            s = socket.dataInputStream();
                                            str = s.split(",");
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (str[0].compareTo("playerOut") == 0 && str[1].compareTo(player1Name.getText()) == 0 && str[2].compareTo(player2Name.getText()) == 0) {
                                                        playerOut.setVisible(true);
                                                        xoBox.setDisable(true);
                                                    }
                                                    if (str[1].compareTo(player1Name.getText()) == 0 && str[2].compareTo(player2Name.getText()) == 0) {
                                                        for (int i = 0; i < buttons.length; i++) {
                                                            if (buttons[i].getId().compareTo(str[0]) == 0) {
                                                                buttons[i].setText(playerTurn.getText());
                                                                changeOX(buttons[i]);
                                                                counter++;
                                                                checkButtons();
                                                                lhm.put(buttons[i].getId(), buttons[i].getText());
                                                                setTurn = !setTurn;
                                                            }
                                                        }

                                                    }
                                                }
                                            });
                                        }
                                    } catch (Exception ex) {
                                        try {
                                            socket.closeSocket();
//                                            serverError.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                                            serverError.setVisible(true);
                                            xoBox.setDisable(true);
                                            System.out.println("server not found 1");
                                        } catch (IOException ex1) {
                                            System.out.println("server not found 2");
                                            break;
                                        }

                                    }
                                }
                            }
                        });
                        d.start();
                    }
                }
            }
        });
    }

}
