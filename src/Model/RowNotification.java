/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Samuel
 */
public class RowNotification extends Pane{

    private Rectangle typeColor;
    private Label title;
    private Label date;
    private Notification notification;
    private PostIt postIt;
 
    ///////Construtores  //  Constructos /////////
    public RowNotification(Rectangle typeColor, Label title, Label date, Notification notification) {
        this.typeColor = typeColor;
        this.title = title;
        this.date = date;
        this.notification = notification;
        loadPane( typeColor, title, date,notification);

    }

    public RowNotification(Notification notification) {
        
           this.typeColor = new Rectangle();
           this.title = new Label();
           this.date = new Label();
           this.notification = notification;
        
        loadPane(notification);
       
    }

    public RowNotification(PostIt postIt) {
        
        this.typeColor = new Rectangle();
        this.title = new Label();
        this.date = new Label();
        this.postIt = postIt;
        
        loadPane(postIt);
    }
    
    

    ///////metodos  //  method /////////
    private void loadPane(Rectangle typeColor, Label title, Label date,Notification notification) {

        this.setPrefWidth(782);
        this.setPrefHeight(75);
        this.setStyle("  -fx-background-color: white;" +
                      "  -fx-background-radius: 100px;" +
                      "  -fx-border-radius: 30px;");

        this.getChildren().add(title);
        this.getChildren().add(typeColor);
        this.getChildren().add(date);
        
        typeColor.prefWidth(30);
        typeColor.prefHeight(30);
        typeColor.setX(21);
        typeColor.setY(25);
        typeColor.setStyle(notification.getTypeColor());

        title.prefWidth(363);
        title.prefHeight(30);
        title.setLayoutX(67);
        title.setLayoutY(25);
        title.setText(notification.getTitle());

        date.prefWidth(363);
        date.prefHeight(30);
        date.setLayoutX(67);
        date.setLayoutY(25);
        date.setText(notification.getScheduledDate()+" as "+notification.getScheduledHour());

        this.setVisible(true);
    }

    private void loadPane(Notification notification) {

        this.setPrefWidth(782);
        this.setPrefHeight(75);
    
        
        typeColor.prefWidth(30);
        typeColor.prefHeight(30);
        typeColor.setX(21);
        typeColor.setY(25);
        typeColor.setStyle(notification.getTypeColor());

        title.prefWidth(363);
        title.prefHeight(30);
        title.setLayoutX(67);
        title.setLayoutY(25);
        title.setText(notification.getTitle());

        date.prefWidth(363);
        date.prefHeight(30);
        date.setLayoutX(67);
        date.setLayoutY(25);
        date.setText(notification.getScheduledDate()+" as "+notification.getScheduledHour());

        this.getChildren().add(title);
        this.getChildren().add(typeColor);
        this.getChildren().add(date);
        
        this.setStyle("  -fx-background-color: white;" +
                      "  -fx-background-radius: 100px;" +
                      "  -fx-border-radius: 100px;");
        
        this.setVisible(true);
    }

    
    private void loadPane(PostIt postIt) {

        this.setPrefWidth(782);
        this.setPrefHeight(75);
    
        
        typeColor.prefWidth(30);
        typeColor.prefHeight(30);
        typeColor.setX(21);
        typeColor.setY(25);
        typeColor.setStyle(postIt.getTypeColor());

        title.prefWidth(363);
        title.prefHeight(30);
        title.setLayoutX(67);
        title.setLayoutY(25);
        title.setText(postIt.getTitle());

        date.prefWidth(363);
        date.prefHeight(30);
        date.setLayoutX(67);
        date.setLayoutY(25);
        date.setText(postIt.getScheduledDate()+" as "+postIt.getScheduledHour());

        this.getChildren().add(title);
        this.getChildren().add(typeColor);
        this.getChildren().add(date);
        
        this.setStyle("  -fx-background-color: white;" +
                      "  -fx-background-radius: 100px;" +
                      "  -fx-border-radius: 100px;");
        
        this.setVisible(true);
    }

    
    ///////get and set/////////

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

    public Label getDate() {
        return date;
    }

    public void setDate(Label description) {
        this.date = description;
    }

}
