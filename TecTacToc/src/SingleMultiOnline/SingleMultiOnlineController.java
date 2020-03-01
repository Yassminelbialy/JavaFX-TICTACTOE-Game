/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SingleMultiOnline;

import Scene.AllScenes;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author General
 */
public class SingleMultiOnlineController implements Initializable {

    @FXML
    private Button single;
    @FXML
    private Button multi;
    @FXML
    private Button online;
    
    private AllScenes scenes;
    @FXML
    private Button backToMain;
    @FXML
    private Button records;
    private String IPServer;
    
//    public SingleMultiOnlineController() {
//        enterIpDialog();           
//
//    }

    public void enterIpDialog() {
        TextInputDialog td = new TextInputDialog(IPServer);
        td.setContentText("enter Server IP");
        Optional<String> res = td.showAndWait();
        if (res.isPresent() && res.get() != " ") {
            System.out.println("valid");
            if (validateIPAddress(res.get())) {
                IPServer = res.get();
                System.out.println(IPServer);

            } else {

                Alert tryAgain;
                tryAgain = new Alert(Alert.AlertType.ERROR, "enter a valid IP",
                        ButtonType.OK);
                tryAgain.showAndWait().ifPresent((ButtonType type) -> {
                    if (type == ButtonType.OK) {
                        enterIpDialog();
                    }
                });
            }
        } else {
            Alert tryAgain;
            tryAgain = new Alert(Alert.AlertType.ERROR, "enter an IP",
                    ButtonType.OK);
            tryAgain.showAndWait().ifPresent((ButtonType type) -> {
                if (type == ButtonType.OK) {
                    enterIpDialog();

                }
            });

        }

    }
    public static boolean validateIPAddress(String ip) {
        String rex = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
        Pattern p = Pattern.compile(rex);
        String[] st = ip.split("\\.");
        if (!p.matcher(ip).matches()) {
            return false;
        } else {
            for (String segment : st) {
                if (Integer.parseInt(segment) > 255 || Integer.parseInt(segment) < 0
                        || (segment.length() > 1 && segment.startsWith("0"))) {
                    return false;
                }
            }
            return true;
        }
    }
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Media media;
    @FXML
    private void goToSingleMode(ActionEvent event) throws Exception {
        scenes.getSceneSingleMode(event);
    }
    @FXML
    private void goToMultiMode(ActionEvent event) throws Exception {
        scenes.getSceneMultiMode(event);
    }
    @FXML
    private void goToOnlineMode(ActionEvent event) throws Exception {
//        if(!IPServer.equals(null)){
            enterIpDialog();            
//        }
        scenes.getSceneOnlineMode(event,IPServer);
    }
    @FXML
    private void goToRecords(ActionEvent event) throws Exception {
        scenes.getSceneRecords(event);
    }
    @FXML
    private void backToMain(ActionEvent event) throws Exception {
        scenes.getSceneMain(event);
    }
    public void setIPServer(String ip){
        IPServer = ip;
    }
    @FXML
    private void singleMouseEnter(MouseEvent event) {
        single.setCursor(Cursor.HAND);
        single.setTextFill(Color.rgb(49, 14, 251));
    }
    @FXML
    private void singleMouseExit(MouseEvent event) {
        single.setCursor(Cursor.HAND);
        single.setBackground(null);
        single.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void multiMouseEnter(MouseEvent event) {
        multi.setCursor(Cursor.HAND);
        multi.setTextFill(Color.rgb(49, 14, 251));
    }
    @FXML
    private void multiMouseExit(MouseEvent event) {
        multi.setCursor(Cursor.HAND);
        multi.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void onlineMouseEnter(MouseEvent event) {
        online.setCursor(Cursor.HAND);
        online.setTextFill(Color.rgb(49, 14, 251));
    }
    @FXML
    private void onlineMouseExit(MouseEvent event) {
        online.setCursor(Cursor.HAND);
        online.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void recordsMouseEnter(MouseEvent event) {
        records.setCursor(Cursor.HAND);
      //  records.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        records.setTextFill(Color.rgb(49, 14, 251));
    }
    @FXML
    private void recordsMouseExit(MouseEvent event) {
        records.setCursor(Cursor.HAND);
      
        records.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void backToMainMouseEnter(MouseEvent event) {
        backToMain.setCursor(Cursor.HAND);
        backToMain.setTextFill(Color.rgb(49, 14, 251));
    }
    @FXML
    private void backToMainMouseExit(MouseEvent event) {
        backToMain.setCursor(Cursor.HAND);
       
        backToMain.setTextFill(Color.rgb(11, 48, 76));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(2), single);
        fadeIn1.setFromValue(0);
        fadeIn1.setToValue(1);
        fadeIn1.setDelay(Duration.seconds(0.2));
        fadeIn1.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(2), multi);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);
        fadeIn2.setDelay(Duration.seconds(0.4));
        fadeIn2.play();
        FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(2), online);
        fadeIn3.setFromValue(0);
        fadeIn3.setToValue(1);
        fadeIn3.setDelay(Duration.seconds(0.6));
        fadeIn3.play();
        FadeTransition fadeIn4 = new FadeTransition(Duration.seconds(2), records);
        fadeIn4.setFromValue(0);
        fadeIn4.setToValue(1);
        fadeIn4.setDelay(Duration.seconds(0.8));
        fadeIn4.play();
        FadeTransition fadeIn5 = new FadeTransition(Duration.seconds(2), backToMain);
        fadeIn5.setFromValue(0);
        fadeIn5.setToValue(1);
        fadeIn5.setDelay(Duration.seconds(1));
        fadeIn5.play();
        single.setBackground(null);
        multi.setBackground(null);
        online.setBackground(null);
        records.setBackground(null);
        backToMain.setBackground(null);
        scenes = new AllScenes();
        

    }    
    
}
