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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
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
    private ImageView imgPerfil;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNascimento;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblTelefone;

    @FXML
    private Label lblSexo;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblID;

    @FXML
    private PieChart pcActivityDone;

    @FXML
    private Pane paneEvento;

    @FXML
    private ImageView imgLembrete;

    @FXML
    private TabPane pTabNotficacoes;

    @FXML
    private Label lbTitulo;

    @FXML
    private Label lbData;

    @FXML
    private Label lbHora;

    @FXML
    private Label lbTipo;

    @FXML
    private Label lbEstado;

    @FXML
    private Label lbAvisado;

    @FXML
    private Pane paneCalendario;

    
    @FXML
    void close() {

    }

    @FXML
    void openCalendar() {
        
        paneCalendario.setVisible(true);
        
        if(paneEvento.isVisible() == true){
            paneEvento.setVisible(false);
        }
        if(paneHome.isVisible() == true){
            paneHome.setVisible(false);
        }
        if(panePerfil.isVisible() == true){
            panePerfil.setVisible(false);
        }
        
    }

    @FXML
    void openEvents() {

         paneEvento.setVisible(true);
        
        if(paneCalendario.isVisible() == true){
            paneCalendario.setVisible(false);
        }
        if(paneHome.isVisible() == true){
            paneHome.setVisible(false);
        }
        if(panePerfil.isVisible() == true){
            panePerfil.setVisible(false);
        }
        
    }

    @FXML
    void openHomePage() {

         paneHome.setVisible(true);
        
        if(paneEvento.isVisible() == true){
            paneEvento.setVisible(false);
        }
        if(paneCalendario.isVisible() == true){
            paneCalendario.setVisible(false);
        }
        if(panePerfil.isVisible() == true){
            panePerfil.setVisible(false);
        }
        
    }

    @FXML
    void openPerfil() {

         panePerfil.setVisible(true);
        
        if(paneEvento.isVisible() == true){
            paneEvento.setVisible(false);
        }
        if(paneHome.isVisible() == true){
            paneHome.setVisible(false);
        }
        if(paneCalendario.isVisible() == true){
            paneCalendario.setVisible(false);
        }
        
    }
    
    
    @FXML
    void registerNewUser() {

    }

    @FXML
    void updateUser() {

    }
    
    @FXML
    void changeUser() {

    }

        @FXML
    void create() {

    }

    @FXML
    void delet() {

    }

    @FXML
    void open() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
      
    }    
    
}
