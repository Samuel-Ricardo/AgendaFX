/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.PostItCreatorController;
import Controller.PostItScreenController;
import Main.MainPostItScreen;
import com.jfoenix.controls.JFXTextArea;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Samuel
 */
public class PostItPreView extends VBox {

    private Label title;
    private JFXTextArea body;
    private PostIt postIt;

    public PostItPreView(Label title, JFXTextArea body, PostIt postIt) {

        this.title = title;
        this.body = body;
        this.postIt = postIt;

        load(title, body, postIt);
    }

    public PostItPreView(PostIt postIt) {

        this.body = new JFXTextArea();
        this.postIt = postIt;
        this.title = new Label();

        load(postIt);
    }

    public void load(Label title, JFXTextArea body, PostIt postIt) {

        this.setPrefHeight(140);
        this.setPrefWidth(140);
        this.setStyle("-fx-background-color: " + postIt.getType().getSecondaryColor() + ";"
                    + "-fx-border-width: 0 0 0 15;"
                    + "-fx-border-color: " + postIt.getType().getPrimaryColor() + ";");
        this.setOnMouseClicked((t) -> {

            if (MainPostItScreen.getWindow() != null) {
                MainPostItScreen.getWindow().close();
            }

            try {

                MainPostItScreen PostItScreen = new MainPostItScreen();

                PostItScreen.start(new Stage());
                PostItScreenController.loadPostIt(postIt);
            } catch (Exception ex) {
                Logger.getLogger(PostItPreView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        this.setPrefHeight(140);
        this.setPrefWidth(140);

        title.setText(postIt.getTitle());
        title.setPadding(new Insets(4, 0, 0, 4));

        this.getChildren().add(title);

        body.setText(postIt.getBody());
        body.getStyleClass().add("text-area-empyt");

        this.getChildren().add(body);
    }

    public void load(PostIt postIt) {

        this.setPrefHeight(140);
        this.setPrefWidth(140);
        this.setStyle("-fx-background-color: " + postIt.getType().getSecondaryColor() + ";"
                    + "-fx-border-width: 0 0 0 15;"
                    + "-fx-border-color: " + postIt.getType().getPrimaryColor() + ";");
        this.setOnMouseClicked((t) -> {

            if (MainPostItScreen.getWindow() != null) {
                MainPostItScreen.getWindow().close();
            }

            try {

                MainPostItScreen PostItScreen = new MainPostItScreen();

                PostItScreen.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(PostItPreView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        this.setPrefHeight(140);
        this.setPrefWidth(140);

        title.setText(postIt.getTitle());
        title.setPadding(new Insets(4, 0, 0, 4));

        this.getChildren().add(title);

        body.setText(postIt.getBody());
        body.getStyleClass().add("text-area-empyt");

        this.getChildren().add(body);
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
