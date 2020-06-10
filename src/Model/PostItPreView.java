/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

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
    
    this.add(typeColor, 0, 0);
    
    title.setText(postIt.getTitle());
    
    this.add(title, 1, 0);
    
   body.setText(postIt.getBody());
   body.setPrefHeight(105);
   body.prefWidth(130);
    
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
    
    this.add(typeColor, 0, 0);
    
    title.setText(postIt.getTitle());
    
    this.add(title, 1, 0);
    
   body.setText(postIt.getBody());
   body.setPrefHeight(105);
   body.prefWidth(130);
    
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
