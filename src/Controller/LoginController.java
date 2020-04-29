/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import Main.MainHome;
import Main.MainLogin;
import Main.MainRegister;
import Model.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    
    private UserDAO dao = new UserDAO();

    @FXML
    void cadastrar() {
        
        MainRegister register = new MainRegister();
        
          try {
              
              register.start(new Stage());
              
          } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "Nao foi possivel abrir a janela: "+ex);
          }
    }

    @FXML
    void login() {
        
        User user = new User();              // creates a user with the data that was removed from the window  //  cria um usuario com os dados que foram retirados da janela
        user.setNome(txtLogin.getText());
        user.setSenha(txtSenha.getText());

        if(dao.exist(user)){                 // if the user exists he closes the window and opens the main screen  //  se o usuario existir ele fecha a janela e abre a tela principal
            
            MainHome home = new MainHome();  
            MainLogin.getWindow().close();   // Close the login window //  Fecha a janela de login
            
            try {
                home.start(new Stage());     // Open the Home window //  Abre a janela de Home
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Senha e ou nome icorretos ou inexistentes");
        }
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
