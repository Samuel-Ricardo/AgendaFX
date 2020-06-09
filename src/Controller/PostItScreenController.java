/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostItDAO;
import Model.PostIt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class PostItScreenController implements Initializable {

   
    @FXML
    private TextFlow txfBody;

    @FXML
    private ImageView imgMenu;

    @FXML
    private VBox vbMenu;

    @FXML
    private Button btAttachment;

    @FXML
    private Button btSchedule;

    @FXML
    private Button btInfo;

    private PostItDAO dao = new PostItDAO();
    
    private PostIt postIt  = PostItDAO.getPostIt();
    
    @FXML
    void schedule() {

    }

    @FXML
    void showAttachment() {

    }

    @FXML
    void showData() {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        txfBody.setStyle("-fx-background-color: "+postIt.getType().getSecondaryColor()+" ;");
        
    }    
    
}
