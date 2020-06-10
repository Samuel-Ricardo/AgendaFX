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
    
    public static ImageView staticImgMenu;
    
    public static VBox staticVbMenu;

    public static Button staticBtAttachment;
    
    public static Button staticBtSchedule;
    
    public static Button staticBtInfo;
    
    public static TextFlow staticTxfBody;

    private PostItDAO dao = new PostItDAO();
    
    private PostIt postIt  = PostItDAO.getPostIt();
    
    private boolean menuVissible = false;
    
    @FXML
    void schedule() {

    }

    @FXML
    void showAttachment() {

    }

    @FXML
    void showData() {

        menuVissible = !menuVissible;
        
        vbMenu.setVisible(menuVissible);
        
    }
    
    public static void loadPostIt(PostIt postIt){
        
        staticTxfBody.setStyle("-fx-background-color: "+postIt.getType().getSecondaryColor()+" ;");
        
        Text text = new Text(postIt.getBody());
        
        staticTxfBody.getChildren().add(text);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        staticBtAttachment = btAttachment;
        staticBtInfo = btInfo;
        staticBtSchedule = btSchedule;
        staticImgMenu = imgMenu;
        staticTxfBody = txfBody;
        staticVbMenu = vbMenu;
        
    }    
    
}
