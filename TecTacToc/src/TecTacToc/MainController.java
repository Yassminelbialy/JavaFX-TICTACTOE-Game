/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TecTacToc;

import Scene.AllScenes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author General
 */
public class MainController implements Initializable {
    
    @FXML
    private Button aboutUS;
    @FXML
    private Button exitGame;
    @FXML
    private Button playGame;
    
    private AllScenes scenes;

    
    @FXML
    private void enterTheGame(ActionEvent event) throws Exception {
        scenes.getSceneSingleMultiOnline(event,"");
    }
    @FXML
    private void aboutTheCreatorOfTheGame(ActionEvent event) throws Exception {
        scenes.getSceneAbout(event);
    }
    @FXML
    private void exitTheGame(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
    @FXML
    private void startGameMouseEnter(MouseEvent event) {
        playGame.setCursor(Cursor.HAND);
        playGame.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        playGame.setTextFill(Color.rgb(11, 48, 76));

    }
    @FXML
    private void startGameMouseExit(MouseEvent event) {
        playGame.setCursor(Cursor.HAND);
        playGame.setBackground(null);
        playGame.setTextFill(Color.WHITE);
    }
    @FXML
    private void aboutUSMouseEnter(MouseEvent event) {
        aboutUS.setCursor(Cursor.HAND);
        aboutUS.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        aboutUS.setTextFill(Color.rgb(11, 48, 76));

    }
    @FXML
    private void aboutUSMouseExit(MouseEvent event) {
        aboutUS.setCursor(Cursor.HAND);
        aboutUS.setBackground(null);
        aboutUS.setTextFill(Color.WHITE);
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
        exitGame.setTextFill(Color.WHITE);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(2), playGame);
        fadeIn1.setFromValue(0);
        fadeIn1.setToValue(1);
        fadeIn1.setDelay(Duration.seconds(0.2));
        fadeIn1.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(2), aboutUS);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);
        fadeIn2.setDelay(Duration.seconds(0.4));
        fadeIn2.play();
        FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(2), exitGame);
        fadeIn3.setFromValue(0);
        fadeIn3.setToValue(1);
        fadeIn3.setDelay(Duration.seconds(0.6));
        fadeIn3.play();
        playGame.setBackground(null);
        aboutUS.setBackground(null);
        exitGame.setBackground(null);
        scenes = new AllScenes();
    }    
    
}
