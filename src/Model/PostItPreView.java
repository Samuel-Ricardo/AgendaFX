/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Main.MainPostItScreen;
import com.jfoenix.controls.JFXTextArea;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Samuel
 */
public class PostItPreView extends GridPane{
    
    private Rectangle typeColor;
    private Label title;
    private JFXTextArea body;
    private PostIt postIt;

    public PostItPreView(Rectangle typeColor, Label title, JFXTextArea body, PostIt postIt) {
        this.typeColor = typeColor;
        this.title = title;
        this.body = body;
        this.postIt = postIt;
        
        load(typeColor,title,body,postIt);
    }

    public PostItPreView(PostIt postIt) {
    
        this.body = new JFXTextArea();
        this.postIt = postIt;
        this.title = new Label();
        this.typeColor = new Rectangle();
        
        load(postIt);
    }

    public void load(Rectangle typeColor, Label title, JFXTextArea body, PostIt postIt) {
        
    this.setPrefHeight(140);
    this.setPrefWidth(140);
    this.setStyle("-fx-background-color: "+postIt.getType().getSecondaryColor()+";");
    this.setPadding(new Insets(0, 0, 0, -1));
    
    typeColor.setWidth(11);
    typeColor.setHeight(249);
    typeColor.setStyle("-fx-fill:"+postIt.getType().getPrimaryColor()+";");
    typeColor.setOnMouseClicked((t) -> {
            
        if(MainPostItScreen.getWindow() != null){
                MainPostItScreen.getWindow().close();
            }
            
            try {
                  
            MainPostItScreen PostItScreen = new MainPostItScreen();
            
      
            PostItScreen.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PostItPreView.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    });
    
    this.add(typeColor, 0, 0);
    
    title.setText(postIt.getTitle());
    title.setOnMouseClicked((t) -> {
        
        if(MainPostItScreen.getWindow() != null){
                MainPostItScreen.getWindow().close();
            }
            
            try {
                  
            MainPostItScreen PostItScreen = new MainPostItScreen();
            
      
            PostItScreen.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PostItPreView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    });
    
    this.add(title, 1, 0);
    
   body.setText(postIt.getBody());
   body.setPrefHeight(105);
   body.prefWidth(130);
   
   body.setOnMouseClicked((t) -> {
   
       if(MainPostItScreen.getWindow() != null){
                MainPostItScreen.getWindow().close();
            }
            
            try {
                  
            MainPostItScreen PostItScreen = new MainPostItScreen();
            
      
            PostItScreen.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PostItPreView.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   });
    
   this.add(body, 1, 1);
   
    }

    public void load(PostIt postIt) {
   
    this.setPrefHeight(140);
    this.setPrefWidth(140);
    this.setStyle("-fx-background-color: "+postIt.getType().getSecondaryColor()+";");
    this.setPadding(new Insets(0, 0, 0, -1));
    
    typeColor.setWidth(11);
    typeColor.setHeight(249);
    typeColor.setStyle("-fx-fill:"+postIt.getType().getPrimaryColor()+";");
    typeColor.setOnMouseClicked((t) -> {
            
        if(MainPostItScreen.getWindow() != null){
                MainPostItScreen.getWindow().close();
            }
            
            try {
                  
            MainPostItScreen PostItScreen = new MainPostItScreen();
            
      
            PostItScreen.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PostItPreView.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    });
    
    this.add(typeColor, 0, 0);
    
    title.setText(postIt.getTitle());
    title.setOnMouseClicked((t) -> {
        
        if(MainPostItScreen.getWindow() != null){
                MainPostItScreen.getWindow().close();
            }
            
            try {
                  
            MainPostItScreen PostItScreen = new MainPostItScreen();
            
      
            PostItScreen.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PostItPreView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    });
    
    this.add(title, 1, 0);
    
   body.setText(postIt.getBody());
   body.setPrefHeight(105);
   body.prefWidth(130);
   
   body.setOnMouseClicked((t) -> {
   
       if(MainPostItScreen.getWindow() != null){
                MainPostItScreen.getWindow().close();
            }
            
            try {
                  
            MainPostItScreen PostItScreen = new MainPostItScreen();
            
      
            PostItScreen.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PostItPreView.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   });
    
   this.add(body, 1, 1);
   
  }

    public Rectangle getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(Rectangle typeColor) {
        this.typeColor = typeColor;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public JFXTextArea getBody() {
        return body;
    }

    public void setBody(JFXTextArea body) {
        this.body = body;
    }

    public PostIt getPostIt() {
        return postIt;
    }

    public void setPostIt(PostIt postIt) {
        this.postIt = postIt;
    }
    
}
