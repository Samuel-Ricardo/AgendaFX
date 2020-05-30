/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Samuel
 */
public class Row extends HBox {

    protected Rectangle typeColor;
    protected Label title;
    protected Label date;
    protected Notification notification;
    protected PostIt postIt;
    protected String style ;
    protected JFXTextArea description; 

    ///////Construtores  //  Constructos /////////
    public Row(Rectangle typeColor, Label title, Label date, Notification notification, String style) {
        this.typeColor = typeColor;
        this.title = title;
        this.date = date;
        this.notification = notification;
        this.style = style;
        loadPane(typeColor, title, date, notification,style);

    }

    public Row(Notification notification) {

        this.typeColor = new Rectangle(20, 20);
        this.title = new Label();
        this.date = new Label();
        this.notification = notification;
        this.style = "-fx-background-color: #343232;"
                           + "-fx-background-radius: 100px;"
                           + "-fx-border-radius: 100px;";
        
        loadPane(notification);

    }

    public Row(PostIt postIt) {

        this.typeColor = new Rectangle(20, 20);
        this.title = new Label();
        this.date = new Label();
        this.postIt = postIt;
        this.style = "-fx-background-color: #343232;"
                           + "-fx-background-radius: 100px;"
                           + "-fx-border-radius: 100px;";
        
        loadPane(postIt);
    }

    ///////metodos  //  method /////////
    private void loadPane(Rectangle typeColor, Label title, Label date, Notification notification, String style) {

        this.setPrefWidth(772);
        this.setPrefHeight(65);
           
        this.setStyle(style);
        this.setPadding(new Insets(20, 0, 0, 20));
        this.setSpacing(90);
         
        typeColor.prefWidth(30);
        typeColor.prefHeight(30);
        typeColor.setStyle("-fx-fill: "+notification.getType().getPrimaryColor()+";");
        typeColor.setVisible(true);
        
        title.prefWidth(363);
        title.prefHeight(30);
        title.setLayoutX(67);
        title.setLayoutY(25);
        title.setText(notification.getTitle());
        title.setVisible(true);
        
        date.prefWidth(363);
        date.prefHeight(30);
        date.setLayoutX(67);
        date.setLayoutY(25);
        if (notification.getScheduledDay() != null) {
            date.setText(notification.getScheduledDate() + " as " + notification.getScheduledHour());
        }else{
            date.setText("sem hora marcada");
        }
        date.setVisible(true);
        
              this.getChildren().addAll(typeColor,title,date);
              
        this.setVisible(true);
    }

    public void loadPane(Notification notification) {

        this.setPrefWidth(772);
        this.setPrefHeight(65);
           
        this.setStyle(style);
        this.setPadding(new Insets(20, 0, 0, 20));
        this.setSpacing(90);
         
        typeColor.prefWidth(30);
        typeColor.prefHeight(30);
        typeColor.setStyle("-fx-fill: "+notification.getType().getPrimaryColor()+";");
        typeColor.setVisible(true);
        
        title.prefWidth(363);
        title.prefHeight(30);
        title.setLayoutX(67);
        title.setLayoutY(25);
        title.setText(notification.getTitle());
        title.setVisible(true);
        
        date.prefWidth(363);
        date.prefHeight(30);
        date.setLayoutX(67);
        date.setLayoutY(25);
        if (notification.getScheduledDay() != null) {
            date.setText(notification.getScheduledDate() + " as " + notification.getScheduledHour());
        }else{
            date.setText("sem hora marcada");
        }
        date.setVisible(true);
        
              this.getChildren().addAll(typeColor,title,date);
              
        this.setVisible(true);
    }

    public void loadPane(PostIt postIt) {
        
        this.setPrefWidth(772);
        this.setPrefHeight(65);
           
        this.setStyle(style);
        this.setPadding(new Insets(20, 0, 0, 20));
        this.setSpacing(90);

        typeColor.prefWidth(30);
        typeColor.prefHeight(30);
        typeColor.setStyle("-fx-fill: "+postIt.getType().getPrimaryColor()+";");
        typeColor.setVisible(true);
        
        title.prefWidth(363);
        title.prefHeight(30);
        title.setLayoutX(67);
        title.setLayoutY(25);
        title.setText(postIt.getTitle());
        title.setVisible(true);
        
        date.prefWidth(287);
        date.prefHeight(69);
        date.setLayoutX(113);
        date.setLayoutY(42);
        if (notification.getScheduledDay() != null) {
            date.setText(notification.getScheduledDate() + " as " + notification.getScheduledHour());
        }else{
            date.setText("sem hora marcada");
        }
        date.setVisible(true);
        
        this.getChildren().addAll(typeColor,title,date);
              
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

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public PostIt getPostIt() {
        return postIt;
    }

    public void setPostIt(PostIt postIt) {
        this.postIt = postIt;
    }
    
    

}
