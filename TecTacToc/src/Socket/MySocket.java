/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import SignUp.SignUpController;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;

/**
 *
 * @author General
 */
public class MySocket {
    private java.net.Socket mySocket;
    private DataInputStream dis;
    private PrintStream ps = null;
    protected  Pane alertServer;
    protected  Label textErrorServer;
    protected  Button buttonServer;
    protected  DropShadow dropShadow;
    private boolean checkSocket;
//    private String ip;
    
    public MySocket(String ip) throws IOException{
        
        try {
            checkSocket = true;  
            mySocket = new java.net.Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());

        } catch (IOException ex) {            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("server not found");
            alert.showAndWait();
            checkSocket = false;
           
        }
        
    }

//    public String getIP() {
//        return ip;
//    }
//    public void setIP(String ip) {
//        this.ip = ip;
//    }
    
    public Socket getMySocket() {
        return mySocket;
    }
    
    public boolean getCheckSocket(){
        return checkSocket;
    }
    public void printStream(String input){
        ps.println(input);
    }
    public String dataInputStream(){
        try {
            return dis.readLine();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("server not found");
            alert.showAndWait();
        }
        return null;
    } 
    public void closeSocket() throws IOException{
        ps.close();
        dis.close();
        mySocket.close();
    }
}
