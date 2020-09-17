/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Utilities;

import Model.Type;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Samuel
 */
public class TypeRow extends BorderPane{
    
    private Label name;
    private Rectangle primaryColor;
    private Type type;

    public TypeRow(Label name, Rectangle primaryColor, Type type) {
        this.name = name;
        this.primaryColor = primaryColor;
        this.type = type;
        
        load(name, primaryColor, type);
    }

    public TypeRow(Type type) {
        
        this.type = type;
        this.name = new Label();
        this.primaryColor = new Rectangle();
        
        load(type);
        
    }
    
    public void load(Label name, Rectangle primaryColor, Type type){
        
        this.setPrefWidth(190);
        this.setPrefHeight(31);
        this.setStyle("-fx-background-color: transparent;");
        
        name.setText(type.getName());
        
        primaryColor.prefHeight(23);
        primaryColor.prefWidth(54);
        primaryColor.setStyle("-fx-fill: "+type.getPrimaryColor()+";");
        
        this.setLeft(name);
        this.setRight(primaryColor);
        
        this.setVisible(true);
        
    }
    
    public void load(Type type){
        
        this.setPrefWidth(180);
        this.setPrefHeight(31);
        this.setStyle("-fx-background-color: transparent;"
                    + "-fx-border-width: 0 5 0 5;"
                    + "-fx-border-color: " +type.getPrimaryColor()+ ";");
        name.setText(type.getName());
        
        primaryColor.prefHeight(23);
        primaryColor.prefWidth(54);
        primaryColor.setStyle("-fx-fill: "+type.getPrimaryColor()+";");
        System.out.println(primaryColor.getStyle());

        
        this.setCenter(name);
        this.setRight(primaryColor);
        
        this.setVisible(true);
    }
    
}
