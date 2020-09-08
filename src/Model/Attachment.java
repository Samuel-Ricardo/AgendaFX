/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class Attachment {
    
    private Integer id;
    private File file;
    private ArrayList<Byte> bytes;
    private PostIt postIt;
    private Notification notification;

    public Attachment(Integer id, File file, ArrayList<Byte> bytes, PostIt postIt, Notification notification) {
        this.id = id;
        this.file = file;
        this.bytes = bytes;
        this.postIt = postIt;
        this.notification = notification;
    }

    public Attachment(Integer id, File file, ArrayList<Byte> bytes) {
        this.id = id;
        this.file = file;
        this.bytes = bytes;
        this.postIt = new PostIt();
        this.notification = new Notification();
    }

    public Attachment(File file) {
        this.id = new Integer(0);
        this.file = file;
        this.bytes = new ArrayList<>();
        this.postIt = new PostIt();
        this.notification = new Notification();
    }
    
   public Attachment(){
       id = new Integer(0);
   }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList<Byte> getBytes() {
        return bytes;
    }

    public void setBytes(ArrayList<Byte> bytes) {
        this.bytes = bytes;
    }

    public PostIt getPostIt() {
        return postIt;
    }

    public void setPostIt(PostIt postIt) {
        this.postIt = postIt;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
