/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Records;

import Scene.AllScenes;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class RecordsController implements Initializable {
    private AllScenes scenes;
    @FXML
    private Button exitGame;
    @FXML
    private Button replayRecord;
    @FXML
    private TableView<Records> tableRecords;
    @FXML
    private TableColumn<Records, Integer> recordNumber;
    @FXML
    private TableColumn<Records, String> listRecords;
    ObservableList<Records> list;
    private List lists;
    int count = 1;
    private String IPServer;
    
    @FXML
    private void backToSingleMultiOnline(ActionEvent event) throws Exception {
        scenes.getSceneSingleMultiOnline(event,IPServer);
    }
    public void setIPServer(String ip){
        IPServer = ip;
    }
    public void listFiles(){
        File directory = new File("C:\\Records");
        //get all the files from a directory
        File[] fList = directory.listFiles();
        if(fList != null){
            lists=new ArrayList();
            for (int i = 0; i < fList.length; i++) { 
               lists.add(new Records(count, String.valueOf(fList[i])));
                   count++;
            }
            list = FXCollections.observableArrayList(lists);
            recordNumber.setCellValueFactory(new PropertyValueFactory<Records,Integer>("id"));
            listRecords.setCellValueFactory(new PropertyValueFactory<Records,String>("fileName"));
            tableRecords.setItems(list);
        }
    }
        
    @FXML
    private void Replay(ActionEvent event) throws IOException, Exception {
        Records pos = tableRecords.getSelectionModel().getSelectedItem();
        if(pos != null){        
            String path = pos.getFileName();
            scenes.getSceneGamePane(event, path, "", "record","");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You should select one record to replay");

            alert.showAndWait();
        }
         
        
    }
    @FXML
    private void replayRecordMouseEnter(MouseEvent event) {
        replayRecord.setCursor(Cursor.HAND);
        replayRecord.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153), CornerRadii.EMPTY, Insets.EMPTY)));
        replayRecord.setTextFill(Color.rgb(11, 48, 76));
    }
    @FXML
    private void replayRecordMouseExit(MouseEvent event) {
        replayRecord.setCursor(Cursor.HAND);
        replayRecord.setBackground(null);
        replayRecord.setTextFill(Color.rgb(255, 255, 153));
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scenes = new AllScenes();
        listFiles();
        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1), replayRecord);
        fadeIn1.setFromValue(0);
        fadeIn1.setToValue(1);
        fadeIn1.setDelay(Duration.seconds(0.4));
        fadeIn1.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), exitGame);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);
        fadeIn2.setDelay(Duration.seconds(0.8));
        fadeIn2.play();
        replayRecord.setBackground(null);
        exitGame.setBackground(null);
    }    
    
}
