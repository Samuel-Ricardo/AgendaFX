/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.Utilities.ImageFile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class ImageFactory {

    static ArrayList<ImageFile> generateImage(ResultSet result) throws SQLException {
    
        ArrayList<ImageFile> images = new ArrayList<>();
        
        while (result.next()) {
            
            ImageFile image = new ImageFile(result.getString("file_way"));
            
             images.add(image);
        }
        
        return images;
    }
    
}
