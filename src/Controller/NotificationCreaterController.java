/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.NotificationDAO;
import Main.MainNotificationCreator;
import Model.Notification;
import com.jfoenix.controls.JFXDatePicker;
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
    private TextField txtHorary;

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

    @FXML
    void cancel() {

        MainNotificationCreator.getWindow().close();

    }

    @FXML
    void chooseFile() {

        attachment = chooser.showOpenDialog(new Stage());

        if (attachment != null) {

            notification.setAttachment(attachment);
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
        String horary = txtHorary.getText();
        String date = day + "/" + month + "/" + year + " " + horary.replace(" ", "");

        Date scheduledDay = new Date();

        try {

            scheduledDay = format.parse(date);

        } catch (ParseException ex) {
            Logger.getLogger(NotificationCreaterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        notification.setDescription(txtDescription.getText());
        notification.setImage(img.getAbsolutePath());
        notification.setScheduledDay(scheduledDay);
        notification.setTitle(txtTitle.getText());
        notification.setType(cbType.getSelectionModel().getSelectedItem());
        notification.setTypeColor(typeColor.getStyle());

        dao.insert(notification);
    }

    @FXML
    void showAttachment() {

        fileVissible = !fileVissible;

        btAttachment.setVisible(fileVissible);
    }

    @FXML
    void showImage() {

        imgVissible = !imgVissible;

        imgNotific.setVisible(imgVissible);

    }

    @FXML
    void showSound() {

        soundVissible = !soundVissible;

        btSound.setVisible(soundVissible);
        lblSound.setVisible(soundVissible);

    }

    @FXML
    void changeTypeColor() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imgNotific.setOnMouseClicked((t) -> {

            chooser.setSelectedExtensionFilter(image);

            img = chooser.showOpenDialog(new Stage());

            imgNotific.setImage(new Image("file:///"+img.getAbsolutePath()));

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
        } );
    

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

}
