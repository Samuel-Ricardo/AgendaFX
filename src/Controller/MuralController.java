/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostItDAO;
import DAO.UserDAO;
import Model.PostIt;
import Model.PostItPreView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class MuralController implements Initializable {
    
    @FXML
    private TilePane tlMural;

    private PostItDAO dao = new PostItDAO();


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<PostIt> postIts = (ArrayList<PostIt>) dao.selectAllFromUser(UserDAO.getUser().getId().intValue());
        
        for (PostIt postIt : postIts) {
            
            PostItPreView preView = new PostItPreView(postIt);
            
            tlMural.getChildren().add(preView);
            
        }
        
    }    
    
}
