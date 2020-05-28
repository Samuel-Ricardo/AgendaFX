/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

       public Long getImportancia() {
        return importance;
    }

    public void setImportancia(Long importance) {
        this.importance = importance;
    }
    
    public void setImportancia(int importance) {
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
