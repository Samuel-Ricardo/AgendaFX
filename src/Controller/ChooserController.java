/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainChooser;
import Main.Creators.MainNotificationCreator;
import Main.Creators.MainPostItCreator;
import Main.Creators.MainTypeCreator;
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
import javafx.scene.control.Button;
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
    
    @FXML
    private Button btCreate;

    @FXML
    private Button btChoose;
  
    private static ComboBox<String> staticCbType;
    
    private static Button staticbtCreate;

    private static Button staticbtChoose;
    
    private static ArrayList<String> alTypes = new ArrayList<>();
    
    private static ObservableList<String> obsTypes;
    
    private static int index = 100000;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        // execute when starting // executa ao iniciar
    
        staticCbType = cbType;
        staticbtChoose = btChoose;
        staticbtCreate = btCreate;
        
        alTypes.add("Notficaçao");  // Loads the Array List with the options // Carrega o ArrayList com as opçoes;
        alTypes.add("PostIt");      
        alTypes.add("Atividade");
        alTypes.add("Tipo Novo");

        obsTypes = FXCollections.observableArrayList(alTypes);  // Convert the ArrayList to ObservableList // Converte o ArrayList para ObservableList

        staticCbType.getItems().clear();
        staticCbType.setItems(obsTypes);    
       
    }

    public int loadChooser(ArrayList<String> alItems){      // loads a custom screen // carrega uma tela personalizada
        
        this.alTypes = alItems;   // Loads the ArrayList of this class with the Arraylist passed as a parameter by the method //  Carrega o ArrayList dessa classe com o Arraylist passado como parametro pelo método
        
        obsTypes = FXCollections.observableArrayList(alTypes);  // Convert the ArrayList to ObservableList // Converte o ArrayList para ObservableList

        cbType.setItems(obsTypes);                            // Loads the ComboBox with the ObservableList // Carrega o ComboBox com o ObservableList
        
        btCreate.setVisible(false); // removes the default button from the screen // remove o botao padrao da tela
        btChoose.setVisible(true); // put a new button // poe um novo botao
        
        btChoose.setOnMouseClicked((t) -> {
        
            index = cbType.getSelectionModel().getSelectedIndex();  // get the selected item at click in the button// pega o item selecionado ao clicar no botao
            
        });
                
        while(index == 100000){
            System.out.println();   // wait until you select an item // espera  até selecionar um item
        }
        
        return index; //return selected Item // retorna item selecionado
} 
    
    public void chooseCreater(){    // loads Default screen // carrega a tela padrao
        
        alTypes.add("Notficaçao");  // Loads the Array List with the options // Carrega o ArrayList com as opçoes;
        alTypes.add("PostIt");      
        alTypes.add("Atividade");

        obsTypes = FXCollections.observableArrayList(alTypes);  // Convert the ArrayList to ObservableList // Converte o ArrayList para ObservableList

        cbType.setItems(obsTypes);                            // Loads the ComboBox with the ObservableList // Carrega o ComboBox com o ObservableList
 
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
                
            case 1:
                
                if (MainPostItCreator.getWindow() != null) {  // Close the window if it is open // Fecha a janela se estiver aberta

                    MainPostItCreator.getWindow().close();
                }

                MainPostItCreator postIt = new MainPostItCreator();

                try {
                    postIt.start(new Stage());         // Open the window // abre a janela
                } catch (Exception ex) {
                    Logger.getLogger(ChooserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
             
            case 3:
                
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
                
                break;

        }
        MainChooser.getWindow().close();   // closes the current window  // fecha a janela atual
        
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
