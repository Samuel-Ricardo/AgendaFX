/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Samuel
 */
public class Notificacao {

    private int id;
    private String title;
    private String description;
    private String image;
    private Date scheduledDay;
    private boolean warned;
    private String type;
    private String attachment;
    private String music;

    public Notificacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getScheduledDay() {
        return scheduledDay;
    }

    public void setScheduledDay(Date scheduledDay) {
        this.scheduledDay = scheduledDay;
    }

    public boolean isWarned() {
        return warned;
    }

    public void setWarned(boolean warned) {
        this.warned = warned;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public java.sql.Date getSQLScheduledDay(){
        
        java.sql.Date sqlDate = new java.sql.Date(scheduledDay.getTime());
        
        return sqlDate;
    }
    
    public String getScheduledDate(){
        
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        
        return date.format(scheduledDay);
    }
    
    public String getScheduledHour(){
        
        SimpleDateFormat date = new SimpleDateFormat("HH:mm");
        
        return date.format(scheduledDay);
    }
    
}
