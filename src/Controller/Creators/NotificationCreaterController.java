/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Creators;

import Controller.ChooserController;
import DAO.NotificationDAO;
import DAO.UserDAO;
import Main.MainChooser;
import Main.Creators.MainNotificationCreator;
import Model.Notification;
import Model.User;
import Services.Downloader;
import Controller.HomeController;
import Controller.HomeController;
import DAO.ImageDAO;
import DAO.TypeDAO;
import Model.BackupImage;
import Model.Type;
import Model.Utilities.ImageFile;
import Services.Dialoger;
import Services.FileManager;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class NotificationCreaterController implements Initializable {

    @FXML
    private TextField txtTitle;

    @FXML
    private JFXDatePicker dpDate;

    @FXML
    private TextArea txtDescription;

    @FXML
    private ComboBox<Type> cbType;

    @FXML
    private Rectangle typeColor;

    @FXML
    private ImageView imgNotific;

    @FXML
    private JFXToggleButton tbSound;

    @FXML
    private Button btSound;

    @FXML
    private Label lblSound;

    @FXML
    private JFXToggleButton tbAttachment;

    @FXML
    private Button btAttachment;

    @FXML
    private JFXToggleButton tbImage;

    @FXML
    private JFXTimePicker tpHorary;

    @FXML
    private Label lblAttachment;

    private FileChooser chooser = new FileChooser();

    private FileChooser.ExtensionFilter image = new FileChooser.ExtensionFilter("Image", "*.jpg", "*.jpeg", "*.gif", "*.png", "*.svg");

    private FileChooser.ExtensionFilter sound = new FileChooser.ExtensionFilter("sound", "*.wav", "*.mp3");

    private File attachment = new File("");

    private File music = new File("");

    private File img = new File("");

    private Notification notification = new Notification();

    private boolean imgVissible = false;

    private boolean soundVissible = false;

    private boolean fileVissible = false;

    private NotificationDAO dao = new NotificationDAO();
    
    private TypeDAO typeDao = new TypeDAO();
    
    private Dialoger dialoger = new Dialoger();
    
    private ImageDAO imageDAO = new ImageDAO();

    private BackupImage backupImage = new BackupImage();
    
    private FileManager fileManager = new FileManager();
    
    private User logUser = UserDAO.getUser();

    private static HomeController home;

    @FXML
    void cancel() { //Close the screen // Fecha a tela

        MainNotificationCreator.getWindow().close();

    }

    @FXML
    void chooseFile() { // choose attachment // escolher o arquivo

        MainChooser chooserScreen = new MainChooser();
        ChooserController chooserC = new ChooserController();
        
            ArrayList<String> alItems = new ArrayList<>();
            
            alItems.add("Arquivo local");   // carregando os items do ComboBox  // carregando os items do ComboBox;
            alItems.add("Link na internet");

        try {

            chooserScreen.start(new Stage());
            
        } catch (Exception ex) {
            Logger.getLogger(NotificationCreaterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (chooserC.loadChooser(alItems)) { // executa a açao baseado na opçao escolhida

            case 0:  // choose attachment  // escolhe o anexo
                
                    attachment = chooser.showOpenDialog(new Stage());

                    if (attachment != null) {

                        notification.setAttachment(attachment);
                        lblAttachment.setText(attachment.getName());
                    } else {
                        dialoger.errorMessage("Nenhum arquivo foi escolhido", "Nenhum arquivo selecionado, selecione um por favor e tente novamente");
                    }

                break;
                
            case 1:  // does the download of internet files // faz download de arquivo da internet
                
                    dialoger.message("Escolha o nome e local do arquivo");
                    attachment = chooser.showSaveDialog(new Stage()); // choose place of file // escolhe o local do arquivo
                
                    String link = JOptionPane.showInputDialog(null,"Digite o Link do arquivo"); // get the link typed by the user // pega o link digitado pelo usuario 
                    
                    if (attachment != null) {
                        
                        Downloader downloader = new Downloader(link, attachment.getAbsolutePath());
                        downloader.start();
                        downloader.download();      // start the file download // inicia o download do arquivo
                    
                        
                        notification.setAttachment(attachment);  // set Notification attachment // definir anexo de notificação
                        lblAttachment.setText(attachment.getName());    // puts the file name on the screen // poe o nome do arquivo na tela
                    }
                    
                    dialoger.successMessage("O arquivo está sendo baixado em 2° plano");
                    
                break;
        }
    }

    @FXML
    void chooseSound() {    // choose sound // escolher o som

        chooser.setSelectedExtensionFilter(sound);

        music = chooser.showOpenDialog(new Stage());

        if (music != null) {

            notification.setMusic(music);
            lblSound.setText(music.getName());
        }

    }

    @FXML
    void create() {  // creates the notification  // cria a notificaçao

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // creates standard date and time formatting //cria formataçao padrao de data e hora

        String day = dpDate.getValue().getDayOfMonth() + "";
        String month = "" + dpDate.getValue().getMonthValue();
        String year = "" + dpDate.getValue().getYear();
        String horary = tpHorary.getValue().getHour() + ":" + tpHorary.getValue().getMinute();
        String date = day + "/" + month + "/" + year + " " + horary.replace(" ", "");    // convert LocalDate to string // converte LocalDate em string

        Date scheduledDay = new Date();

        try {

            scheduledDay = format.parse(date);  // convert String to Date  // converter String para Data

        } catch (ParseException ex) {
            Logger.getLogger(NotificationCreaterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        notification.setBody(txtDescription.getText());
        if (img != null) {
            
            backupImage.setImage(new ImageFile(img));
            
            if(imageDAO.existByName(backupImage) == false){
                
                imageDAO.insert(backupImage);
            }else{
                imageDAO.update(backupImage);
            }
            
            
            notification.setImage(imageDAO.searchByName(backupImage.getImage().getFile().getName()).get(0));
            
            
            File destiny = new File(FileManager.getDefaultFolder()+"/Images/"+img.getName());
            fileManager.copyFile(backupImage.getImage().getFile(), destiny);
        }
        notification.setScheduledDay(scheduledDay);
        notification.setTitle(txtTitle.getText());
        notification.setType(cbType.getSelectionModel().getSelectedItem());
        notification.setUser(logUser);

        if (dao.insert(notification)) {     // creates the notification  // cria a notificaçao
            dialoger.successMessage("Criado com sucesso +,-");
        }
    }

    @FXML
    void showAttachment() { // Shows the items needed to create if desired // Mostra os items necessário para criar se desejado
  
        fileVissible = !fileVissible; 

        btAttachment.setVisible(fileVissible);
        lblAttachment.setVisible(fileVissible);

        if (fileVissible == false) {
            attachment = null;
            lblAttachment.setText("");

        }
    }

    @FXML
    void showImage() { // Shows the items needed to create if desired // Mostra os items necessário para criar se desejado

        imgVissible = !imgVissible;

        imgNotific.setVisible(imgVissible);

        if (imgVissible == false) {
            img = null;
            imgNotific.setImage(new Image("file:///" + getClass().getResource("/View/Images/image_White.png")));
        }
    }

    @FXML
    void showSound() { // Shows the items needed to create if desired // Mostra os items necessário para criar se desejado

        soundVissible = !soundVissible;

        btSound.setVisible(soundVissible);
        lblSound.setVisible(soundVissible);

        if (soundVissible == false) {
            music = null;
            lblSound.setText("Se nao escolher será tocada a padrao");
        }
    }

    @FXML
    void changeTypeColor() {  // metodo descontinuado // discontinued method

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imgNotific.setOnMouseClicked((t) -> {  // when clicking on the image // ao clicar na imagem

            chooser.setSelectedExtensionFilter(image); // set filter // coloca um filtro

            img = chooser.showOpenDialog(new Stage()); // open chooser screen // abre a tela de escolha

            imgNotific.setImage(new Image("file:///" + img.getAbsolutePath()));    // set choosed image // define a imagem escolhida
            
          //  notification.setImage(img.getAbsolutePath());   // set choosed image in notification// define a imagem escolhida na notificaçao
            
        });

        loadComboBox(); // loads the ComboBox with the types // carrega o ComboBox com os tipos

        cbType.valueProperty().addListener(new ChangeListener<Type>() {    // when exchanging item // ao trocar Item
           
            @Override
            public void changed(ObservableValue<? extends Type> ov, Type t, Type t1) {

                int index = cbType.getSelectionModel().getSelectedIndex(); // change the color of Rectangle: typeColor when change the notification //alterar a cor do retângulo: typeColor ao alterar o tipo de notificação
                String colorDetails = cbType.getSelectionModel().getSelectedItem().getPrimaryColor();
                
                typeColor.setStyle("-fx-fill: "+colorDetails+";");
                System.out.println(colorDetails);
            }
            
        });

        
        chooser.getExtensionFilters().add(sound);
        chooser.getExtensionFilters().add(image);
    }

    private void loadComboBox() {   // loads the ComboBox with the types // carrega o ComboBox com os tipos

        ArrayList<Type> arTypes = new ArrayList<>();
        
        for (Type type : typeDao.selectAllFromUser(logUser.getId().intValue())) {
            
            arTypes.add(type);
            
        }
        
        ObservableList<Type> obTypes = FXCollections.observableArrayList(arTypes);    // Convert the ArrayList to ObservableList // Converte o ArrayList para ObservableList
        
        cbType.setItems(obTypes);    // Loads the ComboBox with the ObservableList // Carrega o ComboBox com o ObservableList
    }
    
    
    ///// Getters and Setters/////

    public TextField getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(TextField txtTitle) {
        this.txtTitle = txtTitle;
    }

    public JFXDatePicker getDpDate() {
        return dpDate;
    }

    public void setDpDate(JFXDatePicker dpDate) {
        this.dpDate = dpDate;
    }

    public TextArea getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextArea txtDescription) {
        this.txtDescription = txtDescription;
    }

    public ComboBox<Type> getCbType() {
        return cbType;
    }

    public void setCbType(ComboBox<Type> cbType) {
        this.cbType = cbType;
    }

    public Rectangle getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(Rectangle typeColor) {
        this.typeColor = typeColor;
    }

    public ImageView getImgNotific() {
        return imgNotific;
    }

    public void setImgNotific(ImageView imgNotific) {
        this.imgNotific = imgNotific;
    }

    public JFXToggleButton getTbSound() {
        return tbSound;
    }

    public void setTbSound(JFXToggleButton tbSound) {
        this.tbSound = tbSound;
    }

    public Button getBtSound() {
        return btSound;
    }

    public void setBtSound(Button btSound) {
        this.btSound = btSound;
    }

    public Label getLblSound() {
        return lblSound;
    }

    public void setLblSound(Label lblSound) {
        this.lblSound = lblSound;
    }

    public JFXToggleButton getTbAttachment() {
        return tbAttachment;
    }

    public void setTbAttachment(JFXToggleButton tbAttachment) {
        this.tbAttachment = tbAttachment;
    }

    public Button getBtAttachment() {
        return btAttachment;
    }

    public void setBtAttachment(Button btAttachment) {
        this.btAttachment = btAttachment;
    }

    public JFXToggleButton getTbImage() {
        return tbImage;
    }

    public void setTbImage(JFXToggleButton tbImage) {
        this.tbImage = tbImage;
    }

    public JFXTimePicker getTpHorary() {
        return tpHorary;
    }

    public void setTpHorary(JFXTimePicker tpHorary) {
        this.tpHorary = tpHorary;
    }

    public Label getLblAttachment() {
        return lblAttachment;
    }

    public void setLblAttachment(Label lblAttachment) {
        this.lblAttachment = lblAttachment;
    }

    public FileChooser getChooser() {
        return chooser;
    }

    public void setChooser(FileChooser chooser) {
        this.chooser = chooser;
    }

    public FileChooser.ExtensionFilter getImage() {
        return image;
    }

    public void setImage(FileChooser.ExtensionFilter image) {
        this.image = image;
    }

    public FileChooser.ExtensionFilter getSound() {
        return sound;
    }

    public void setSound(FileChooser.ExtensionFilter sound) {
        this.sound = sound;
    }

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public File getMusic() {
        return music;
    }

    public void setMusic(File music) {
        this.music = music;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public boolean isImgVissible() {
        return imgVissible;
    }

    public void setImgVissible(boolean imgVissible) {
        this.imgVissible = imgVissible;
    }

    public boolean isSoundVissible() {
        return soundVissible;
    }

    public void setSoundVissible(boolean soundVissible) {
        this.soundVissible = soundVissible;
    }

    public boolean isFileVissible() {
        return fileVissible;
    }

    public void setFileVissible(boolean fileVissible) {
        this.fileVissible = fileVissible;
    }

    public NotificationDAO getDao() {
        return dao;
    }

    public void setDao(NotificationDAO dao) {
        this.dao = dao;
    }

    public User getLogUser() {
        return logUser;
    }

    public void setLogUser(User logUser) {
        this.logUser = logUser;
    }

    public static HomeController getHome() {
        return home;
    }

    public static void setHome(HomeController home) {
        NotificationCreaterController.home = home;
    }

}
