/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.NotificationDAO;
import DAO.UserDAO;
import Main.MainChooser;
import Main.MainLogin;
import Main.MainRegister;
import Main.MainUpdate;
import Model.Notification;
import Model.PostIt;
import Model.RowNotification;
import Model.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private VBox vboxAtividadesHoje;

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
    private ImageView imgPerfilZoom;

    private final UserDAO userDao = new UserDAO();
    
    private final NotificationDAO notDAO = new NotificationDAO();

    private User logUser;
    
    private ArrayList<Notification> notifications;
    
    private ArrayList<PostIt> postIt;
    
    private static int index;


    @FXML
    void close() {

    }

    @FXML
    void openCalendar() {

        paneCalendario.setVisible(true);

        if (paneEvento.isVisible() == true) {
            paneEvento.setVisible(false);
        }
        if (paneHome.isVisible() == true) {
            paneHome.setVisible(false);
        }
        if (panePerfil.isVisible() == true) {
            panePerfil.setVisible(false);
        }

    }

    @FXML
    void openEvents() {

        paneEvento.setVisible(true);

        if (paneCalendario.isVisible() == true) {
            paneCalendario.setVisible(false);
        }
        if (paneHome.isVisible() == true) {
            paneHome.setVisible(false);
        }
        if (panePerfil.isVisible() == true) {
            panePerfil.setVisible(false);
        }

     
        
    }

    @FXML
    void openHomePage() {

        paneHome.setVisible(true);

        if (paneEvento.isVisible() == true) {
            paneEvento.setVisible(false);
        }
        if (paneCalendario.isVisible() == true) {
            paneCalendario.setVisible(false);
        }
        if (panePerfil.isVisible() == true) {
            panePerfil.setVisible(false);
        }

    }

    @FXML
    void openPerfil() {

        loadPerfil();

        panePerfil.setVisible(true);

        if (paneEvento.isVisible() == true) {
            paneEvento.setVisible(false);
        }
        if (paneHome.isVisible() == true) {
            paneHome.setVisible(false);
        }
        if (paneCalendario.isVisible() == true) {
            paneCalendario.setVisible(false);
        }

        loadPerfil();

    }

    @FXML
    void registerNewUser() {

        MainRegister register = new MainRegister();

        try {
            register.start(new Stage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel abrir a janela: " + ex);
        }

    }

    @FXML
    void updateUser() {

        if (MainUpdate.getWindow() != null) {
            MainUpdate.getWindow().close();
        }
        MainUpdate updateView = new MainUpdate();

        try {
            updateView.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void changeUser() {

        MainLogin.getWindow().show();

    }

    @FXML
    void create() {
        
        MainChooser chooser = new MainChooser();
        
        try {
            chooser.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    void delet() {

    }

    @FXML
    void open() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadPerfil();
        
        imgPerfil.setOnMouseClicked((t) -> {

            imgPerfilZoom.setImage(new Image("file:///" + logUser.getImage()));
            imgPerfilZoom.setVisible(true);

        });

        imgPerfilZoom.setOnMouseClicked((t) -> {

            imgPerfilZoom.setVisible(false);

        });

    }

    private void loadPerfil() { // load  Profile  //  Carrega o perfil

        logUser = userDao.search(UserDAO.getUser());

        if (logUser != null) {

            lblID.setText(logUser.getId() + "");
            lblNome.setText(logUser.getNome());
            lblEmail.setText(logUser.getEmail());
            if (logUser.getNascimento() != null) {
                lblNascimento.setText(logUser.getFormatedNascimento());
            }
            lblCPF.setText(logUser.getCPF());
            lblSexo.setText(logUser.getSexo());
            lblTelefone.setText(logUser.getTelefone());

            if (logUser.getImage() != null) {
                imgPerfil.setImage(new Image("file:///" + logUser.getImage()));
            }
            
            notificationLoad();
        }
    }

    private void notificationLoad() {
        
        notifications = (ArrayList<Notification>) notDAO.selectAll();
        
        int cont = 0;
        
        for (Notification notification : notifications) {
            
            RowNotification row = new RowNotification(notifications.get(cont));
            
              vboxAtividadesHoje.getChildren().add(row);
              cont++;
        }
      

}

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public ImageView getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(ImageView imgLogo) {
        this.imgLogo = imgLogo;
    }

    public Button getBtnHomePage() {
        return btnHomePage;
    }

    public void setBtnHomePage(Button btnHomePage) {
        this.btnHomePage = btnHomePage;
    }

    public Button getBtnPerfil() {
        return btnPerfil;
    }

    public void setBtnPerfil(Button btnPerfil) {
        this.btnPerfil = btnPerfil;
    }

    public Button getBtnEventos() {
        return btnEventos;
    }

    public void setBtnEventos(Button btnEventos) {
        this.btnEventos = btnEventos;
    }

    public Button getBtnCalendario() {
        return btnCalendario;
    }

    public void setBtnCalendario(Button btnCalendario) {
        this.btnCalendario = btnCalendario;
    }

    public Pane getPaneHome() {
        return paneHome;
    }

    public void setPaneHome(Pane paneHome) {
        this.paneHome = paneHome;
    }

    public Pane getPanePerfil() {
        return panePerfil;
    }

    public void setPanePerfil(Pane panePerfil) {
        this.panePerfil = panePerfil;
    }

    public ImageView getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(ImageView imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    public Label getLblNome() {
        return lblNome;
    }

    public void setLblNome(Label lblNome) {
        this.lblNome = lblNome;
    }

    public Label getLblNascimento() {
        return lblNascimento;
    }

    public void setLblNascimento(Label lblNascimento) {
        this.lblNascimento = lblNascimento;
    }

    public Label getLblEmail() {
        return lblEmail;
    }

    public void setLblEmail(Label lblEmail) {
        this.lblEmail = lblEmail;
    }

    public Label getLblTelefone() {
        return lblTelefone;
    }

    public void setLblTelefone(Label lblTelefone) {
        this.lblTelefone = lblTelefone;
    }

    public Label getLblSexo() {
        return lblSexo;
    }

    public void setLblSexo(Label lblSexo) {
        this.lblSexo = lblSexo;
    }

    public Label getLblCPF() {
        return lblCPF;
    }

    public void setLblCPF(Label lblCPF) {
        this.lblCPF = lblCPF;
    }

    public Label getLblID() {
        return lblID;
    }

    public void setLblID(Label lblID) {
        this.lblID = lblID;
    }

    public PieChart getPcActivityDone() {
        return pcActivityDone;
    }

    public void setPcActivityDone(PieChart pcActivityDone) {
        this.pcActivityDone = pcActivityDone;
    }

    public Pane getPaneEvento() {
        return paneEvento;
    }

    public void setPaneEvento(Pane paneEvento) {
        this.paneEvento = paneEvento;
    }

    public ImageView getImgLembrete() {
        return imgLembrete;
    }

    public void setImgLembrete(ImageView imgLembrete) {
        this.imgLembrete = imgLembrete;
    }

    public TabPane getpTabNotficacoes() {
        return pTabNotficacoes;
    }

    public void setpTabNotficacoes(TabPane pTabNotficacoes) {
        this.pTabNotficacoes = pTabNotficacoes;
    }

    public VBox getVboxAtividadesHoje() {
        return vboxAtividadesHoje;
    }

    public void setVboxAtividadesHoje(VBox vboxAtividadesHoje) {
        this.vboxAtividadesHoje = vboxAtividadesHoje;
    }

    public Label getLbTitulo() {
        return lbTitulo;
    }

    public void setLbTitulo(Label lbTitulo) {
        this.lbTitulo = lbTitulo;
    }

    public Label getLbData() {
        return lbData;
    }

    public void setLbData(Label lbData) {
        this.lbData = lbData;
    }

    public Label getLbHora() {
        return lbHora;
    }

    public void setLbHora(Label lbHora) {
        this.lbHora = lbHora;
    }

    public Label getLbTipo() {
        return lbTipo;
    }

    public void setLbTipo(Label lbTipo) {
        this.lbTipo = lbTipo;
    }

    public Label getLbEstado() {
        return lbEstado;
    }

    public void setLbEstado(Label lbEstado) {
        this.lbEstado = lbEstado;
    }

    public Label getLbAvisado() {
        return lbAvisado;
    }

    public void setLbAvisado(Label lbAvisado) {
        this.lbAvisado = lbAvisado;
    }

    public Pane getPaneCalendario() {
        return paneCalendario;
    }

    public void setPaneCalendario(Pane paneCalendario) {
        this.paneCalendario = paneCalendario;
    }

    public ImageView getImgPerfilZoom() {
        return imgPerfilZoom;
    }

    public void setImgPerfilZoom(ImageView imgPerfilZoom) {
        this.imgPerfilZoom = imgPerfilZoom;
    }

    public User getLogUser() {
        return logUser;
    }

    public void setLogUser(User logUser) {
        this.logUser = logUser;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<PostIt> getPostIt() {
        return postIt;
    }

    public void setPostIt(ArrayList<PostIt> postIt) {
        this.postIt = postIt;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        HomeController.index = index;
    }
}