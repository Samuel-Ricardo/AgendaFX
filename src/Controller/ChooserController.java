/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainChooser;
import Main.MainNotificationCreator;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class ChooserController implements Initializable {

    @FXML
    private Pane ChooserPane;
    @FXML
    private ComboBox<String> cbType;
    private ArrayList<String> alTypes = new ArrayList<>();
    private ObservableList<String> obsTypes;
    private int index;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        alTypes.add("Notficaçao");
        alTypes.add("PostIt");
        alTypes.add("Atividade");

        obsTypes = FXCollections.observableArrayList(alTypes);

        cbType.setItems(obsTypes);

    }

    @FXML
    public void createThis(ActionEvent event) {

        index = cbType.getSelectionModel().getSelectedIndex();

        switch (index) {

            case 0:

                if (MainNotificationCreator.getWindow() != null) {

                    MainNotificationCreator.getWindow().close();
                }

                MainNotificationCreator not = new MainNotificationCreator();

                try {
                    not.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(ChooserController.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

        }
        MainChooser.getWindow().close();
    }

    public Pane getChooserPane() {
        return ChooserPane;
    }

    public void setChooserPane(Pane ChooserPane) {
        this.ChooserPane = ChooserPane;
    }

    public ComboBox<String> getCbType() {
        return cbType;
    }

    public void setCbType(ComboBox<String> cbType) {
        this.cbType = cbType;
    }

    public ArrayList<String> getAlTypes() {
        return alTypes;
    }

    public void setAlTypes(ArrayList<String> alTypes) {
        this.alTypes = alTypes;
    }

    public ObservableList<String> getObsTypes() {
        return obsTypes;
    }

    public void setObsTypes(ObservableList<String> obsTypes) {
        this.obsTypes = obsTypes;
    }

}
