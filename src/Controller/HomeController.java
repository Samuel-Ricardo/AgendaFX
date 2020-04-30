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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
 
   
    @FXML
    private VBox vbox;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Button btnHomePage;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnEventos;

    @FXML
    private Button btnCalendario;

    @FXML
    private Pane paneHome;

    @FXML
    private Pane panePerfil;

    @FXML
    private Pane paneEvento;

    @FXML
    private Pane paneCalendario;

    @FXML
    void close() {

    }

    @FXML
    void openCalendar() {

    }

    @FXML
    void openEvents() {

    }

    @FXML
    void openHomePage() {

    }

    @FXML
    void openPerfil() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
    
}
