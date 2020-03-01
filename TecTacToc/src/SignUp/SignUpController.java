/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SignUp;

import Login.LoginController;
import Scene.AllScenes;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import Socket.MySocket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class SignUpController implements Initializable {
    private AllScenes scenes;
    @FXML
    private TextField signNameTextField;
    @FXML
    private TextField signPassTextField;
    @FXML
    private TextField confirmPass;
    @FXML
    private Button submitBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private RadioButton radioMale;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private RadioButton radioFemale;
    @FXML
    private Label checkName;
    @FXML
    private Label checkPass;
    @FXML
    private Label checkConfirmPass;
    @FXML
    private Label checkGender;
    @FXML
    private Label lableName;
    @FXML
    private Label lablePassword;
    @FXML
    private Label lableConfirmPass;
    @FXML
    private Label lableGender;

    private MySocket socket;
    public boolean checkSocket;
    private String validateText;
    private String validatePass;
    private Pattern patternText = null;
    private Matcher matcherText = null;
    private Pattern patternPass = null;
    private Matcher matcherPass = null;
    private boolean checkNamePlayer;
    private String IPServer;
    /**
     * Initializes the controller class.
     */
    
 
    @FXML
    private void goToLogin(ActionEvent event) throws Exception {
//        socket.setIP("127.0.0.1");
        socket = new MySocket(IPServer);
        checkSocket = socket.getCheckSocket();
        String str = signNameTextField.getText();
        String str1 = signPassTextField.getText();
        String str2 = confirmPass.getText(); 
        
        Integer lengthStr = str.length();
        Integer lengthStr1 = str1.length();
        

        if(checkSocket == true){
            if((lengthStr >= 3 && lengthStr <= 8 && checkNamePlayer == true) && matcherPass.matches() && matcherText.matches() && lengthStr1 >= 5 && str1.equals(str2) && (radioMale.isSelected() || radioFemale.isSelected())){
                String radioSelected = Gender.getSelectedToggle().getUserData().toString();
                socket.printStream(str + "," + "signup" + "," + str1 + "," + radioSelected);
    //            ps.println(str + "," + "signup" + "," + str1 + "," + radioSelected);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String value;
                        String str[];
                        try {
                            value = socket.dataInputStream();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("congratulations");
                            alert.setHeaderText(null);
                            alert.setContentText("The player is SingUp successfully.\n Login To start to play");
                            alert.showAndWait();
                            scenes.getSceneOnlineMode(event,IPServer);
                        } catch (IOException ex) {
                            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }
        }
    }
    @FXML
    private void goToBackToLogin(ActionEvent event) throws Exception {
        scenes.getSceneOnlineMode(event,IPServer);
    }
    @FXML
    private void checkPlayerName(KeyEvent event) throws Exception {
        String str = signNameTextField.getText();
        String str1 = signPassTextField.getText();
        String str2 = confirmPass.getText();
        validateText = signNameTextField.getText();
        patternText = Pattern.compile("^[a-zA-Z0-9]+$");
        matcherText = patternText.matcher(validateText);
        Integer lengthStr = str.length();
        Integer lengthStr1 = str1.length();
        if(!matcherText.matches()){
            checkName.setTextFill(Color.RED);
            checkName.setText("Player name shouldn't be containe a-z | A-Z | 0-9 ✘");
        }else if(lengthStr >= 9){
            checkName.setTextFill(Color.RED);
            checkName.setText("Player name shouldn't be more than 8 characters ✘");
        }else if(lengthStr < 3 && lengthStr >= 1){
            checkName.setTextFill(Color.RED);
            checkName.setText("Player name shouldn't be less than 3 characters ✘");
        }else if(lengthStr == 0){
            checkName.setTextFill(Color.RED);
            checkName.setText("You should enter player name ✘");
        }else{

            socket = new MySocket(IPServer);
            socket.printStream(str + "," + "checkText");
            checkSocket = socket.getCheckSocket();
            if(checkSocket == true){
                System.out.println("true checkText");
                String value;
                String strs[];
                value = socket.dataInputStream();
                strs = value.split(",");
                if(strs[0].equals("true")){
                    checkName.setTextFill(Color.rgb(62,238,62));
                    checkName.setText("The player name is good ✔");
                    checkNamePlayer = true;
                }else{
                    checkName.setTextFill(Color.RED);
                    checkName.setText("The player name is used ✘");
                    checkNamePlayer = false;
                }
            }
        }
        if((lengthStr >= 3 && lengthStr <= 8 && checkNamePlayer == true) && lengthStr1 >= 5 && str1.equals(str2) && (radioMale.isSelected() || radioFemale.isSelected())){
            submitBtn.setDisable(false);
        }else{
            submitBtn.setDisable(true);
        }
    }
    @FXML
    private void checkPassword(KeyEvent event) throws Exception {
        String str = signNameTextField.getText();
        String str1 = signPassTextField.getText();
        String str2 = confirmPass.getText();
        validatePass = signPassTextField.getText();
        patternPass = Pattern.compile("^[a-zA-Z0-9%@#$&_]+$");
        matcherPass = patternPass.matcher(validatePass);
        Integer lengthStr = str.length();
        Integer lengthStr1 = str1.length();
        if(!matcherPass.matches()){
            checkPass.setTextFill(Color.RED);
            checkPass.setText("The password shouldn't be containe ,+-*/=-)(|^!~?>< ✘");
        }else if(lengthStr1 == 0){
            checkPass.setTextFill(Color.RED);
            checkPass.setText("You should enter password ✘");
        }else if(lengthStr1 < 3 && lengthStr1 >= 1){
            checkPass.setTextFill(Color.RED);
            checkPass.setText("The password is very weak ✘");
        }else if(lengthStr1 < 5){
            checkPass.setTextFill(Color.ORANGE);
            checkPass.setText("The password is weak ✘");
        }else if(lengthStr1 < 7){
            checkPass.setTextFill(Color.GOLD);
            checkPass.setText("The password is good ✔");
        }else{
            checkPass.setTextFill(Color.rgb(62,238,62));
            checkPass.setText("The password is very good ✔");
        }
        if (str1.equals(str2) && !str1.equals("") && !str2.equals("")) {
            checkConfirmPass.setTextFill(Color.rgb(62,238,62));
            checkConfirmPass.setText("The password is confirmed ✔");
        }else if(confirmPass.equals(null) || !str1.equals(str2) || signPassTextField.equals(null)){
            checkConfirmPass.setTextFill(Color.RED);
            checkConfirmPass.setText("The password is not confirmed ✘");
        } 
        if((lengthStr >= 3 && lengthStr <= 8 && checkNamePlayer == true) && lengthStr1 >= 5 && str1.equals(str2) && (radioMale.isSelected() || radioFemale.isSelected())){
            submitBtn.setDisable(false);
        }else{
            submitBtn.setDisable(true);
        }
    }
    @FXML
    private void checkConfirmPassword(KeyEvent event) throws Exception {
        String str = signNameTextField.getText();
        String str1 = signPassTextField.getText();
        String str2 = confirmPass.getText();
        Integer lengthStr = str.length();
        Integer lengthStr1 = str1.length();
        if (str1.equals(str2) && !str1.equals("") && !str2.equals("")) {
            checkConfirmPass.setTextFill(Color.rgb(62,238,62));
            checkConfirmPass.setText("The password is confirmed ✔");
        }else if(confirmPass.equals(null) || !str1.equals(str2) || signPassTextField.equals(null)){
            checkConfirmPass.setTextFill(Color.RED);
            checkConfirmPass.setText("The password is not confirmed ✘");
        } 
        if((lengthStr >= 3 && lengthStr <= 8 && checkNamePlayer == true) && lengthStr1 >= 5 && str1.equals(str2) && (radioMale.isSelected() || radioFemale.isSelected())){
            submitBtn.setDisable(false);
        }else{
            submitBtn.setDisable(true);
        }
    }
    @FXML
    private void checkRadioGender(MouseEvent event) throws Exception {
        String str = signNameTextField.getText();
        String str1 = signPassTextField.getText();
        String str2 = confirmPass.getText();
        Integer lengthStr = str.length();
        Integer lengthStr1 = str1.length();
        if(!radioMale.isSelected() && !radioFemale.isSelected()){
            checkGender.setText("You should select one of them ✘");
        }else{
            checkGender.setTextFill(Color.rgb(62,238,62));
            checkGender.setText("The gender is selected ✔");
        }
        if((lengthStr >= 3 && lengthStr <= 8 && checkNamePlayer == true) && lengthStr1 >= 5 && str1.equals(str2) && (radioMale.isSelected() || radioFemale.isSelected())){
            submitBtn.setDisable(false);
        }else{
            submitBtn.setDisable(true);
        }
    }
    public void setIPServer(String ip){
        IPServer = ip;
    }
    @FXML
    private void submitBtnMouseEnter(MouseEvent event) {
        submitBtn.setCursor(Cursor.HAND);
        submitBtn.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        submitBtn.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void submitBtnMouseExit(MouseEvent event) {
        submitBtn.setCursor(Cursor.HAND);
        submitBtn.setBackground(null);
        submitBtn.setTextFill(Color.rgb(255, 255, 153));
    }
    @FXML
    private void exitBtnMouseEnter(MouseEvent event) {
        exitBtn.setCursor(Cursor.HAND);
        exitBtn.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        exitBtn.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void exitBtnMouseExit(MouseEvent event) {
        exitBtn.setCursor(Cursor.HAND);
        exitBtn.setBackground(null);
        exitBtn.setTextFill(Color.rgb(255, 255, 153));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1), lableName);
        fadeIn1.setFromValue(0);
        fadeIn1.setToValue(1);
        fadeIn1.setDelay(Duration.seconds(.5));
        fadeIn1.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), signNameTextField);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);
        fadeIn2.setDelay(Duration.seconds(.5));
        fadeIn2.play();
        FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(1), lablePassword);
        fadeIn3.setFromValue(0);
        fadeIn3.setToValue(1);
        fadeIn3.setDelay(Duration.seconds(1));
        fadeIn3.play();
        FadeTransition fadeIn4 = new FadeTransition(Duration.seconds(1), signPassTextField);
        fadeIn4.setFromValue(0);
        fadeIn4.setToValue(1);
        fadeIn4.setDelay(Duration.seconds(1));
        fadeIn4.play();
        FadeTransition fadeIn5 = new FadeTransition(Duration.seconds(1), lableConfirmPass);
        fadeIn5.setFromValue(0);
        fadeIn5.setToValue(1);
        fadeIn5.setDelay(Duration.seconds(1.5));
        fadeIn5.play();
        FadeTransition fadeIn6 = new FadeTransition(Duration.seconds(1), confirmPass);
        fadeIn6.setFromValue(0);
        fadeIn6.setToValue(1);
        fadeIn6.setDelay(Duration.seconds(1.5));
        fadeIn6.play();
        FadeTransition fadeIn7 = new FadeTransition(Duration.seconds(1), lableGender);
        fadeIn7.setFromValue(0);
        fadeIn7.setToValue(1);
        fadeIn7.setDelay(Duration.seconds(2));
        fadeIn7.play();
        FadeTransition fadeIn8 = new FadeTransition(Duration.seconds(1), radioFemale);
        fadeIn8.setFromValue(0);
        fadeIn8.setToValue(1);
        fadeIn8.setDelay(Duration.seconds(2));
        fadeIn8.play();
        FadeTransition fadeIn9 = new FadeTransition(Duration.seconds(1), radioMale);
        fadeIn9.setFromValue(0);
        fadeIn9.setToValue(1);
        fadeIn9.setDelay(Duration.seconds(2));
        fadeIn9.play();
        FadeTransition fadeIn10 = new FadeTransition(Duration.seconds(1), submitBtn);
        fadeIn10.setFromValue(0);
        fadeIn10.setToValue(1);
        fadeIn10.setDelay(Duration.seconds(2.5));
        fadeIn10.play();
        FadeTransition fadeIn11 = new FadeTransition(Duration.seconds(1), exitBtn);
        fadeIn11.setFromValue(0);
        fadeIn11.setToValue(1);
        fadeIn11.setDelay(Duration.seconds(2.5));
        fadeIn11.play();
        submitBtn.setBackground(null);
        exitBtn.setBackground(null);
        scenes = new AllScenes();
        radioFemale.setUserData("0");
        radioMale.setUserData("1");
    }    
    
}
