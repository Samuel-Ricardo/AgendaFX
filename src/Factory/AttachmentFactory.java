/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.Attachment;
import Services.Downloader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class AttachmentFactory {

    private final Downloader downloader;
    private final PostItFactory postItFactory;
    private final NotificationFactory notificationFactory;

    public AttachmentFactory() {
        this.downloader = null;
        this.postItFactory = null;
        this.notificationFactory = null;
    }

    public AttachmentFactory(Downloader downloader, PostItFactory postItFactory, NotificationFactory notificationFactory) {
        this.downloader = downloader;
        this.postItFactory = postItFactory;
        this.notificationFactory = notificationFactory;
    }
    
    public Attachment genereteAttachment(ResultSet result) {
     
             Attachment attachment = new Attachment();
        
        try {
            
            attachment.setId(result.getInt("id_file"));
            attachment.setFile(result.getString("file_way"));
            attachment.setNotification(notificationFactory.generateNotification(result));
            attachment.setPostIt(postItFactory.generatePostIt(result));
            attachment.setBytes(downloader.downloadBytes(result.getBinaryStream("file_bytes")));

            downloader.createFile(attachment.getArrayBytes(), attachment.getFile());
            
        } catch (SQLException ex) {
            Logger.getLogger(AttachmentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    return attachment;
    }
    
}
