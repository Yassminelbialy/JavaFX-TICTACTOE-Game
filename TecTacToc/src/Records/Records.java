/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Records;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Yasmeen
 */
public class Records {
    private SimpleIntegerProperty id;
    private SimpleStringProperty fileName;

    public Records(Integer _id, String _fileName) {
        id=new SimpleIntegerProperty(_id);
        fileName=new SimpleStringProperty(_fileName);
        
    }
    
    public Integer getId()
    {
        return id.get();
    }
    public void setId(Integer _id)
    {
        id.set(_id);
    }
    public String getFileName()
    {
        return fileName.get();
    }
    public void setId(String _fileName)
    {
        fileName.set(_fileName);
    }
}
