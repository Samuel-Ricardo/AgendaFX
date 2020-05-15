/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.NotificationDAO;
import DAO.UserDAO;
import Main.MainNotificationCreator;
import Model.Notification;
import Model.User;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
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
    private ComboBox<String> cbType;

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

    private FileChooser.ExtensionFilter image = new FileChooser.ExtensionFilter("Image", "*.jpg", "*.jpeg", "*gif", "*.png", "*.svg");

    private FileChooser.ExtensionFilter sound = new FileChooser.ExtensionFilter("sound", "*.wav", "*.mp3");

    private File attachment = new File("");

    private File music = new File("");

    private File img = new File("");

    private Notification notification = new Notification();

    private boolean imgVissible = false;

    private boolean soundVissible = false;

    private boolean fileVissible = false;

    private NotificationDAO dao = new NotificationDAO();

    private User logUser = UserDAO.getUser();
    
    private static HomeController home;

    @FXML
    void cancel() { //Close the screen // Fecha a tela

        MainNotificationCreator.getWindow().close();

    }

    @FXML
    void chooseFile() { // choose the attachment

        
        attachment = chooser.showOpenDialog(new Stage());

        if (attachment != null) {

            notification.setAttachment(attachment);
            lblAttachment.setText(attachment.getName());
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo foi escolhido");
        }
    }

    @FXML
    void chooseSound() {

        chooser.setSelectedExtensionFilter(sound);

        music = chooser.showOpenDialog(new Stage());

        if (music != null) {

            notification.setMusic(music);
            lblSound.setText(music.getName());
        }

    }

    @FXML
    void create() {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String day = dpDate.getValue().getDayOfMonth() + "";
        String month = "" + dpDate.getValue().getMonthValue();
        String year = "" + dpDate.getValue().getYear();
        String horary = tpHorary.getValue().getHour() + ":" + tpHorary.getValue().getMinute();
        String date = day + "/" + month + "/" + year + " " + horary.replace(" ", "");

        Date scheduledDay = new Date();

        try {

            scheduledDay = format.parse(date);

        } catch (ParseException ex) {
            Logger.getLogger(NotificationCreaterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        notification.setDescription(txtDescription.getText());
        if (img == null) {
            notification.setImage(img.getAbsolutePath());
        }
        notification.setScheduledDay(scheduledDay);
        notification.setTitle(txtTitle.getText());
        notification.setType(cbType.getSelectionModel().getSelectedItem());
        notification.setTypeColor(typeColor.getStyle());
        notification.setUser(logUser);

        if(dao.insert(notification)){
            JOptionPane.showMessageDialog(null, "Criado");
        }
    }

    @FXML
    void showAttachment() {

        fileVissible = !fileVissible;

        btAttachment.setVisible(fileVissible);
        lblAttachment.setVisible(fileVissible);

        if (fileVissible == false) {
            attachment = null;
            lblAttachment.setText("");

        }
    }

    @FXML
    void showImage() {

        imgVissible = !imgVissible;

        imgNotific.setVisible(imgVissible);

        if (imgVissible == false) {
            img = null;
            imgNotific.setImage(new Image("file:///"+getClass().getResource("/View/Images/image_White.png")));
        }
    }

    @FXML
    void showSound() {

        soundVissible = !soundVissible;

        btSound.setVisible(soundVissible);
        lblSound.setVisible(soundVissible);

        if (soundVissible == false) {
            music = null;
            lblSound.setText("Se nao escolher será tocada a padrao");
        }
    }

    @FXML
    void changeTypeColor() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imgNotific.setOnMouseClicked((t) -> {

            chooser.setSelectedExtensionFilter(image);

            img = chooser.showOpenDialog(new Stage());

            imgNotific.setImage(new Image("file:///" + img.getAbsolutePath()));

        });

        loadComboBox();

        cbType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {

                int index = cbType.getSelectionModel().getSelectedIndex();
                String style = "";

                switch (index) {

                    case 0:

                        style = "-fx-fill: #ff0000;";
                        typeColor.setStyle(style);
                        break;

                    case 1:

                        style = "-fx-fill: #8a2be2;";
                        typeColor.setStyle(style);
                        break;

                    case 2:

                        style = "-fx-fill: #d4ff00;";
                        typeColor.setStyle(style);
                        break;

                    case 3:

                        style = "-fx-fill: #ffd700;";
                        typeColor.setStyle(style);
                        break;

                    case 4:

                        style = "-fx-fill: #000080;";
                        typeColor.setStyle(style);
                        break;
                }
            }
        });

    }

    private void loadComboBox() {

        ArrayList<String> arTypes = new ArrayList<>();

        arTypes.add("Urgente");
        arTypes.add("Trabalho / Escola");
        arTypes.add("Evento");
        arTypes.add("Especial");
        arTypes.add("Banal");

        ObservableList<String> obTypes = FXCollections.observableArrayList(arTypes);

        cbType.setItems(obTypes);
    }

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

    public ComboBox<String> getCbType() {
        return cbType;
    }

    public void setCbType(ComboBox<String> cbType) {
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
