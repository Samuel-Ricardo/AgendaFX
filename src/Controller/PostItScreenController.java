/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class PostItScreenController implements Initializable {

   @FXML
    private HTMLEditor j;
   
   @FXML
    private WebView view;
   
   @FXML
    void test() {

        System.out.println(j.getHtmlText());
        
        view.getEngine().loadContent(j.getHtmlText());
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
