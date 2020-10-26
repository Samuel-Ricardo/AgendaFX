/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Utilities.ImageFile;

/**
 *
 * @author Samuel
 */
public class BackupImage {
    
    private Integer id;
    private ImageFile image;
    private PostIt postIt;
    private User user;
    private Notification notification;

    public BackupImage(Integer id, ImageFile image, PostIt postIt, User user, Notification notification) {
        this.id = id;
        this.image = image;
        this.postIt = postIt;
        this.user = user;
        this.notification = notification;
    }

    public BackupImage() {
    }

    public BackupImage(Notification notification) {
    
        this.notification = notification;
    }
    
    public BackupImage(User user) {
    
        this.user = user;
    }
    
    public BackupImage(PostIt postIt) {
    
        this.postIt = postIt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ImageFile getImage() {
        return image;
    }

    public void setImage(ImageFile image) {
        this.image = image;
    }

    public PostIt getPostIt() {
        return postIt;
    }

    public void setPostIt(PostIt postIt) {
        this.postIt = postIt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
    
    
    
}
