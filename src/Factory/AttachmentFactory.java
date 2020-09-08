/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.Attachment;
import Services.Downloader;
import java.sql.ResultSet;

/**
 *
 * @author Samuel
 */
public class AttachmentFactory {

    Downloader downloader;
    
    public static Attachment genereteAttachment(ResultSet result) {
     
    Attachment attachment = new Attachment();
    
        attachment.setId(result.getInt("id_file"));
        attachment.setFile(result.getString("file_way"));
        attachment.setId(result.get);
        attachment.setId(result.get);
        attachment.setId(result.get);
        attachment.setId(result.get);
        attachment.setId(result.get);
    
    }
    
}
