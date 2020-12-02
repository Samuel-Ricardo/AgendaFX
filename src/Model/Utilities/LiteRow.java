/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Utilities;

import Model.Notification;
import Model.PostIt;
import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Samuel
 */
public class LiteRow extends BorderPane {

    protected ImageView image;
    protected Label title;
    protected Notification notification;
    protected PostIt postIt;
    protected String style;
    protected JFXTextArea description;

    ///////Construtores  //  Constructos /////////
    public LiteRow(ImageView image, Label title, JFXTextArea description, Notification notification, String style) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.notification = notification;
        this.style = style;
        loadPane(image, title, description, notification, style);

    }

    public LiteRow(Notification notification) {

        this.image = new ImageView();
        this.title = new Label();
        this.description = new JFXTextArea();
        this.notification = notification;
        this.style = "-fx-background-color: #343232;"
                + "-fx-background-radius: 100px;"
                + "-fx-border-radius: 100px;";

        loadPane(notification);

    }

    public LiteRow(PostIt postIt) {

        this.image = new ImageView();
        this.title = new Label();
        this.description = new JFXTextArea();
        this.postIt = postIt;
        this.style = "-fx-background-color: black;"
                + "-fx-background-radius: 100px;"
                + "-fx-border-radius: 100px;";

        loadPane(postIt);
    }

    ///////metodos  //  method /////////
    private void loadPane(ImageView image, Label title, JFXTextArea description, Notification notification, String style) {

       this.setPrefWidth(411);
        this.setPrefHeight(100);
        this.setPadding(new Insets(20, 10, 10, 20));

        if (notification.getImage() != null || notification.getImage().equals("") == false) {
            image.setImage(notification.getImage().getImage().getImageFX());
        }

        image.setFitWidth(88);
        image.setFitHeight(88);
        image.setPreserveRatio(false);
        image.prefWidth(88);
        image.prefHeight(88);

        image.setVisible(true);

        title.prefWidth(304);
        title.prefHeight(21);
        title.setText(notification.getTitle());
        title.setStyle("-fx-text-fill: white;"
                + "    -fx-font-family: \"Arial\";"
                + "    -fx-font-size: 18px;"
                + "    -fx-font-style: italic;"
                + "    -fx-font-weight: bold;");
      //  title.setPadding( new Insets(0, 5, 10, 0));
        title.setVisible(true);

        description.prefWidth(313);
        description.prefHeight(41);

        description.setText(notification.getBody());
        description.setStyle("  -fx-font-family: \"Arial\";"
                + "    -fx-font-size: 13px;"
                + "    -fx-font-weight: bold;"
                + "    -fx-text-fill: white;"
                + "    -fx-background-color: transparent;"
                + "    -fx-background-radius: 100px;"
                + "    -fx-border-width: 2 0 2 0;"
                + "    -fx-border-color: #8A2BE2;");
        description.setPadding(new Insets(0, 0, 5, 0));
        description.setVisible(true);

        this.setTop(title);
        this.setCenter(description);
        this.setLeft(image);
        
        BorderPane.setMargin(image, new Insets(0, 0, 15, 2));
        
        BorderPane.setMargin(title, new Insets(2, 0, 2, 0));
        
        BorderPane.setMargin(description, new Insets(0, 5, 10, 0));
        
        this.setStyle("-fx-background-color: black;"
                + "-fx-border-width: 5 5 5 5;"
                + "-fx-border-color: " + notification.getType().getPrimaryColor() + ";");
        
        this.setVisible(true);
    }

    public void loadPane(Notification notification) {

        this.setPrefWidth(411);
        this.setPrefHeight(100);
        this.setPadding(new Insets(20, 10, 10, 20));

        if (notification.getImage() != null || notification.getImage().equals("") == false) {
            image.setImage(notification.getImage().getImage().getImageFX());
        }

        image.setFitWidth(88);
        image.setFitHeight(88);
        image.setPreserveRatio(false);
        image.prefWidth(88);
        image.prefHeight(88);

        image.setVisible(true);

        title.prefWidth(304);
        title.prefHeight(21);
        title.setText(notification.getTitle());
        title.setStyle("-fx-text-fill: white;"
                + "    -fx-font-family: \"Arial\";"
                + "    -fx-font-size: 18px;"
                + "    -fx-font-style: italic;"
                + "    -fx-font-weight: bold;");
      //  title.setPadding( new Insets(0, 5, 10, 0));
        title.setVisible(true);

        description.prefWidth(303);
        description.prefHeight(41);

        description.setText(notification.getBody());
        description.setStyle("  -fx-font-family: \"Arial\";"
                + "    -fx-font-size: 13px;"
                + "    -fx-font-weight: bold;"
                + "    -fx-text-fill: white;"
                + "    -fx-background-color: transparent;"
                + "    -fx-background-radius: 100px;"
                + "    -fx-border-width: 2 0 2 0;"
                + "    -fx-border-color: #8A2BE2;");
        description.setPadding(new Insets(0, 0, 5, 0));
        description.setVisible(true);

        this.setTop(title);
        this.setCenter(description);
        this.setLeft(image);
        
        BorderPane.setMargin(image, new Insets(0, 5, 15, 0));
        
        BorderPane.setMargin(title, new Insets(2, 0, 2, 0));
        
        BorderPane.setMargin(description, new Insets(0, 5, 12, 0));
        
        this.setStyle("-fx-background-color: black;"
                + "-fx-border-width: 5 5 5 5;"
                + "-fx-border-color: " + notification.getType().getPrimaryColor() + ";");
        
        this.setVisible(true);
    }

    public void loadPane(PostIt postIt) {

        this.setPrefWidth(411);
        this.setPrefHeight(100);
        this.setPadding(new Insets(20, 10, 10, 20));

        image.setVisible(true);

        title.prefWidth(304);
        title.prefHeight(21);
        title.setText(postIt.getTitle());
        title.setStyle("-fx-text-fill: white;"
                + "    -fx-font-family: \"Arial\";"
                + "    -fx-font-size: 18px;"
                + "    -fx-font-style: italic;"
                + "    -fx-font-weight: bold;");
      //  title.setPadding( new Insets(0, 5, 10, 0));
        title.setVisible(true);

        description.prefWidth(313);
        description.prefHeight(41);

        description.setText(postIt.getBody());
        description.setStyle("  -fx-font-family: \"Arial\";"
                + "    -fx-font-size: 13px;"
                + "    -fx-font-weight: bold;"
                + "    -fx-text-fill: white;"
                + "    -fx-background-color: transparent;"
                + "    -fx-background-radius: 100px;"
                + "    -fx-border-width: 2 0 2 0;"
                + "    -fx-border-color: #8A2BE2;");
        description.setPadding(new Insets(0, 0, 5, 0));
        description.setVisible(true);

        this.setTop(title);
        this.setCenter(description);
        this.setLeft(image);
        
        BorderPane.setMargin(image, new Insets(0, 0, 15, 2));
        
        BorderPane.setMargin(title, new Insets(2, 0, 2, 0));
        
        BorderPane.setMargin(description, new Insets(0, 5, 10, 0));
        
        this.setStyle("-fx-background-color: black;"
                + "-fx-border-width: 5 5 5 5;"
                + "-fx-border-color: " + postIt.getType().getPrimaryColor() + ";");
        
        this.setVisible(true);
    }

    ///////get and set/////////
    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
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

    public JFXTextArea getDescription() {
        return description;
    }

    public void setDescription(JFXTextArea description) {
        this.description = description;
    }

}
