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
import Main.Creators.MainRegister;
import Main.Creators.MainTypeCreator;
import Main.Updaters.MainUpdate;
import Model.Notification;
import Model.PostIt;
import Model.Utilities.Row;
import Model.Type;
import Model.Utilities.TypeRow;
import Model.User;
import Helper.Filler;
import Main.MainMural;
import Services.Notify;
import Services.SecondPlan;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
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
    private ImageView imgPerfilZoom;

    @FXML
    private ListView<Row> lvTodayNotifications;
    
    @FXML
    private ListView<Row> lvMyDay;

    @FXML
    private ListView<Row> lvAllEvents;
    
    @FXML
    private ListView<Row> lvActivitiesDone;

    @FXML
    private ListView<TypeRow> lvTypesEvent;
    
    @FXML
    private AreaChart<String, Double> acOcuppiedDaysGraph;

    @FXML
    private CategoryAxis acX;

    @FXML
    private NumberAxis acY;
    
    @FXML
    private PieChart pcMostUsedEvents;
    
    @FXML
    private ComboBox<String> cbTimeInterval;
    
    @FXML
    private WebView wbvGmail;

    @FXML
    private WebView wbvOutlook;

    @FXML
    private WebView wbvGitHub;
    
    @FXML
    private ImageView imgAddType;

    
    private final UserDAO userDao = new UserDAO();

    private final NotificationDAO notDAO = new NotificationDAO();

    private User logUser = userDao.search(UserDAO.getUser());;

    private ArrayList<Notification> notifications;

    private ArrayList<PostIt> postIt;

    private static int index;
    
    private Notify notify = new Notify(this);
    
    private final SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyy");
    
    private Filler filler = new Filler(this);
        
    
            

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
        
        loadEvents();
        
        if (paneCalendario.isVisible() == true) {
            paneCalendario.setVisible(false);
        }
        if (paneHome.isVisible() == true) {
            paneHome.setVisible(false);
        }
        if (panePerfil.isVisible() == true) {
            panePerfil.setVisible(false);
        }
        
        Filler.fillOutAllTypes(lvTypesEvent);
        Filler.fillOutNotificationsOfToday(lvMyDay);

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
        
        XYChart.Series janLine = new XYChart.Series();
        
        janLine.setName("Janeiro");
        janLine.getData().add(new XYChart.Data("Segunda", 10));
        janLine.getData().add(new XYChart.Data("Terça", 20));
        janLine.getData().add(new XYChart.Data("Quarta", 35));
        janLine.getData().add(new XYChart.Data("Quinta", 28));
        janLine.getData().add(new XYChart.Data("Sexta", 15));
        janLine.getData().add(new XYChart.Data("Sábado", 8));
        janLine.getData().add(new XYChart.Data("Domino", 5));
        
         XYChart.Series fevLine = new XYChart.Series();
        
        fevLine.setName("Fevereiro");
        fevLine.getData().add(new XYChart.Data("Segunda", 30));
        fevLine.getData().add(new XYChart.Data("Terça", 10));
        fevLine.getData().add(new XYChart.Data("Quarta", 55));
        fevLine.getData().add(new XYChart.Data("Quinta", 28));
        fevLine.getData().add(new XYChart.Data("Sexta", 8));
        fevLine.getData().add(new XYChart.Data("Sábado", 80));
        fevLine.getData().add(new XYChart.Data("Domino", 20));
        
        XYChart.Series marLine = new XYChart.Series();
        
        marLine.setName("Março");
        marLine.getData().add(new XYChart.Data("Segunda", 10));
        marLine.getData().add(new XYChart.Data("Terça", 40));
        marLine.getData().add(new XYChart.Data("Quarta", 35));
        marLine.getData().add(new XYChart.Data("Quinta", 18));
        marLine.getData().add(new XYChart.Data("Sexta", 25));
        marLine.getData().add(new XYChart.Data("Sábado", 28));
        marLine.getData().add(new XYChart.Data("Domino", 15));
        
        acOcuppiedDaysGraph.getData().clear();
        acOcuppiedDaysGraph.getData().addAll(janLine,fevLine,marLine);
        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                
                new PieChart.Data("Notificaçao", 38),
                new PieChart.Data("PostIt", 30),
                new PieChart.Data("Atividades", 40)
                
        );
        
        pcMostUsedEvents.getData().clear();
        pcMostUsedEvents.setData(data);
        //pcMostUsedEvents.setLabelLineLength(100);
        pcMostUsedEvents.setLabelsVisible(true);
        pcMostUsedEvents.setLegendVisible(true);
        //pcMostUsedEvents.setLegendSide(Side.RIGHT);
        
        wbvGmail.getEngine().load("https://www.google.com/gmail/about/");
        wbvOutlook.getEngine().load("https://outlook.live.com/mail/0/inbox");
        wbvGitHub.getEngine().load("https://github.com/Samuel-Ricardo");
        
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
        
        loadPerfil();
    }

    @FXML
    void changeUser() {

        MainLogin.getWindow().show();

    }

    @FXML
    void create() { // opens a window to choose what to create // abre janela para escolher o que criar

        MainChooser chooser = new MainChooser();
        ChooserController chooseController = new ChooserController();
        
        try {
            
            chooser.start(new Stage());
      
            chooseController.chooseCreater();
            
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    void delet() { // delete the chosen item // deleta o item escolhido 

        ObservableList<Row> rows= lvAllEvents.getSelectionModel().getSelectedItems();
        
        int cont = 0;
        for (Pane row : rows) {
            
            notDAO.delete(rows.get(cont).getNotification());
            
        }
        Filler.setList(lvAllEvents);
        filler.fillOutAllEventNotifications(lvAllEvents);
    }

    @FXML
    void openMural() { 

        try {
            
            if(MainMural.getWindow() != null){
                MainMural.getWindow().close();
            }
            
            MainMural mural = new MainMural();
            
            mural.start(new Stage());
            
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        notify.start();    // start notification service // inicia o serviço de notificaçao

        loadPerfil();

        
        
        imgPerfil.setOnMouseClicked((t) -> {

            imgPerfilZoom.setImage(new Image("file:///" + logUser.getImage()));
            imgPerfilZoom.setVisible(true);

        });

        imgPerfilZoom.setOnMouseClicked((t) -> {        // zoom

            imgPerfilZoom.setVisible(false);

            
        });
        
         
         
        SecondPlan secondPlan = new SecondPlan();   
        
        secondPlan.start(); // starts the program in the background // inicia  o programa em 2° plano
        
        Type.loadDefaultTypes();
    }

    private void loadPerfil() { // load  Profile  //  Carrega o perfil

        if (logUser != null) {

            lblID.setText(logUser.getId() + "");
            lblNome.setText(logUser.getNome());
            lblEmail.setText(logUser.getEmail());
            if (logUser.getNascimento() != null) {
                lblNascimento.setText(logUser.getNascimento().getOnlyDate());
            }
            lblCPF.setText(logUser.getCPF());
            lblSexo.setText(logUser.getSexo());
            lblTelefone.setText(logUser.getTelefone());

            if (logUser.getImage() != null) {
                imgPerfil.setImage(new Image("file:///" + logUser.getImage()));
            }
            
            Filler.setList(lvTodayNotifications);
            filler.fillTodaysScheduledActivities(lvTodayNotifications);
            filler.fillOutActivitiesDoneToday(lvActivitiesDone);
        }
    }
    
        private void loadEvents() {
      
        filler.setList(lvAllEvents);
        filler.fillOutAllEventNotifications(lvAllEvents);
        
        imgAddType.setOnMouseClicked((t) -> {
        
            if(MainTypeCreator.getWindow() != null){
                    
                    
                    MainTypeCreator.getWindow().close();
                }
                
                MainTypeCreator typeCreator = new MainTypeCreator();
                
            {
                try {
                    typeCreator.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(ChooserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
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

    public ListView<Row> getLvTodayNotifications() {
        return lvTodayNotifications;
    }

    public void setLvTodayNotifications(ListView<Row> lvNotifications) {
        this.lvTodayNotifications = lvNotifications;
    }

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }    

    public ListView<Row> getLvAllEvents() {
        return lvAllEvents;
    }

    public void setLvAllEvents(ListView<Row> lvAllEvents) {
        this.lvAllEvents = lvAllEvents;
    }

    public Filler getFiller() {
        return filler;
    }

    public void setFiller(Filler filler) {
        this.filler = filler;
    }

    public ListView<Row> getLvMyDay() {
        return lvMyDay;
    }

    public void setLvMyDay(ListView<Row> lvMyDay) {
        this.lvMyDay = lvMyDay;
    }

    public ListView<Row> getLvActivitiesDone() {
        return lvActivitiesDone;
    }

    public void setLvActivitiesDone(ListView<Row> lvActivitiesDone) {
        this.lvActivitiesDone = lvActivitiesDone;
    }

    public ListView<TypeRow> getLvTypesEvent() {
        return lvTypesEvent;
    }

    public void setLvTypesEvent(ListView<TypeRow> lvTypesEvent) {
        this.lvTypesEvent = lvTypesEvent;
    }

    public AreaChart<String, Double> getAcOcuppiedDaysGraph() {
        return acOcuppiedDaysGraph;
    }

    public void setAcOcuppiedDaysGraph(AreaChart<String, Double> acOcuppiedDaysGraph) {
        this.acOcuppiedDaysGraph = acOcuppiedDaysGraph;
    }

    public CategoryAxis getAcX() {
        return acX;
    }

    public void setAcX(CategoryAxis acX) {
        this.acX = acX;
    }

    public NumberAxis getAcY() {
        return acY;
    }

    public void setAcY(NumberAxis acY) {
        this.acY = acY;
    }

    public PieChart getPcMostUsedEvents() {
        return pcMostUsedEvents;
    }

    public void setPcMostUsedEvents(PieChart pcMostUsedEvents) {
        this.pcMostUsedEvents = pcMostUsedEvents;
    }

    public ComboBox<String> getCbTimeInterval() {
        return cbTimeInterval;
    }

    public void setCbTimeInterval(ComboBox<String> cbTimeInterval) {
        this.cbTimeInterval = cbTimeInterval;
    }

    public WebView getWbvGmail() {
        return wbvGmail;
    }

    public void setWbvGmail(WebView wbvGmail) {
        this.wbvGmail = wbvGmail;
    }

    public WebView getWbvOutlook() {
        return wbvOutlook;
    }

    public void setWbvOutlook(WebView wbvOutlook) {
        this.wbvOutlook = wbvOutlook;
    }

    public WebView getWbvGitHub() {
        return wbvGitHub;
    }

    public void setWbvGitHub(WebView wbvGitHub) {
        this.wbvGitHub = wbvGitHub;
    }

    public ImageView getImgAddType() {
        return imgAddType;
    }

    public void setImgAddType(ImageView imgAddType) {
        this.imgAddType = imgAddType;
    }
    
}
