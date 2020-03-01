/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import GamePane.GamePaneController;
import Login.LoginController;
import OnlinePeaple.ListOfOnlinePeapleController;
import SignUp.SignUpController;
import SingleMode.SingleModeController;
import SingleMultiOnline.SingleMultiOnlineController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author General
 */
public class AllScenes {
    protected Parent root;
    protected Scene scene;
    protected Stage stage;
    
    public void getSceneMain(ActionEvent event) throws IOException, Exception{     
        root = FXMLLoader.load(getClass().getResource("/TecTacToc/Main.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("TecTacToe");
        stage.show();
    }
    public void getSceneAbout(ActionEvent event) throws IOException, Exception{
        root = FXMLLoader.load(getClass().getResource("/AboutUS/AboutUS.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("About US");
        stage.show();
    }
    public void getSceneSingleMultiOnline(ActionEvent event,String IPServer) throws IOException, Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SingleMultiOnline/SingleMultiOnline.fxml"));
        loader.load();
        SingleMultiOnlineController display = loader.getController();
        display.setIPServer(IPServer);
        root = loader.getRoot();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Single, Multi and Online Mode");
        stage.show();
    }
    public void getSceneOnlinePeaple(ActionEvent event,String id,String player,String IPServer) throws IOException, Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/OnlinePeaple/ListOfOnlinePeaple.fxml"));
        loader.load();
        ListOfOnlinePeapleController display = loader.getController();
        display.setIPServer(IPServer);
        display.getTablecontant(id,IPServer);
        display.setPlayer(player);
        root = loader.getRoot();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        display.setStage(stage);
        stage.setScene(scene);
        stage.setTitle("Online Peaple");
        stage.show();
    }
    public void getSceneRecords(ActionEvent event) throws IOException, Exception{
        root = FXMLLoader.load(getClass().getResource("/Records/Records.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Records");
        stage.show();
    }
    public void getSceneRegistration(ActionEvent event,String IPServer) throws IOException, Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SignUp/SignUp.fxml"));
        loader.load();
        SignUpController display = loader.getController();
        display.setIPServer(IPServer);
        root = loader.getRoot();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("SignUp");
        stage.show();
    }
    public void getSceneSingleMode(ActionEvent event) throws IOException, Exception{
        root = FXMLLoader.load(getClass().getResource("/SingleMode/SingleMode.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Single Mode");
        stage.show();
    }
    public void getSceneMultiMode(ActionEvent event) throws IOException, Exception{
        root = FXMLLoader.load(getClass().getResource("/NamesMulti/NamesMulti.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Multi Mode");
        stage.show();
    }
    public void getSceneOnlineMode(ActionEvent event,String IPServer) throws IOException, Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Login/Login.fxml"));
        loader.load();
        LoginController display = loader.getController();
        display.setIPServer(IPServer);
        root = loader.getRoot();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
    public void getSceneGamePane(ActionEvent event,String player1,String player2,String mode,String radioSelected) throws IOException, Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GamePane/GamePane.fxml"));
        loader.load();
        GamePaneController display = loader.getController();
        if(mode.equals("single")){
            display.changeMode("single",radioSelected);
            display.setPlayerName(player1,player2,radioSelected);
        }else if(mode.equals("multi")){
            display.changeMode("multi","x");
            display.setPlayerName(player1,player2,radioSelected); 
        }else if(mode.equals("online")){
            display.changeMode("online",radioSelected);
            display.setPlayerName(player1,player2,radioSelected);
        }else if(mode.equals("record")){ 
            display.replayGame(player1,mode);
        }
        root = loader.getRoot();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Game Pane");
        stage.show();
    }
}
