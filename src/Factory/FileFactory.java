/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class FileFactory {

    static ArrayList<File> generateAttachment(ResultSet result) throws SQLException {
    
        ArrayList<File> Attachments = new ArrayList<>();
        
        while (result.next()) {
            
            File file = new File(result.getString("file_way"));
            
        Attachments.add(file);
        }
        
        return Attachments;
    }
    
}
