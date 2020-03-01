/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlinePeaple;

import DBConnection.DBHandler;
import GamePane.GamePaneController;
import Records.Records;
import Scene.AllScenes;
import Socket.MySocket;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author General
 */
public class ListOfOnlinePeapleController implements Initializable {

    @FXML
    private TableView<OnlinePeaple> tableOnlinePeaple;
    @FXML
    private TableColumn<OnlinePeaple, Integer> onlineNumber;
    @FXML
    private TableColumn<OnlinePeaple, String> listOlinePeaple;
    @FXML
    private Button exitGame;
    @FXML
    private Button sendRequest;
    private List lists;
    private Connection connection;
    private ObservableList<OnlinePeaple> list;
    private DBHandler dbHandler;
    private Integer count;
    private AllScenes scenes;
    private String id;
    private String player1name;
    private Socket mySocket = null;
    private DataInputStream dis = null;
    private PrintStream ps = null;
    @FXML
    private HBox exitAndSend;
    @FXML
    private Label progress;
    private OnlinePeaple player2name;
    private ActionEvent e;
    protected Parent root;
    protected Scene scene;
    protected Stage stage;
    private MySocket socket;
    private boolean checkSocket;
    private String idPlayer;
    @FXML
    private Button reloadGame;
    @FXML
    private Label progressRefuse;
    private String IPServer;
    private boolean turn;

    @FXML
    private void backToSingleMultiOnline(ActionEvent event) throws Exception {
        socket.printStream(idPlayer + "," + "notActive");
        scenes.getSceneSingleMultiOnline(event,IPServer);
    }

