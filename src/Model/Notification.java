/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Interfaces.Reminder;
import Time.Time;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Samuel
 */
public class Notification extends Reminder{
    
    private Time scheduledDay;
    private boolean warned;
    private File music;
    private String image;
    private ArrayList<File> Attachments;

    public Notification(Time scheduledDay, boolean warned, File music, String image, ArrayList<File> Attachments, User user) {
        super(user);
        this.scheduledDay = scheduledDay;
        this.warned = warned;
        this.music = music;
        this.image = image;
        this.Attachments = Attachments;
    }

    public Notification(Time scheduledDay, boolean warned, File music, String image, ArrayList<File> Attachments) {
        this.scheduledDay = scheduledDay;
        this.warned = warned;
        this.music = music;
        this.image = image;
        this.Attachments = Attachments;
    }
    
    public Notification(){
        
        this.scheduledDay = new Time();
        this.warned = false;
        this.Attachments = new ArrayList<>();
    }
    
        
    public Notification(User user) {
        
        this.user = user;
        this.scheduledDay = new Time();
        this.warned = false;
        this.Attachments = new ArrayList<>();
    }
    
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

    public ArrayList<File> getAttachment() {
        
        return Attachments;
    }

    public void setAttachment(File Attachment) {
        this.Attachments = new ArrayList<File>();
        
        Attachments.add(Attachment);
    }
    
    public void addAttachment(File Attachment) {
        this.Attachments.add(Attachment);
    }
    
    public void setMusic(File music) {
        this.music = music;
    }

    public Time getScheduledDay() {
        return scheduledDay;
    }

    public void setScheduledDay(Time scheduledDay) {
        this.scheduledDay = scheduledDay;
    }
    
    public void setScheduledDay(Date scheduledDay) {
        this.scheduledDay = new Time(scheduledDay);
    }
    
    public boolean isWarned() {
        return warned;
    }

    public void setWarned(boolean warned) {
        this.warned = warned;
    }

    public void setScheduledDay(LocalDateTime date) {
        
            this.scheduledDay = new Time(date);
    }
}
