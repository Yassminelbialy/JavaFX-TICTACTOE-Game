/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AboutUS;

import Scene.AllScenes;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author General
 */
public class AboutUSController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private AllScenes scenes;
    @FXML
    private Button backToMain;
    @FXML
    private void backToMain(ActionEvent event) throws Exception {
        scenes.getSceneMain(event);
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
        backToMain.setTextFill(Color.rgb(11, 48, 76));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scenes = new AllScenes();
        backToMain.setBackground(null);
    }    
    
}
