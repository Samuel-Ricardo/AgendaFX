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

    Downloader downloader = new Downloader();
    P
    
    public Attachment genereteAttachment(ResultSet result) {
     
             Attachment attachment = new Attachment();
        
        try {
            
            attachment.setId(result.getInt("id_file"));
            attachment.setFile(result.getString("file_way"));
            attachment.setNotification();
            attachment.setPostIt(result.get);
            attachment.setBytes(downloader.download(result.getBinaryStream("file_bytes")));

        } catch (SQLException ex) {
            Logger.getLogger(AttachmentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
