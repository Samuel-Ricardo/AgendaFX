/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author Samuel
 */
public class ChooserPane extends Pane {

    private Pane pane;
    private Label title;
    private ComboBox<String> type;
    private ObservableList<String> obsTypes;
    private ArrayList<String> alTypes;
    private Button button;
    private int index;
    private boolean open = true;

    public ChooserPane(Label title, ComboBox<String> type, ObservableList<String> obsTypes, Button button, ArrayList<String> alTypes) {

        this.title = title;
        this.type = type;
        this.obsTypes = obsTypes;
        this.button = button;
        this.alTypes = alTypes;

    }

    public ChooserPane() {

        this.pane = new Pane();
        this.title = new Label();
        this.type = new ComboBox<>();
        this.obsTypes = null;
        this.button = new Button();
        this.alTypes = new ArrayList<>();
        
        
        this.setPrefHeight(247);
        this.setPrefHeight(179);
        

    }

    public int open() {

        pane.setPrefHeight(247);
        pane.setPrefHeight(179);
        pane.setStyle("  -fx-background-color: #343232;"
                + "-fx-background-radius: 30px;");
        pane.setVisible(true);

        title.setStyle("  -fx-text-fill: white;\n"
                + "    -fx-font-family: \"Arial\";\n"
                + "    -fx-font-size: 18px;\n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-weight: bold;\n");

        title.setLayoutX(14);
        title.setLayoutY(21);
        title.setText("o que tipo deseja Criar?");

        pane.getChildren().add(title);
        
        type.prefWidth(30);
        type.prefHeight(30);
        type.setLayoutX(28);
        type.setLayoutY(77);
        type.setStyle("  -fx-text-fill: #8A2BE2;\n"
                + "    -fx-font-family: \"Arial\";\n"
                + "    -fx-font-size: 14px;\n"
                + "    -fx-background-color: #343232;\n");

        pane.getChildren().add(type);
        
        button.setLayoutX(45);
        button.setLayoutY(134);
        button.prefWidth(150);
        button.prefHeight(25);
        button.setText("Criar");
        
        pane.getChildren().add(button);

        alTypes.add("Notficaçao");
        alTypes.add("PostIt");
        alTypes.add("Atividade");

        obsTypes = FXCollections.observableArrayList(alTypes);

        type.setItems(obsTypes);

        this.setVisible(true);

       
             
             button.setOnAction((t) -> {

                this.setVisible(false);
                index = type.getSelectionModel().getSelectedIndex();

                open = false;
            });
         while (open == true) {
        }

        return index;

    }

    public int open(Label title, ComboBox<String> type, ObservableList<String> obsTypes, Pane pane, Button button) {

        this.setPrefHeight(247);
        this.setPrefHeight(179);
        this.setStyle("  -fx-background-color: #343232;"
                + "-fx-background-radius: 30px;");

        title.setStyle("  -fx-text-fill: white;\n"
                + "    -fx-font-family: \"Arial\";\n"
                + "    -fx-font-size: 18px;\n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-weight: bold;\n");

        title.setLayoutX(14);
        title.setLayoutY(21);
        title.setText("o que tipo deseja Criar?");

        type.prefWidth(30);
        type.prefHeight(30);
        type.setLayoutX(28);
        type.setLayoutY(77);
        type.setStyle("  -fx-text-fill: #8A2BE2;\n"
                + "    -fx-font-family: \"Arial\";\n"
                + "    -fx-font-size: 14px;\n"
                + "    -fx-background-color: #343232;\n");

        button.setLayoutX(45);
        button.setLayoutY(134);
        button.prefWidth(150);
        button.prefHeight(25);
        button.setText("Criar");

        alTypes.add("Notficaçao");
        alTypes.add("PostIt");
        alTypes.add("Atividade");

        obsTypes = FXCollections.observableArrayList(alTypes);

        type.setItems(obsTypes);

        button.setOnMouseClicked((t) -> {

            this.setVisible(false);
            index = type.getSelectionModel().getSelectedIndex();

        });

        return index;

    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public ComboBox<String> getType() {
        return type;
    }

    public void setType(ComboBox<String> type) {
        this.type = type;
    }

    public ObservableList<String> getObsTypes() {
        return obsTypes;
    }

    public void setObsTypes(ObservableList<String> obsTypes) {
        this.obsTypes = obsTypes;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
