/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.awt.Component;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class Dialoger {
    
            
         Alert alert = newAlert(Alert.AlertType.INFORMATION);
    
    public static void message(Component component, String message){
        
        JOptionPane.showMessageDialog(component, message);
    }
    
    
    
    public void message(String message, Alert.AlertType type){ 
     
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(type);
        
        alert.setTitle("Mensagem");
        alert.setHeaderText("Informação");
        alert.setContentText(message);
        
      
        alert.show();
        });
    }   

    public Alert newAlert(Alert.AlertType type) {

        Platform.runLater(() -> {

        Alert alert = new Alert(type);
        
        });
        
        return alert;
    }
    
    public void message(String header,String message, Alert.AlertType type){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(type);
        
        alert.setTitle("Mensagem");
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.show();
        });
    } 
    
    public void message(String title, String header, String message, Alert.AlertType type){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(type);
        
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.show();
        });
    }   
    
    
    
    
    
    
    public void message(String message){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setTitle("Mensagem");
        alert.setHeaderText("Informação");
        alert.setContentText(message);
        
        alert.show();
        });
    }   
    
    public void successMessage(String message){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setTitle("Mensagem");
        alert.setHeaderText("Sucesso (/ >,>)/");
        alert.setContentText(message);
        
        alert.show();
        });
    }   
    
    public void loadingMessage(String message){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setTitle("Carregando");
        alert.setHeaderText("Carregando (~ U ,U)~");
        alert.setContentText(message);
        
        alert.show();
        });
    }   
    
    public void incompletePartOfSoftware(){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setTitle("Incompleto");
        alert.setHeaderText("Incompleto");
        alert.setContentText("Essa parte do software ainda não está pronta, aguarde as proximas atualizações (~ X ,X)~");
        
        alert.show();
        });
    }
    
    public void incompletePartOfSoftware(String message){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setTitle("Incompleto");
        alert.setHeaderText("Incompleto");
        alert.setContentText(message);
        
        alert.show();
        });
    }   
    
    public void message(String header,String message){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setTitle("Mensagem");
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.show();
        });
    } 
    
    public void message(String title, String header, String message){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.show();
        });
    } 
    
    
    
    public Alert confirmation(String message){
   
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Confirmação");
        alert.setHeaderText("Confirme");
        alert.setContentText(message);
        
        alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.CANCEL);
        
        showAndWait(alert);
        
        return alert;
    }   
    
    public Alert confirmation(String header,String message){
         
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Confirmação");
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.CANCEL);
        
        showAndWait(alert);
        
        return alert;
    } 
    
    public Alert confirmation(String title, String header, String message){
        
         Alert alert = newAlert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.CANCEL);
         
        showAndWait(alert);
        
        return alert;
    } 
    
    
    
    
    
    
    
    public void warning(String message){
        
        Platform.runLater(() -> {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setTitle("Atenção");
        alert.setHeaderText("Importante");
        alert.setContentText(message);
        
        alert.show();
        });
    }   
    
    public void warning(String header,String message){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setTitle("Atenção");
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.show();
        });
    } 
    
    public void warning(String title, String header, String message){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.show();
        });
    }   
    
    
    
    
    
    
    
    public void errorMessage(String message,String Erro){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.ERROR);
        
        alert.setTitle("Erro");
        alert.setHeaderText("Erro");
        alert.setContentText(message);
        
        TextArea area = new TextArea(Erro);
        
        alert.getDialogPane().setExpandableContent(area);
        
        alert.show();
        });
    }  
    
    public void errorMessage(String message, Exception ex){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.ERROR);
        
        alert.setTitle("Erro");
        alert.setHeaderText("Erro");
        alert.setContentText(message);
        
        TextArea area = new TextArea("Erro :"+ ex.getMessage()+ "\n \n Causa: " +ex.getCause());
        
        alert.getDialogPane().setExpandableContent(area);
        
        alert.show();
        });
    }   
    
    public void errorMessage(String header, String message, String Erro){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.ERROR);
        
        alert.setTitle("Erro");
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        TextArea area = new TextArea(Erro);
        
        alert.getDialogPane().setExpandableContent(area);
        
        alert.show();
        });
    } 
    
    public void errorMessage(String title, String header, String message, String Erro){
        
        Platform.runLater(() -> {
            
         Alert alert = new Alert(Alert.AlertType.ERROR);
        
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        TextArea area = new TextArea(Erro);
        
        alert.getDialogPane().setExpandableContent(area);
        
        alert.show();
        });
    }   
    
    public void show(Alert alert){
        
        Platform.runLater(() -> {
            
            alert.show();
        });
    }

    private void showAndWait(Alert alert) {
      
        Platform.runLater(() -> {
            
            alert.showAndWait();
        });
    }
}
