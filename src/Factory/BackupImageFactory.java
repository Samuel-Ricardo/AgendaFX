/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template image, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.BackupImage;
import Model.Utilities.ImageFile;
import Services.Downloader;
import Services.FileManager;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class BackupImageFactory {

    private final Downloader downloader;
    private final PostItFactory postItFactory;
    private final NotificationFactory notificationFactory;
    private final UserFactory userFactory;
    private final ImageFactory imageFactory;

    public BackupImageFactory() {
        this.downloader = new Downloader();
        this.postItFactory = new PostItFactory();
        this.notificationFactory = new NotificationFactory();
        this.userFactory = new UserFactory();
        this.imageFactory = new ImageFactory();
    }

    public BackupImageFactory(Downloader downloader, PostItFactory postItFactory, NotificationFactory notificationFactory, UserFactory userFactory, ImageFactory imageFactory) {
        this.downloader = downloader;
        this.postItFactory = postItFactory;
        this.notificationFactory = notificationFactory;
        this.userFactory = userFactory;
        this.imageFactory = imageFactory;
    }
    
    public BackupImage genereteBackupImage(ResultSet result) {
     
             BackupImage backupImage = new BackupImage();
        
        try {
            
            File localImage = new File(result.getString("image_way")); 
            
            File lcoalImage2 = new File(FileManager.getDefaultFolder()+"/"+localImage.getName());
            
            if (localImage.exists()) {
                
                backupImage.setImage(new ImageFile(localImage));
                
            }else if(lcoalImage2.exists()){
                
                backupImage.setImage(new ImageFile(lcoalImage2));
            }else{
            
               backupImage.setImage(imageFactory.generateImage(result.getString("image_name"), result.getBinaryStream("image_bytes")));
            }
            
            backupImage.setId(result.getInt("id_image"));
            backupImage.setNotification(notificationFactory.generateNotification(result));
            backupImage.setPostIt(postItFactory.generatePostIt(result));
            backupImage.setUser(userFactory.generateUser(result));
            
        } catch (SQLException ex) {
            Logger.getLogger(BackupImageFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    return backupImage;
    }
    
}
