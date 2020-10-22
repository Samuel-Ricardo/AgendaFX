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
    
    
    
}
