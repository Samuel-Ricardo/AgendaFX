/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class PostIt {

    protected int id;
    protected String title;
    protected String description;
    protected Date scheduledDay;
    protected boolean warned;
    protected String type;
    protected String typeColor;
    protected User user;
    protected File music;

    public PostIt(User user) {
        this.user = user;
    }

    public PostIt() {
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

    public java.sql.Date getSQLScheduledDay() {

        java.sql.Date sqlDate = new java.sql.Date(scheduledDay.getTime());

        return sqlDate;
    }

    public String getScheduledDate() {

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

        return date.format(scheduledDay);
    }

    public String getScheduledHour() {

        SimpleDateFormat date = new SimpleDateFormat("HH:mm");

        return date.format(scheduledDay);
    }
    
    public LocalDate getScheduledLocalDate(){
        
       // scheduledDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       
        return getSQLScheduledDay().toLocalDate();
    }
    public LocalTime getScheduledLocalTime(){
        
        SimpleDateFormat horary = new SimpleDateFormat("HH:mm");
        
      Date scheduledHour = null;
        try {
            scheduledHour = horary.parse(getScheduledHour());
        } catch (ParseException ex) {
            Logger.getLogger(PostIt.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return scheduledHour.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public String getTypeColor() {
        return typeColor.replace(";", "");
    }

    public void setTypeColor(String typeColor) {
        String hexadecimal = typeColor.substring(typeColor.lastIndexOf("#"));
        this.typeColor = hexadecimal.replace(";", "");
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getMusic() {
        return music;
    }

    public void setMusic(File music) {
        this.music = music;
    }
    
    

}
