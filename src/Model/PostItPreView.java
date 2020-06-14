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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Samuel
 */
public class PostItPreView extends HBox {

    private Rectangle typeColor;
    private Label title;
    private JFXTextArea body;
    private PostIt postIt;
    private VBox vbox;

    public PostItPreView(Rectangle typeColor, Label title, JFXTextArea body, PostIt postIt, VBox vbox) {

        this.typeColor = typeColor;
        this.title = title;
        this.body = body;
        this.postIt = postIt;
        this.vbox = vbox;

        load(typeColor, title, body, postIt, vbox);
    }

    public PostItPreView(PostIt postIt) {

        this.body = new JFXTextArea();
        this.postIt = postIt;
        this.title = new Label();
        this.typeColor = new Rectangle();

        load(postIt);
    }

    public void load(Rectangle typeColor, Label title, JFXTextArea body, PostIt postIt, VBox vbox) {

        this.setPrefHeight(140);
        this.setPrefWidth(140);
        this.setStyle("-fx-background-color: " + postIt.getType().getSecondaryColor() + ";");
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

        typeColor.setWidth(15);
        typeColor.setHeight(this.getHeight());
        typeColor.setStyle("-fx-fill:" + postIt.getType().getPrimaryColor() + "; "
                + "-fx-stroke: traspparent;");

        this.getChildren().add(typeColor);

        vbox.setPrefHeight(140);
        vbox.setPrefWidth(124);

        title.setText(postIt.getTitle());
        title.setPadding(new Insets(4, 0, 0, 4));

        vbox.getChildren().add(title);

        body.setText(postIt.getBody());

        vbox.getChildren().add(body);

        this.getChildren().add(vbox);
    }

    public void load(PostIt postIt) {

        this.setPrefHeight(140);
        this.setPrefWidth(140);
        this.setStyle("-fx-background-color: " + postIt.getType().getSecondaryColor() + ";");
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

        typeColor.setWidth(15);
        typeColor.setHeight(this.getHeight());
        typeColor.setStyle("-fx-fill:" + postIt.getType().getPrimaryColor() + "; "
                + "-fx-stroke: traspparent;");

        this.getChildren().add(typeColor);

        vbox.setPrefHeight(140);
        vbox.setPrefWidth(124);

        title.setText(postIt.getTitle());
        title.setPadding(new Insets(4, 0, 0, 4));

        vbox.getChildren().add(title);

        body.setText(postIt.getBody());

        vbox.getChildren().add(body);

        this.getChildren().add(vbox);

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
