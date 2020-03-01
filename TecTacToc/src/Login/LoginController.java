/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Scene.AllScenes;
import Socket.MySocket;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author General
 */
public class LoginController implements Initializable {

    @FXML
    private TextField loginNameTextField;
    @FXML
    private PasswordField loginPassTextfield;
    @FXML
    private Button loginBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Hyperlink signBtn;
    private AllScenes scenes;
    @FXML
    private Label chackPlayerName;
    @FXML
    private Label checkPassword;
    private String identifier;
    private MySocket socket;
    private boolean checkSocket;
    private String validateText;
    private String validatePass;
    public String IPServer;
//    private TextInputDialog td;
    
    public void setIPServer(String ip){
        IPServer = ip;
    }
    @FXML
    private void makeSignUp(ActionEvent event) throws Exception {
        scenes.getSceneRegistration(event,IPServer);
    }
    @FXML
    public void validationText(KeyEvent event){
        validateText = loginNameTextField.getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(validateText);
//        System.out.println(matcher.matches());
        if(!matcher.matches()){
            chackPlayerName.setText("the name should be a-z | A-Z | 0-9");
            loginBtn.setDisable(true);
        }else{
            chackPlayerName.setText("");
            loginBtn.setDisable(false);
        }
    }
    @FXML
    public void validationPass(KeyEvent event){
        validatePass = loginPassTextfield.getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9%@#$&_]+$");
        Matcher matcher = pattern.matcher(validatePass);
//        System.out.println(matcher.matches());
        if(!matcher.matches()){
            checkPassword.setText("The password shouldn't be containe ,+-*/=-)(|^!~?><");
            loginBtn.setDisable(true);
        }else{
            checkPassword.setText("");
            loginBtn.setDisable(false);
        }
    }
    @FXML
    private void goToOnlinePeaple(ActionEvent event) throws Exception {
        socket = new MySocket(IPServer);
        checkSocket = socket.getCheckSocket();
        String loginName = loginNameTextField.getText();
        String loginPass = loginPassTextfield.getText();
        if(checkSocket == true){
            socket.printStream(loginName + "," + "login" + "," + loginPass);
    //        ps.println(loginName + "," + "login" + "," + loginPass);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String value;
                    String str[];
                    value = socket.dataInputStream();
                    if(value.compareTo("false") == 0){
                        chackPlayerName.setText("There is Something wrong with name");
                        checkPassword.setText("There is Something wrong with password");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("There is something wrong with the name or password");
                        
                        alert.showAndWait();
                    }else{
                        str = value.split(",");
                        chackPlayerName.setTextFill(Color.rgb(62, 238, 62));
                        checkPassword.setTextFill(Color.rgb(62, 238, 62));
                        chackPlayerName.setText("The name is correct");
                        checkPassword.setText("The password is correct");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("congratulations");
                        alert.setHeaderText(null);
                        alert.setContentText("You Login successfully ");
                        
                        alert.showAndWait();
                        System.out.println(value);
                        try {
                            scenes.getSceneOnlinePeaple(event,str[1],loginName,IPServer);
                        } catch (Exception ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });
        }
                    

    }
    @FXML
    private void goToSingleMultiOnline(ActionEvent event) throws Exception {
        scenes.getSceneSingleMultiOnline(event,IPServer);
    }

    @FXML
    private void loginBtnMouseEnter(MouseEvent event) {
        loginBtn.setCursor(Cursor.HAND);
        loginBtn.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        loginBtn.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void loginBtnMouseExit(MouseEvent event) {
        loginBtn.setCursor(Cursor.HAND);
        loginBtn.setBackground(null);
        loginBtn.setTextFill(Color.rgb(255, 255, 153));
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1), loginBtn);
        fadeIn1.setFromValue(0);
        fadeIn1.setToValue(1);
        fadeIn1.setDelay(Duration.seconds(1));
        fadeIn1.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), exitBtn);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);
        fadeIn2.setDelay(Duration.seconds(1));
        fadeIn2.play();
        scenes = new AllScenes();
        loginBtn.setBackground(null);
        exitBtn.setBackground(null);
    }    
    
}
