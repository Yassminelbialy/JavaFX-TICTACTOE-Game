/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SingleMode;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class SingleModeController implements Initializable {

    private AllScenes scenes;
    @FXML
    private ToggleGroup XO;
    @FXML
    private Button backToMain;
    @FXML
    private Button playGame;
    @FXML
    private TextField playerNameField;
    protected String playerName;
    @FXML
    private RadioButton checkO;
    @FXML
    private RadioButton checkX;
    private String IPServer;
    
    @FXML
    private void goToGamePane(ActionEvent event) throws Exception {
        playerName = playerNameField.getText();
        if(playerName.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Enter Your name, please");
            alert.showAndWait();
        }else if(XO.getSelectedToggle() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Select (O) or (X) to continue, please");
            alert.showAndWait();
        }else{
            String radioSelected = XO.getSelectedToggle().getUserData().toString();
            scenes.getSceneGamePane(event,playerName,"Pc","single",radioSelected);
        }
    }
    @FXML
    private void backToMain(ActionEvent event) throws Exception {
        scenes.getSceneSingleMultiOnline(event,IPServer);
    }
    public void setIPServer(String ip){
        IPServer = ip;
    }
    @FXML
    private void playGameMouseEnter(MouseEvent event) {
        playGame.setCursor(Cursor.HAND);
        playGame.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        playGame.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void playGameMouseExit(MouseEvent event) {
        playGame.setCursor(Cursor.HAND);
        playGame.setBackground(null);
        playGame.setTextFill(Color.rgb(255, 255, 153));
    }
    @FXML
    private void backToMainMouseEnter(MouseEvent event) {
        backToMain.setCursor(Cursor.HAND);
        backToMain.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        backToMain.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void backToMainMouseExit(MouseEvent event) {
        backToMain.setCursor(Cursor.HAND);
        backToMain.setBackground(null);
        backToMain.setTextFill(Color.rgb(255, 255, 153));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(2), playGame);
        fadeIn1.setFromValue(0);
        fadeIn1.setToValue(1);
        fadeIn1.setDelay(Duration.seconds(0.4));
        fadeIn1.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(2), backToMain);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);
        fadeIn2.setDelay(Duration.seconds(0.6));
        fadeIn2.play();
        checkO.setUserData("o");
        checkX.setUserData("x");
        playGame.setBackground(null);
        backToMain.setBackground(null);
        scenes = new AllScenes();
    }    
    
}
