/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlinePeaple;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author General
 */
public class OnlinePeaple {
    private SimpleIntegerProperty id;
    private SimpleStringProperty playerName;
    private SimpleStringProperty password;
    private SimpleIntegerProperty gender;
    private SimpleIntegerProperty active;
    
    public OnlinePeaple(Integer id,String playerName,String password,Integer gender,Integer active){
        this.id = new SimpleIntegerProperty(id);
        this.playerName = new SimpleStringProperty(playerName);
        this.password = new SimpleStringProperty(password);
        this.gender = new SimpleIntegerProperty(gender);
        this.active = new SimpleIntegerProperty(active);
    }
    public OnlinePeaple(Integer id,String playerName){
        this.id = new SimpleIntegerProperty(id);
        this.playerName = new SimpleStringProperty(playerName);

    }
    public Integer getId(){
        return id.get();
    }
    public void setId(Integer id){
        this.id.set(id);
    }
    public IntegerProperty idProperty(){
        return id;
    }
    
    public String getPlayerName(){
        return playerName.get();
    }
    public void setPlayerName(String playerName){
        this.playerName.set(playerName);
    }
    public StringProperty playerNameProperty(){
        return playerName;
    }
    
    public String getPassword(){
        return password.get();
    }
    public void setPassword(String password){
        this.password.set(password);
    }
    public StringProperty passwordProperty(){
        return password;
    }
    
    public Integer getGender(){
        return gender.get();
    }
    public void setGender(Integer gender){
        this.gender.set(gender);
    }
    public IntegerProperty genderProperty(){
        return gender;
    }
    
    public Integer getActive(){
        return active.get();
    }
    public void setActive(Integer active){
        this.active.set(active);
    }
    public IntegerProperty activeProperty(){
        return active;
    }
}
