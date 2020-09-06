/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Utilities.ImageFile;
import Model.Interfaces.Reminder;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class PostIt extends Reminder{

    private ArrayList<ImageFile> images;
    private ArrayList<File> Attachments;

    public PostIt() {
        
        this.images = new ArrayList<>();
        this.Attachments = new ArrayList<>();
    }

    public PostIt(User user) {
        super(user);
        
        this.images = new ArrayList<>();
        this.Attachments = new ArrayList<>();
    }

    public ArrayList<ImageFile> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageFile> images) {
        this.images = images;
    }

    public ArrayList<File> getAttachments() {
        return Attachments;
    }

    public void setAttachments(ArrayList<File> Attachments) {
        this.Attachments = Attachments;
    }
    
    public File getAttachment() {
        
        return Attachments.get(0);
    }

    public void setAttachment(File Attachment) {
        this.Attachments = new ArrayList<File>();
        
        Attachments.add(Attachment);
    }
    
      public void addAttachment(File Attachment) {
        this.Attachments.add(Attachment);
    }
}
