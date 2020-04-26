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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private AnchorPane apFundo;

    @FXML
    private Rectangle fundo;

    @FXML
    private AnchorPane apFundoLogin;

    @FXML
    private Rectangle recFundoL;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private TextField txtLogin;

    @FXML
    private Label txtEsqueceu;

    @FXML
    private PasswordField txtSenha;

    @FXML
    void cadastrar() {

    }

    @FXML
    void login() {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