    @FXML
    private void sendRequest(ActionEvent event) throws Exception {
        player2name = tableOnlinePeaple.getSelectionModel().getSelectedItem();
        socket = new MySocket(IPServer);
        checkSocket = socket.getCheckSocket();
        if(checkSocket == true){
            if(player2name != null){                
                progress.setVisible(true);
                exitAndSend.setVisible(false);
                socket.printStream(player1name + "," + "onlinePeaple" + "," + player2name.getPlayerName());
//                ps.println(player1name + "," + "onlinePeaple" + "," + player2name.getPlayerName());
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You Should select one of player to continue playing");
                alert.showAndWait();
            }
        }

    }
    public void setIPServer(String ip){
        IPServer = ip;
    }
    public void setStage(Stage stageOnline){
        stage = stageOnline;
    }
    public ListOfOnlinePeapleController() throws IOException {
        socket = new MySocket(IPServer);
        checkSocket = socket.getCheckSocket();
        
        if(checkSocket == true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String value;
                        String str[];
                        try {
                            if (!socket.getMySocket().isClosed()) {
                                value = socket.dataInputStream();
                                str = value.split(",");
                                if(exitAndSend.isVisible() == true){
                                    if (str[0].compareTo(player1name) == 0 && str[1].compareTo("request") == 0) {
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                Alert record;
                                                record = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to play with " + str[2] + " ?", ButtonType.YES, ButtonType.NO);
                                                record.showAndWait().ifPresent((ButtonType type) -> {
                                                    if (type == ButtonType.YES) {
                                                        //System.out.println("done for player1111111111111111111");
                                                        socket.printStream(str[2] + "," + "accept");
    //                                                    ps.println(str[2] + "," + "accept");
                                                        socket.printStream(idPlayer + "," + "notActive");
                                                        getGamePaneOnlineScene(str[0], str[2],false,IPServer);
//                                                        progress.setVisible(false);
//                                                        exitAndSend.setVisible(true);
                                                        return;
                                                    }else if (type == ButtonType.NO) {
                                                        System.out.println("refuse player "+ str[2]);
                                                        socket.printStream(str[2] + "," + "refuse");
                                                        return;
                                                    }
                                                });
                                            }
                                        });
                                    } else {
                                       // System.out.println("falseeeeeeeeeeeeeeeeeeee");
                                    }
                                }

                                //if(progressRefuse.isVisible() == true || progress.isVisible() == true){
                                    if(str[0].compareTo(player1name) == 0 && str[1].compareTo("refuse") == 0){
                                        progress.setVisible(false);
                                        progressRefuse.setVisible(true);
                                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                                        pause.setOnFinished((e) -> {
                                            progressRefuse.setVisible(false);

                                            exitAndSend.setVisible(true);
                                        });
                                        pause.playFromStart();
                                    }
                                    if(str[0].compareTo(player1name) == 0 && str[1].compareTo("response") == 0){
                                       // System.out.println("done for response2222222222222222222");
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                socket.printStream(idPlayer + "," + "notActive");
                                                getGamePaneOnlineScene(player2name.getPlayerName(),player1name ,true,IPServer);
                                                return;
                                             }
                                        });
                                    }
                                //}
                            }
                        } catch (Exception ex) {
                            try {
                                socket.closeSocket();
                                System.out.println("server shoutdown or network is not connection 2");
                                break;
                            } catch (IOException ex1) {
                                System.out.println("server shoutdown or network is not connection 22");
                                break;
                            }

//                                System.out.println("server shoutdown or network is not connection 2");
                        }
                    }
                }
            });
            thread.start();
        }
    }
    @FXML
    public void replay() throws IOException{
        getTablecontant(idPlayer,IPServer);
    }
    public void getGamePaneOnlineScene(String player1,String player2,boolean turn,String ip){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GamePane/GamePane.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ListOfOnlinePeapleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        GamePaneController display = loader.getController();
        display.changeMode("online","x");
        display.setPlayerName(player1,player2,"x");
        display.setPlayerTurn(turn);
        display.setIPServer(ip);
        root = loader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Game Pane");
        stage.show();
    }
    public void setPlayer(String player1) {
        player1name = player1;
    }

    @FXML
    private void sendRequestMouseEnter(MouseEvent event) {
        sendRequest.setCursor(Cursor.HAND);
        sendRequest.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        sendRequest.setTextFill(Color.rgb(11, 48, 76));
    }

    @FXML
    private void sendRequestMouseExit(MouseEvent event) {
        sendRequest.setCursor(Cursor.HAND);
        sendRequest.setBackground(null);
        sendRequest.setTextFill(Color.rgb(255, 255, 153));
    }

    @FXML
    private void exitGameMouseEnter(MouseEvent event) {
        exitGame.setCursor(Cursor.HAND);
        exitGame.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        exitGame.setTextFill(Color.rgb(11, 48, 76));
    }

    @FXML
    private void exitGameMouseExit(MouseEvent event) {
        exitGame.setCursor(Cursor.HAND);
        exitGame.setBackground(null);
        exitGame.setTextFill(Color.rgb(255, 255, 153));
    }
    @FXML
    private void reloadGameMouseEnter(MouseEvent event) {
        reloadGame.setCursor(Cursor.HAND);
        reloadGame.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        reloadGame.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void reloadGameMouseExit(MouseEvent event) {
        reloadGame.setCursor(Cursor.HAND);
        reloadGame.setBackground(null);
        reloadGame.setTextFill(Color.rgb(255, 255, 153));
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1), sendRequest);
        fadeIn1.setFromValue(0);
        fadeIn1.setToValue(1);
        fadeIn1.setDelay(Duration.seconds(1));
        fadeIn1.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), exitGame);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);
        fadeIn2.setDelay(Duration.seconds(1));
        fadeIn2.play();
        FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(1), reloadGame);
        fadeIn3.setFromValue(0);
        fadeIn3.setToValue(1);
        fadeIn3.setDelay(Duration.seconds(1));
        fadeIn3.play();
        scenes = new AllScenes();
        dbHandler = new DBHandler();
        reloadGame.setBackground(null);
        sendRequest.setBackground(null);
        exitGame.setBackground(null);
        progressRefuse.setBackground(new Background(new BackgroundFill(Color.rgb(255, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        progressRefuse.setTextFill(Color.WHITE);
        progress.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        progress.setTextFill(Color.BLUE);
    }

    public void getTablecontant(String id,String ip) throws IOException {
        idPlayer = id;
        setIPServer(IPServer);
        socket = new MySocket(ip);
        checkSocket = socket.getCheckSocket();
        
        if(checkSocket == true){
            socket.printStream(id + "," + "listOfOnlinePeaple");
//            ps.println(id + "," + "listOfOnlinePeaple");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String rs;
                    String arr[];
                    rs = socket.dataInputStream();
                    arr = rs.split(",");
                    lists = new ArrayList();
                    count = 1;
                    for (int i = 0; i < arr.length; i += 2) {
                        if (arr[i + 1].equals("1")) {
                            lists.add(new OnlinePeaple(count, arr[i]));
                            count++;
                        }
                    }
                    list = FXCollections.observableArrayList(lists);
                    onlineNumber.setCellValueFactory(new PropertyValueFactory<OnlinePeaple, Integer>("id"));
                    listOlinePeaple.setCellValueFactory(new PropertyValueFactory<OnlinePeaple, String>("playerName"));
                    tableOnlinePeaple.setPlaceholder(new Label("There is NO Online Peaple"));
                    tableOnlinePeaple.setItems(list);
                }
            });
        }
    }

}
