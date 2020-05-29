/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.TypeDAO;
import DAO.UserDAO;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class Type {
    
    private Long id;
    private String name;
    private String color;
    private String colorDetails;
    private Long importance;
    private static ArrayList<Type> defaultTypes = TypeDAO.getDefaultTypes();
    private User user;
    private boolean vissible;
    public static final int MAX_PRIORITY = 10;
    public static final int HIGH_PRIORITY = 8;
    public static final int MEDIUN_PRIORITY = 5;
    public static final int LOW_PRIORITY = 3;
    public static final int MIN_PRIORITY = 1;

    public Type() {
        
        this.vissible = true; 
        
    }

    public boolean isVissible() {
        return vissible;
    }

    public void setVissible(boolean vissible) {
        this.vissible = vissible;
    }
    
    
    
    public Long getImportance() {
        return importance;
    }

    public void setImportance(Long importance) {
        this.importance = importance;
    }

    public static ArrayList<Type> getDefaultTypes() {
        return defaultTypes;
    }

    public static void setDefaultTypes(ArrayList<Type> defaultTypes) {
        Type.defaultTypes = defaultTypes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setImportance(int importance) {
        this.importance = new Long(importance);
    }
    
    public void setId(int id) {
        this.id = new Long(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        
        String hexadecimal = color.substring(color.lastIndexOf("#"));
        
        this.color = hexadecimal.replace(";", "");
    }

    public String getColorDetails() {
        return colorDetails;
    }

    public void setColorDetails(String colorDetails) {
        
         String hexadecimal = colorDetails.substring(colorDetails.lastIndexOf("#"));
        
        this.colorDetails = hexadecimal.replace(";", "");
    }
    
    
    
}
