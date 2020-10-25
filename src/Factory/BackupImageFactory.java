/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template image, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.BackupImage;
import Services.Downloader;
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

    public BackupImageFactory() {
        this.downloader = new Downloader();
        this.postItFactory = new PostItFactory();
        this.notificationFactory = new NotificationFactory();
        this.userFactory = new UserFactory();
    }

    public BackupImageFactory(Downloader downloader, PostItFactory postItFactory, NotificationFactory notificationFactory, UserFactory userFactory) {
        this.downloader = downloader;
        this.postItFactory = postItFactory;
        this.notificationFactory = notificationFactory;
        this.userFactory = userFactory;
    }
    
    public BackupImage genereteBackupImage(ResultSet result) {
     
             BackupImage backupImage = new BackupImage();
        
        try {
            
            backupImage.setId(result.getInt("id_image"));
            backupImage.setImage(result.getString("image_way"));
            backupImage.setNotification(notificationFactory.generateNotification(result));
            backupImage.setPostIt(postItFactory.generatePostIt(result));
            backupImage.setBytes(downloader.downloadBytes(result.getBinaryStream("image_bytes")));

            downloader.createFile(backupImage.getArrayBytes(), backupImage.getFile());
            
        } catch (SQLException ex) {
            Logger.getLogger(BackupImageFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    return backupImage;
    }
    
}
