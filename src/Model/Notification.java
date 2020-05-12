/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;

/**
 *
 * @author Samuel
 */
public class Notification extends PostIt{
   
    private String image;
    private File attachment;
    private File music;

    public Notification() {
    }

  

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public File getMusic() {
        return music;
    }

    public void setMusic(File music) {
        this.music = music;
    }

}
