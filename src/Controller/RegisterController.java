/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import Main.MainLogin;
import Main.MainRegister;
import Model.User;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class RegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ImageView imgPerfil;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtTelefone;

    @FXML
    private DatePicker dpNascimento;

    @FXML
    private ComboBox<String> cbSexo;

    @FXML
    private TextField txtNome;
    
    private SimpleDateFormat defaultFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    private UserDAO dao = new UserDAO();

    private String image;
    
    @FXML
    void cadastrar() {
        
        LocalDate d =  dpNascimento.getValue();
        
       String day = ""+d.getDayOfMonth();
       String month = ""+d.getMonthValue();
       System.out.println(month);
       String year = ""+d.getYear();
       String date = day+"/"+month+"/"+year; 
       
         Date dateOfBirth = null;
       
        try {
            
           dateOfBirth = defaultFormat.parse(date);
            
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel gravar a data: "+ex);
        }
        
        
        
        
        User user = new User();
        
        
        if(image == null){
            user.setImage(""+getClass().getResource("/View/Images/user_white_2.png"));
        }else{
        user.setImage(image);
        }
        
        user.setNome(txtNome.getText());
        user.setSenha(txtSenha.getText());
        user.setEmail(txtEmail.getText());
        user.setCPF(txtCPF.getText());
        user.setNascimento(dateOfBirth);
        user.setSexo(cbSexo.getSelectionModel().getSelectedItem());
        user.setTelefone(txtTelefone.getText());
        
        if(dao.insert(user)){
            JOptionPane.showMessageDialog(null, "Salvo com sucesso -,+");
        }
        

    }
    
    
    @FXML
    void cancelar() {

          MainRegister.getWindow().close();
          MainLogin.getWindow().show();
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       start();
       
       imgPerfil.setOnMouseClicked((t) -> {
           FileChooser chooseImage = new FileChooser();
           
           File imageF = new File("");
           
           imageF = chooseImage.showOpenDialog(new Stage());
           
           if (imageF.exists()){
               
               imgPerfil.setImage(new Image("file:///"+imageF.getAbsolutePath()));
               
               image = imageF.getAbsolutePath();
           }
           
           
       });
    }    

    private void start() {
        
        String male = "Masculino";
        String female = "Feminino";
        
        ArrayList<String> arSexos = new ArrayList<>();
        
        arSexos.add(male);
        arSexos.add(female);
        
        ObservableList<String> obsSexos = FXCollections.observableArrayList(arSexos);
        
        cbSexo.setItems(obsSexos);

    }
    
}
