/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import Model.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class RecoverPasswordController implements Initializable {

        @FXML
    private TextField txtName;

    @FXML
    private TextField txtMail;

    @FXML
    private Button btRecovery;
    
    private User recoveredUser = new User();
    
    private UserDAO dao = new UserDAO();

    @FXML
    void Recovery() {
        
        recoveredUser.setEmail(txtMail.getText());
        recoveredUser.setNome(txtName.getText());
        
        ArrayList<User> users = (ArrayList<User>) dao.searchByName(recoveredUser.getNome());
        
        User checkedUser = checkUser(users, recoveredUser);
        
        if(checkedUser != recoveredUser){
            
            JOptionPane.showMessageDialog(null, "Sua senha é: "+checkedUser.getSenha());
            
        }else{
            
            JOptionPane.showMessageDialog(null, "Nome e ou email icorretos ou inexistentes");
        }

    }

    public User checkUser(ArrayList<User> users, User recoveredUser) {
        
       // boolean exist = false;
       
       int cont = 0;
       
        for (User user : users) {
            System.out.println(recoveredUser.getEmail());
            if(recoveredUser.getEmail().equals(users.get(cont).getEmail())){
                System.out.println("igual");
                recoveredUser = users.get(cont);
              //  exist = true;
            }else{
              //  exist = false;
            }
            cont ++;
        }
        return recoveredUser;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
