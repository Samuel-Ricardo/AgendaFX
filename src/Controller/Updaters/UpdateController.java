/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Updaters;

import DAO.UserDAO;
import Main.Updaters.MainUpdate;
import Model.User;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 *
 * @author Samuel
 */
public class UpdateController implements Initializable {

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
    private ComboBox<String> cbSexo;

    @FXML
    private TextField txtNome;
    
    @FXML
    private DatePicker dpNascimento;

    private final UserDAO dao = new UserDAO();

    private final User user = UserDAO.getUser();

    private String userImage;

    @FXML
    public void atualizar() {       // Update user data // atualiza os dados do usuario

        User updatedUser = new User();  

        Date nasc = Date.from(dpNascimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        updatedUser.setId(user.getId());
        updatedUser.setNome(txtNome.getText());
        updatedUser.setCPF(txtCPF.getText());
        updatedUser.setEmail(txtEmail.getText());
        updatedUser.setNascimento(nasc);
        updatedUser.setSenha(txtSenha.getText());
        updatedUser.setTelefone(txtTelefone.getText());
        updatedUser.setSexo(cbSexo.getSelectionModel().getSelectedItem());
        updatedUser.setImage(userImage);

        if (dao.update(updatedUser) == true) {
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            try {
                Thread.sleep(500);
                loadScreen();
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            MainUpdate.getWindow().close();

        }

    }

    @FXML
    public void cancelar() {    // close screen // fecha a tela

        MainUpdate.getWindow().close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadScreen();   // Load Screen // carrega a tela

        imgPerfil.setOnMouseClicked((t) -> {

            FileChooser chooseImage = new FileChooser();

            File imageF = new File("");  

            imageF = chooseImage.showOpenDialog(new Stage());   // open Chooser Screen // Abre a tela de escolha

            if (imageF.exists()) {  // whether the existing or selected image shows an image // se a imagen gexistir ou for selecionado mostra a imagem

                imgPerfil.setImage(new Image("file:///" + imageF.getAbsolutePath()));

                userImage = imageF.getAbsolutePath();
            }

        });

    }

    private void loadScreen() {     // Load Screen // carrega a tela

        txtNome.setText(user.getNome());
        txtEmail.setText(user.getEmail());
        txtSenha.setText(user.getSenha());
        txtTelefone.setText(user.getTelefone());
        if (user.getSexo() != null) {
            cbSexo.getSelectionModel().select(user.getSexo());
        }
        txtCPF.setText(user.getCPF());

        System.out.println(user.getImage());
        
        if (user.getImage() != null) {
            imgPerfil.setImage(new Image("file:///" + user.getImage()));
        } else {
            imgPerfil.setImage(new Image(getClass().getResource("/View/Images/user_white_2.png") + ""));
        }

        LocalDate nasc = null;
        if (user.getNascimento() != null) {
            nasc = user.getNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        dpNascimento.setValue(nasc);

    }

}
