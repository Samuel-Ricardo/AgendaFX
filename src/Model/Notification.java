/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Interfaces.Reminder;
import Model.Utilities.ImageFile;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class Notification extends Reminder{
    
    private Date scheduledDay;
    private boolean warned;
    private File music;
    private String image;
    private ArrayList<File> Attachments;
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public File getMusic() {
        return music;
    }

    public ArrayList<File> getAttachments() {
        return Attachments;
    }

    public void setAttachments(ArrayList<File> Attachments) {
        this.Attachments = Attachments;
    }

    
    
    public void setMusic(File music) {
        this.music = music;
    }



    public Notification(User user) {
        this.user = user;
    }

    public Notification() {
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

    public void setScheduledDay(LocalDate date, LocalTime time) {
     
        SimpleDateFormat complet = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                
        String stringDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String stringTime = time.format(dtf);
        
        try {
            scheduledDay = complet.parse(stringDate+" "+stringTime);
        } catch (ParseException ex) {
            Logger.getLogger(PostIt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
