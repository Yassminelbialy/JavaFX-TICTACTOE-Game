/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NamesMulti;

import Scene.AllScenes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Yasmeen
 */
public class NamesMultiController implements Initializable {
    private AllScenes scenes;
    @FXML
    private Button backToSingleMultiOnline;
    @FXML
    private Button playTheGame;
    @FXML
    private TextField firstPlayer;
    @FXML
    private TextField secondPlayer;
    @FXML
    private Label errorFirstPlayer;
    @FXML
    private Label errorSecondPlayer;
    public String IPServer;
    
    public void setIPServer(String ip){
        IPServer = ip;
    }
    @FXML
    private void goToGamePane(ActionEvent event) throws Exception {
        if(firstPlayer.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You should enter the first player name");
            alert.showAndWait();
        }else if(secondPlayer.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You should enter the second player name");
            alert.showAndWait();
        }else if(firstPlayer.getText().equals(secondPlayer.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The two player is the same name.\n Change one of them");
            alert.showAndWait();
        }else{
            scenes.getSceneGamePane(event,firstPlayer.getText(),secondPlayer.getText(),"multi","x");
        }
    }
    @FXML
    private void goToSingleMultiOnline(ActionEvent event) throws Exception {
        scenes.getSceneSingleMultiOnline(event,IPServer);
    }
    @FXML
    private void playTheGameMouseEnter(MouseEvent event) {
        playTheGame.setCursor(Cursor.HAND);
        playTheGame.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        playTheGame.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void playTheGameMouseExit(MouseEvent event) {
        playTheGame.setCursor(Cursor.HAND);
        playTheGame.setBackground(null);
        playTheGame.setTextFill(Color.rgb(255, 255, 153));
    }
    @FXML
    private void backToSingleMultiOnlineMouseEnter(MouseEvent event) {
        backToSingleMultiOnline.setCursor(Cursor.HAND);
        backToSingleMultiOnline.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        backToSingleMultiOnline.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void backToSingleMultiOnlineMouseExit(MouseEvent event) {
        backToSingleMultiOnline.setCursor(Cursor.HAND);
        backToSingleMultiOnline.setBackground(null);
        backToSingleMultiOnline.setTextFill(Color.rgb(255, 255, 153));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1), playTheGame);
        fadeIn1.setFromValue(0);
        fadeIn1.setToValue(1);
        fadeIn1.setDelay(Duration.seconds(0.5));
        fadeIn1.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), backToSingleMultiOnline);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);
        fadeIn2.setDelay(Duration.seconds(0.5));
        fadeIn2.play();
        scenes = new AllScenes();
        playTheGame.setBackground(null);
        backToSingleMultiOnline.setBackground(null);
    }    
    
}
