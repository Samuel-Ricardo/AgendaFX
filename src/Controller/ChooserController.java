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
    public void initialize(URL url, ResourceBundle rb) {        // execute when starting // executa ao iniciar

        alTypes.add("Notficaçao");  // Load the Array List with the options // Carrega o ArrayList com as opçoes;
        alTypes.add("PostIt");      
        alTypes.add("Atividade");

        obsTypes = FXCollections.observableArrayList(alTypes);  // Convert the ArrayList to ObservableList // Converte o ArrayList para ObservableList

        cbType.setItems(obsTypes);                              // Load the ComboBox with the ObservableList // Carrega o ComboBox com o ObservableList

    }

    @FXML
    public void createThis(ActionEvent event) {                    // Opens the creation window corresponding to the one selected // Abre a janela de criaçao correspondente ao que foi selecionado

        index = cbType.getSelectionModel().getSelectedIndex();   // get what was selected in the ComboBox  //  pega o que foi selecionado no ComboBox

        switch (index) {                                       // choose the window to open  //  escolhe a janela a ser aberta

            case 0:

                if (MainNotificationCreator.getWindow() != null) {  // Close the window if it is open // Fecha a janela se estiver aberta

                    MainNotificationCreator.getWindow().close();
                }

                MainNotificationCreator not = new MainNotificationCreator();

                try {
                    not.start(new Stage());         // Open the window // abre a janela
                } catch (Exception ex) {
                    Logger.getLogger(ChooserController.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

        }
        MainChooser.getWindow().close();      // closes the current window  // fecha a janela atual
    }

        //////Getters and Setters////////
    
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
